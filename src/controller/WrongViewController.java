package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class WrongViewController extends AbstractController implements Initializable {
    @FXML
    Button btn;

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
        sceneChange("MenuView");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(getSuperAttempts());
        if ((getSuperAttempts() > 1) && _modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 2)) {
            btn.setText("Finish");
        }
        else if ((getSuperAttempts() > 1)) {
            btn.setText("Next Question");
        }
        else {
            btn.setText("Try Again!");
        }
    }
}
