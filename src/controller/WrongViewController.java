package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class WrongViewController extends AbstractController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private Label answerLbl;

    private static ModeDirector _modeDirector = ModeDirector.instance();

    public void btnAction() {
        if (_modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 1)) {
            _modeDirector.setIteration(0);
            popChild();
            pushChild("SummaryView");
        }
        else {
            popChild();
        }
        //other logic
    }

    public void mainMenu() {
        pushChild("MenuView");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        answerLbl.setText("You said " + _modeDirector.getCurrentAttempt());
        if ((getSuperAttempts() > 1) && _modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 2)) {
            btn.setText("Finish");
            answerLbl.setText("You said " + _modeDirector.getCurrentAttempt() + ", the answer was " + _modeDirector.getCurrentAnswer());
        }
        else if ((getSuperAttempts() > 1)) {
            btn.setText("Next Question");
            answerLbl.setText("You said " + _modeDirector.getCurrentAttempt() + ", the answer was " + _modeDirector.getCurrentAnswer());
        }
        else {
            btn.setText("Try Again!");
        }
    }
}
