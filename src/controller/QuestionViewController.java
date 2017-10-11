package controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Region;
import main.Main;
import model.AudioDirector;
import model.MathsCollection;
import model.NumberCollection;
import model.PracticeCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuestionViewController extends AbstractController implements Initializable{
    private int _iteration = 0;
    private int _score = 0;
    private int _attempt = 1;
    private static AudioDirector _audioDirector = AudioDirector.instance();
    private static NumberCollection _model = PracticeCollection.instance();
    private static NumberCollection _mathModel = MathsCollection.instance();
    private String _textResult;
    private HashMap<Integer, String> _questionMap;
    private HashMap<Integer, String> _answerMap;

    @FXML
    private Label numberLbl;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button recordBtn;
    @FXML
    Button returnMainBtn;
    @FXML
    Button skipQuestionBtn;
    @FXML
    Label scoreLbl;
    @FXML
    Label attemptLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recordBtn.setDisable(false);

        scoreLbl.setText("Score: " + _score + "/" + _iteration);
        attemptLbl.setText("Attempt: " + _attempt);

        //generateNumber();
        if (_model.getType() == NumberCollection.Type.MATH) {
            _questionMap = _mathModel.getCurrentQuestionMap();
            _answerMap = _mathModel.getCurrentAnswerMap();
        }
        else {
            _questionMap = _model.getCurrentQuestionMap();
            _answerMap = _model.getCurrentAnswerMap();
        }
        numberLbl.setText(_questionMap.get(_iteration));
    }

    // Returns to main menu when button is pressed.
    public void returnToMain() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("You are about to quit!");
        alert.setContentText("Do you want to save progress?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(yes, no, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            _model.updateStats(_score,_iteration);
            popChild(); //will throw exceptions until proper menu is set up
            //WILL NEED TO ADD LOGIC FOR SAVINGS

        }
        else if (result.get() == no) {
            _model.updateStats(_score,_iteration);
            popChild();
        }
    }

    // Carries out calls to skip the current questions and move onto next
    public void skipQuestion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setContentText("Are you sure you want to skip the current question?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        ButtonType yes = new ButtonType("Yes");
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(yes, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            if (_iteration > 8) {
                _model.updateStats(_score, _iteration);
                pushChild("SummaryView");
            } else {
                _attempt = 1;
                nextQuestion();
                resetBtns();
                //NEEDS LOGIC FOR NEXT ITERATION
            }

        }
    }

    //Extends Task to perform work on a worker thread for recording the audio
    class AudioInBackground extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {
            String cmd = "ffmpeg -nostats -loglevel 0 -f alsa -i default -t 3 -acodec pcm_s16le -ar 22050 -ac 1 -y  data" + File.separator + "audio.wav";
            runInBash(cmd);
            return 0;
        }
    }


    //Extends Task to perform work on a worker thread for a timer used to show progress in a progress bar
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

    //Helper method for changing the scene, takes a string of the scene name as a parameter
    private void sceneChange(String scene) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource( File.separator + "View" + File.separator + scene + ".fxml"));
            Main.pushScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Records audio on a different thread from a bash process
    public void recordAudio() {
        recordBtn.setDisable(true);
        returnMainBtn.setDisable(true);
        skipQuestionBtn.setDisable(true);

        AudioInBackground record = new AudioInBackground();

        record.setOnSucceeded((WorkerStateEvent event) -> {
            //playbackAudio();

            VoiceRecognitionInBackground recognition = new VoiceRecognitionInBackground();
            recognition.setOnSucceeded((WorkerStateEvent revent) -> {
                System.out.println("textresult: " + _textResult);
                System.out.println("currentanswer: " + _answerMap.get(_iteration));
                try {
                    readResults();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (_textResult.equals(_answerMap.get(_iteration))) {
                    _score+=1;
                    pushChild("CorrectView");
                    nextQuestion();
                }
                else {
                    pushChild("WrongView");
                    _attempt++;
                }
                resetBtns();


            });
            new Thread(recognition).start();
        });


        Timer timer = new Timer();

        progressBar.progressProperty().bind(timer.progressProperty());
        ColorAdjust adjust = new ColorAdjust();
        adjust.setHue(-0.4);
        progressBar.setEffect(adjust);

        new Thread(record).start();
        new Thread(timer).start();

    }

    //Reads results of voice recognition, goes to file in documents. Need to change so it work for VM directory
    private void readResults() throws IOException {
        _textResult = "";

        // reads file
        File file = new File("data" + File.separator + "recout.mlf");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();

        String text = new String(bytes, "UTF-8");
        //Puts string from file into string array
        String[] words = text.split("\\W+");

        // Places text file back into string without the extra format
        for (int j = 0; j < words.length-1; j++) {
            if (j == words.length-2) {
                _textResult += words[j];
                break;
            }
            _textResult += words[j] + " ";
        }

        // Removes any text that is not needed
        _textResult = _textResult.replaceFirst(" ", "").replaceFirst("MLF audio rec sil ", "");
    }

    // Extends Task to perform work on a worker thread to carry out voice recognition
    class VoiceRecognitionInBackground extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {
            String docDirectory ="~" + File.separator + "Documents" + File.separator + "HTK" + File.separator + "MaoriNumbers" + File.separator;
            String cmd = "HVite -H " + docDirectory + "HMMs" + File.separator + "hmm15" +  File.separator + "macros -H " + docDirectory + "HMMs" +  File.separator +
                    "hmm15" +  File.separator + "hmmdefs -C " + docDirectory + "user" +  File.separator + "configLR  -w " + docDirectory + "user" +  File.separator
                    + "wordNetworkNum -o SWT -l '*' -i " + "data" + File.separator + "recout.mlf -p 0.0 -s 5.0  " + docDirectory + "user" +  File.separator +
                    "dictionaryD " + docDirectory + "user" + File.separator + "tiedList data" + File.separator + "audio.wav";
            runInBash(cmd);
            return 0;
        }
    }

    //Helper method for making the process and process builder so commands can be executed in bash
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
            return;
        }
    }

    private void nextQuestion() {
        _iteration+=1;
        numberLbl.setText(_questionMap.get(_iteration));
        scoreLbl.setText("Score: " + _score + "/" + _iteration);
        _attempt = 1;
    }

    private void resetBtns() {
        if (_attempt > 2) {
            nextQuestion();
        }
        skipQuestionBtn.setDisable(false);
        recordBtn.setDisable(false);
        returnMainBtn.setDisable(false);
        progressBar.progressProperty().unbind();
        progressBar.setProgress(0.0);
        attemptLbl.setText("Attempt: " + _attempt);
    }



    /*
    //Helper method to playback audio once they have finished recording
    private void playbackAudio() {
        if (model.getPlaybackEnabled()) {
            File file = new File(System.getProperty("user.dir") + fileSeperator + "data" + fileSeperator + "audio.wav");
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
        }
    }
    */





}
