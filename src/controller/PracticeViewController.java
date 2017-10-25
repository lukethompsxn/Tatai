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

public class PracticeViewController extends AbstractController {
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static PracticeCollection _pracModel = PracticeCollection.instance();

    /**
     * This method is the action for the easy button. It first sets the collection inside the practice model, passing
     * in the number largest number as the parameter. It then sets the mode and number of questions inside the mode
     * director, before calling a method from abstract controller to child to the question view.
     */
    public void easy() {
        _pracModel.setCollection(10);
        _modeDirector.setMode(ModeDirector.Mode.PRACTICE_EASY);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    /**
     * This method is the action for the hard button. It first sets the collection inside the practice model, passing
     * in the number largest number as the parameter. It then sets the mode and number of questions inside the mode
     * director, before calling a method from abstract controller to child to the question view.
     */
    public void hard() {
        _pracModel.setCollection(99);
        _modeDirector.setMode(ModeDirector.Mode.PRACTICE_HARD);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    //Action for the "home" button. This method calls a method from abstract controller to load the "are you sure" pop up
    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
