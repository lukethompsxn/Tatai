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

/**
 * @author Joel Clarke, Luke Thompson
 */
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

    /**
     * This action method changes the the visible scene based on the state of the application. If the current quiz is
     * finished the method will call AbstractController method pushChild() so that the summary view will become visible.
     * Otherwise this method call AbstractControllers popChild() method which will remove the wrong view scene returning
     * the application to the question view.
     */
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

    /**
     * This method is invoked when the "home" button is pressed, it calls pushPopup method from AbstractController
     * which loads the "are you sure" popup.
     */
    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method was overridden in order to intialise certain labels, icons and variables based on the state of the application.
     * As well as this the method also checks that the answer and attempt have the correct Maori spelling so that it can
     * be accurately be displayed to the user.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        finishIcon.setVisible(false);
        redoIcon.setVisible(false);
        nextIcon.setVisible(false);
        String attempt = _modeDirector.getCurrentAttempt();
        String answer = _modeDirector.getCurrentAnswer();

        if (attempt.contains("whaa")) {
            attempt = attempt.replaceAll("whaa", "whā");
        }
        if (answer.contains("whaa")) {
            answer = answer.replaceAll("whaa", "whā");
        }
        if (attempt.contains("maa")) {
            attempt = attempt.replaceAll("maa", "mā");
        }
        if (answer.contains("maa")) {
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
    }
}
