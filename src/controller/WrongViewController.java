package controller;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.ModeDirector;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WrongViewController extends AbstractController implements Initializable {

    // Labels
    @FXML
    private Label answerLbl;
    @FXML
    private Label lbl;

    // Icons
    @FXML
    private MaterialDesignIconView nextIcon;
    @FXML
    private MaterialDesignIconView redoIcon;
    @FXML
    private MaterialDesignIconView finishIcon;

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
    }

    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        finishIcon.setVisible(false);
        redoIcon.setVisible(false);
        nextIcon.setVisible(false);
        String attempt = _modeDirector.getCurrentAttempt();
        String answer = _modeDirector.getCurrentAnswer();

        if (attempt.indexOf("whaa") != -1) {
            attempt = attempt.replaceAll("whaa", "whā");
        }
        if (answer.indexOf("whaa") != -1) {
            answer = answer.replaceAll("whaa", "whā");
        }
        if (attempt.indexOf("maa") != -1) {
            attempt = attempt.replaceAll("maa", "mā");
        }
        if (answer.indexOf("maa") != -1) {
            answer = answer.replaceAll("maa", "mā");
        }

        answerLbl.setText("You said " + attempt);
        if ((getSuperAttempts() > 1) && _modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 2)) {
            lbl.setText("Finish");
            answerLbl.setText("You said " + attempt + ", the answer was " + answer);
            finishIcon.setVisible(true);
        }
        else if ((getSuperAttempts() > 1)) {
            lbl.setText("Next");
            answerLbl.setText("You said " + attempt + ", the answer was " + answer);
            nextIcon.setVisible(true);
        }
        else {
            lbl.setText("Try Again!");
            redoIcon.setVisible(true);
        }

        //setFonts(lbl, 65, answerLbl, 30, null, -1, null, -1, 2);
    }
}
