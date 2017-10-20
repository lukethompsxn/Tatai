package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import model.ModeDirector;
import model.NumberCollection;
import model.PracticeCollection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PracticeViewController extends AbstractController implements Initializable {

    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static PracticeCollection _pracModel = PracticeCollection.instance();

    public void easy() {
        _pracModel.setCollection(10);
        _modeDirector.setMode(ModeDirector.Mode.PRACTICE_EASY);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void hard() {
        _pracModel.setCollection(99);
        _modeDirector.setMode(ModeDirector.Mode.PRACTICE_HARD);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
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
    }
}
