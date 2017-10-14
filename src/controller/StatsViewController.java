package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class StatsViewController extends AbstractController implements Initializable {
    private static ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    Label addLbl;
    @FXML
    Label subLbl;
    @FXML
    Label multLbl;
    @FXML
    Label divLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addLbl.setText("Addition: " + _modeDirector.getStats(ModeDirector.Mode.MATH_ADD) + "% Accuracy");
        subLbl.setText("Subtraction: " + _modeDirector.getStats(ModeDirector.Mode.MATH_SUB) + "% Accuracy");
        multLbl.setText("Multiplication: " + _modeDirector.getStats(ModeDirector.Mode.MATH_MULT) + "% Accuracy");
        divLbl.setText("Division: " + _modeDirector.getStats(ModeDirector.Mode.MATH_DIV) + "% Accuracy");
    }

    public void returnToMathView() {
        popChild();
    }
}
