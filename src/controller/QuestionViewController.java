package controller;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class QuestionViewController extends AbstractController implements Initializable{
    private int _iteration = 0;
    private int _score = 0;
    private int _attempt = 1;
    private int _numQuestions;
    private MediaPlayer _mediaPlayer;

    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static NumberCollection _pracModel = PracticeCollection.instance();
    private static NumberCollection _mathModel = MathsCollection.instance();
    private static NumberCollection _customModel = CustomCollection.instance();

    private String _textResult;
    private HashMap<Integer, String> _questionMap;
    private HashMap<Integer, String> _answerMap;

    //Progress Bar
    @FXML
    private ProgressBar progressBar;

    //Buttons
    @FXML
    private Button recordBtn;
    @FXML
    private Button skipQuestionBtn;
    @FXML
    private Button menuBtn;

    //Labels
    @FXML
    private Label numberLbl;
    @FXML
    private Label scoreLbl;
    @FXML
    private Label attemptLbl;
    @FXML
    private Label recordLbl;
    @FXML
    private Label skipLbl;

    //Icons
    @FXML
    MaterialDesignIconView recordIcon;
    @FXML
    MaterialDesignIconView skipIcon;
    @FXML
    MaterialDesignIconView homeIcon;

    /**
     * This method was overridden in order to disable various buttons, intialise certain labels, load the first question,
     * and to bind the visible properties of the overlay labels to the disabled properties of the corresponding buttons.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recordBtn.setDisable(false);
        scoreLbl.setText(_score + "/" + _iteration);
        attemptLbl.setText(_attempt + "");
        _numQuestions = _modeDirector.getNumQuestions() - 2;
        _modeDirector.setScore(0);
        _modeDirector.setIteration(0);

        if (_modeDirector.getType() == ModeDirector.Type.MATH) {
            if (_modeDirector.getMode() == ModeDirector.Mode.MATH_CUSTOM) {
                _questionMap = _customModel.getCurrentQuestionMap();
                _answerMap = _customModel.getCurrentAnswerMap();
            } else {
                _questionMap = _mathModel.getCurrentQuestionMap();
                _answerMap = _mathModel.getCurrentAnswerMap();
            }
        }
        else {
            _questionMap = _pracModel.getCurrentQuestionMap();
            _answerMap = _pracModel.getCurrentAnswerMap();
        }

        numberLbl.setText(_questionMap.get(_iteration));

        recordLbl.visibleProperty().bind(recordBtn.disabledProperty().not());
        skipLbl.visibleProperty().bind(skipQuestionBtn.disabledProperty().not());
        recordIcon.visibleProperty().bind(recordBtn.disabledProperty().not());
        skipIcon.visibleProperty().bind(skipQuestionBtn.disabledProperty().not());

    }

    /**
     * This method is the action for the "skip" button. It first loads the scene for the skip class pop up, then sets
     * the controller, passing in the iteration, number of questions, score and the current question view controller
     * its self. This is need so that methods can be called on this controller in order to update the question etc.
     * After doing this is calls a method from abstract controller to push the pop up.
     */
    public void skipQuestion() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(File.separator + "view" + File.separator + "SkipPopup.fxml"));
        loader.setController(new SkipPopupController(_iteration, _numQuestions, _score, this));
        try {
            pushPopup(new Scene(loader.load()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This class extends Task in order to perform the recording on a background thread. This is required since the
     * recording of the users audio is done by a BASH command. This class is instantiated and executed upon the user
     * pressing the record button.
     */
    class AudioInBackground extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {
            String cmd = "ffmpeg -nostats -loglevel 0 -f alsa -i default -t 3 -acodec pcm_s16le -ar 22050 -ac 1 -y  data" + File.separator + "audio.wav";
            runInBash(cmd);
            return 0;
        }
    }


    /**
     * This class extends Task in order to display a progress bar to assit the user on knowing the time remaining.
     * This class is instantiated and executed when the user clicks the record button.
     */
    class Timer extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {
            for (int i = 0; i < 60; i++) {
                updateProgress(i, 59);
                Thread.sleep(50);
            }
            return 0;
        }
    }

    /**
     * This is the action the for record button. It first disables both the record button and the skip question button.
     * It then creates an instance of the class used to record audio in a background thread, setting the action for when
     * it is completed to call the playBack audio method and instantiate a voice recognition class. The action on
     * completion for the voice recognition thread is then set the display the correct view if the answer was right,
     * or the wrong view if the answer was wrong. In addition to this it also handles the logic to do with the question.
     */
    public void recordAudio() {
        recordBtn.setDisable(true);
        skipQuestionBtn.setDisable(true);
        menuBtn.setDisable(true);

        AudioInBackground record = new AudioInBackground();

        record.setOnSucceeded((WorkerStateEvent event) -> {
            playbackAudio();

            VoiceRecognitionInBackground recognition = new VoiceRecognitionInBackground();
            recognition.setOnSucceeded((WorkerStateEvent revent) -> {
                try {
                    readResults();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                _modeDirector.setCurrentAnswer(_textResult, _answerMap.get(_iteration));
                if (_textResult.equals(_answerMap.get(_iteration))) {
                    _score+=1;
                    _modeDirector.setScore(_score);
                    pushChild("CorrectView");
                    nextQuestion();
                }
                else {
                    setSuperAttempts(_attempt);
                    pushChild("WrongView");
                    _attempt++;
                }
                resetBtns();

            });
            new Thread(recognition).start();
        });


        Timer timer = new Timer();

        progressBar.progressProperty().bind(timer.progressProperty());

        new Thread(record).start();
        new Thread(timer).start();

    }

    /**
     * This method reads the recout.mlf file which contains the results of the HTK voice recognition software. It then
     * sets the instance variable _textResult to the result found within the file.
     * @throws IOException
     */
    private void readResults() throws IOException {
        _textResult = "";

        // Reads file recout file
        if (!(new File("data" + File.separator + "recout.mlf").exists())) {
            _textResult="";
            return;
        }

        File file = new File("data" + File.separator + "recout.mlf");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();

        String text = new String(bytes, "UTF-8");

        //Puts string from file into string array
        String[] words = text.split("\\W+");

        // Places text file back into string without the extra formatting
        for (int j = 0; j < words.length-1; j++) {
            if (j == words.length-2) {
                _textResult += words[j];
                break;
            }
            _textResult += words[j] + " ";
        }

        // Removes any left over text that is not needed
        _textResult = _textResult.replaceFirst(" ", "").replaceFirst("MLF audio rec sil ", "");
    }

    /**
     * This method extends Task. It is used for completing the voice recognition in a background thread. This is done
     * through a BASH command, so the command is set up, then it calls the helper method runInBash() which uses Java's
     * process builder functionality y to run the process inside BASH.
     */
    class VoiceRecognitionInBackground extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {
            String docDirectory = "models" + File.separator + "HTK" + File.separator + "MaoriNumbers" + File.separator;
            String cmd = "HVite -H " + docDirectory + "HMMs" + File.separator + "hmm15" +  File.separator + "macros -H " + docDirectory + "HMMs" +  File.separator +
                    "hmm15" +  File.separator + "hmmdefs -C " + docDirectory + "user" +  File.separator + "configLR  -w " + docDirectory + "user" +  File.separator
                    + "wordNetworkNum -o SWT -l '*' -i " + "data" + File.separator + "recout.mlf -p 0.0 -s 5.0  " + docDirectory + "user" +  File.separator +
                    "dictionaryD " + docDirectory + "user" + File.separator + "tiedList data" + File.separator + "audio.wav";
            runInBash(cmd);
            return 0;
        }
    }

    /**
     * This method is used to run commands in BASH. It takes the command as a String type as a parameter then uses the
     * inbuild process and process builder functionality to run the process inside BASH.
     * @param cmd desired command to run in bash
     */
    private void runInBash(String cmd) {
        Process process;
        ProcessBuilder processBuilder = new ProcessBuilder(File.separator + "bin" + File.separator + "bash", "-c", cmd);
        try {
            process = processBuilder.start();
        } catch (IOException excep) {
            excep.printStackTrace();
            return;
        }
        try {
            process.waitFor();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * This method is a helper method for updating labels and setting the next iteration. This method is called from
     * various places inside QuestionViewController each time the next question is required to be loaded/set.
     */
    protected void nextQuestion() {
        _iteration+=1;
        _modeDirector.setIteration(_iteration);
        numberLbl.setText(_questionMap.get(_iteration));
        scoreLbl.setText(_score + "/" + _iteration);
        _attempt = 1;
    }

    /**
     * This method is a helper method for resetting the buttons/artifacts for the next iteration. This method is called
     * from various places inside QuestionViewController and is called when the next iteration is occuring.
     */
    protected void resetBtns() {
        if (_attempt > 2) {
            nextQuestion();
        }
        skipQuestionBtn.setDisable(false);
        recordBtn.setDisable(false);
        menuBtn.setDisable(false);
        progressBar.progressProperty().unbind();
        progressBar.setProgress(0.0);
        attemptLbl.setText("" +  _attempt);
    }

    //Action for the "home" button. This method calls a method from abstract controller in order to load the "are you sure" pop up
    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is a helper method to playback the audio which the user recorded. This method is called from inside
     * QuestionViewController upon successfully recording the users audio answer. This method first tests whether playback
     * has been enabled by the user and if so loads and plays their recording.
     */
    private void playbackAudio() {
        if (_modeDirector.getPlaybackEnabled()) {
            File file = new File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "audio.wav");
            Media media = new Media(file.toURI().toString());
            _mediaPlayer = new MediaPlayer(media);
            _mediaPlayer.setAutoPlay(true);
        }
    }
}
