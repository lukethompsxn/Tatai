package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class WrongViewController extends AbstractController implements Initializable {
    @FXML
    Button btn;

    public void btnAction() {
        popChild();
        //other logic
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(getSuperAttempts());
        if (getSuperAttempts() > 1) {
            btn.setText("Next Question");
        }
        else {
            btn.setText("Try Again!");
        }
    }
}
