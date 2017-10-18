package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class CorrectViewController extends AbstractController implements Initializable {

    @FXML
    private Button nextBtn;
    @FXML
    Label lbl;

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
        pushChild("MenuView");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (_modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 2)) {
            lbl.setText("Finish");
        } else {
            lbl.setText("Next");
        }

        setFonts(lbl, 82, null, -1, null, -1, null, -1, 1);
    }
}
