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

public class CorrectViewController extends AbstractController implements Initializable {

    @FXML
    Label lbl;
    @FXML
    private MaterialDesignIconView nextIcon;
    @FXML
    private MaterialDesignIconView finishIcon;

    private static ModeDirector _modeDirector = ModeDirector.instance();

    /**
     * This method decides which scene to load next based on what question the user is currently on. If the user
     * is on the final question of a session it will load the summary view by passing in the string SummaryView to the
     * push child method found within AbstractController.
     */
    public void nextQuestion() {
        if (_modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 1)) {
            _modeDirector.setIteration(0);
            popChild();
            pushChild("SummaryView");
        } else {
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
     * This method was overridden in order to intialise certain labels and icons based on the state of the application,
     * e.g whether or not the user is only the user has finished the last question or not.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextIcon.setVisible(false);
        finishIcon.setVisible(false);

        if (_modeDirector.getIteration() > (_modeDirector.getNumQuestions() - 2)) {
            lbl.setText("Finish");
            finishIcon.setVisible(true);
        } else {
            lbl.setText("Next");
            nextIcon.setVisible(true);
        }
    }
}
