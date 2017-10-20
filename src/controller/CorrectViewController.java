package controller;


import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ModeDirector;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CorrectViewController extends AbstractController implements Initializable {

    @FXML
    private Button nextBtn;
    @FXML
    Label lbl;
    @FXML
    private MaterialDesignIconView nextIcon;
    @FXML
    private MaterialDesignIconView finishIcon;

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
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

        //setFonts(lbl, 82, null, -1, null, -1, null, -1, 1);

    }
}
