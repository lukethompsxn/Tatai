package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class CorrectViewController extends AbstractController implements Initializable {

    @FXML
    private Button nextBtn;

    private static ModeDirector _modeDirector = ModeDirector.instance();

    public void nextQuestion() {
        if (_modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 1)) {
            _modeDirector.setIteration(0);
            popChild();
            pushChild("SummaryView");
        } else {
            popChild();
        }
    }

    public void mainMenu() {
        sceneChange("MenuView");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (_modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 2)) {
            nextBtn.setText("Finish");
        } else {
            nextBtn.setText("Next Question");
        }
    }
}
