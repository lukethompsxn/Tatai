package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class SummaryViewController extends AbstractController implements Initializable {

    @FXML
    private Button returnBtn;
    @FXML
    private Label scoreLbl;

    private static ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    private void returnBtnAction() {
        _modeDirector.setIteration(0);
        popChild();
        popChild();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scoreLbl.setText("You scored " + _modeDirector.getScore() + "/" + _modeDirector.getNumQuestions());
    }
}
