package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import model.AudioDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionViewController extends AbstractController implements Initializable{
    private int _iteration = 0;
    private int _score = 0;
    private boolean _attempted = false;
    private static AudioDirector _audioDirector = AudioDirector.instance();

    @FXML
    private Label numberLbl;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button recordBtn;


    //Action for record button
    public void record() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recordBtn.setDisable(false);

        scoreLbl.setText("Score: " + _score + "/" + _iteration);
        if (_attempted) {
            attemptLbl.setText("Attempt: " + 2);
        }
        else {
            attemptLbl.setText("Attempt: " + 1);
        }
        generateNumber();
        _iteration++;
    }

    // Returns to main menu when button is pressed.
    public void returnToMain() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setContentText("Are you sure you want to return to main menu?");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        ButtonType yes = new ButtonType("Yes");
        ButtonType cancel = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(yes, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            if (model.getAttempted() == false) {
                model.setIteration(model.getIteration() - 1);
            }
            model.updateStats();
            model.setIteration(0);
            model.setScore(0);
            model.setAttempted(false);
            sceneChange("MainView");

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
            if (model.getIteration() > 9) {
                model.updateStats();
                model.setIteration(0);
                model.setAttempted(false);
                sceneChange("SummaryView");
            } else {
                model.setAttempted(false);
                sceneChange("QuestionView");
            }

        }
    }

    //Extends Task to perform work on a worker thread for recording the audio
    class AudioInBackground extends Task<Integer> {

        @Override
        protected Integer call() throws Exception {
            String cmd = "ffmpeg -nostats -loglevel 0 -f alsa -i default -t 3 -acodec pcm_s16le -ar 22050 -ac 1 -y  data" + fileSeperator + "audio.wav";
            runInBash(cmd);
            return 0;
        }
    }

    // Extends Task to perform work on a worker thread to carry out voice recognition


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
            root = FXMLLoader.load(getClass().getResource( fileSeperator + "View" + fileSeperator + scene + ".fxml"));
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
            playbackAudio();

            VoiceRecognitionInBackground recognition = new VoiceRecognitionInBackground();
            recognition.setOnSucceeded((WorkerStateEvent revent) -> {
                try {
                    model.setRecordingResult(readResults());
                    if (model.getRecordingResult().equals(model.returnMaoriName(model.getCurrentNumber()))) {
                        model.setScore(model.getScore()+1);
                        sceneChange("CorrectView");
                    }
                    else {
                        sceneChange("WrongView");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

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

    //Helper method for making the process and process builder so commands can be executed in bash
    private void runInBash(String cmd) {
        Process process;
        ProcessBuilder processBuilder = new ProcessBuilder(fileSeperator + "bin" + fileSeperator + "bash", "-c", cmd);
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

    //Helper method to playback audio once they have finished recording
    private void playbackAudio() {
        if (model.getPlaybackEnabled()) {
            File file = new File(System.getProperty("user.dir") + fileSeperator + "data" + fileSeperator + "audio.wav");
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
        }
    }





}
