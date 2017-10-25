package controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.Main;
import model.MathsCollection;
import model.ModeDirector;
import model.NumberCollection;
import model.PracticeCollection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuViewController extends AbstractController {
    private static ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    Button mathBtn;
    @FXML
    Button settingsBtn;

    /**
     * This method is the action for the practice button. It first sets the type inside the mode director, then sets
     * up the panes for the transition. After loading the practice view scene, it transitions between the two panes
     * in order to display the practice view.
     */
    public void practiceMenu() {
        _modeDirector.setType(ModeDirector.Type.PRACTICE);
        _mainPane.setTranslateX(-1280);
        _mainPane.setVisible(true);
        pushChild("PracticeView");

        TranslateTransition transitionF = new TranslateTransition(Duration.millis(200), _frontPane);
        transitionF.fromXProperty().set(0);
        transitionF.toXProperty().set(1280);

        TranslateTransition transitionM = new TranslateTransition(Duration.millis(200), _mainPane);
        transitionM.fromXProperty().set(-1270);
        transitionM.toXProperty().set(0);

        transitionF.play();
        transitionM.play();

    }

    //Action for the "info" button. This method calls a method from abstract controller in order to disaply the info popup
    public void info() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "InfoPopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is the action for the math button. It first sets the type inside the mode director, then sets
     * up the panes for the transition. After loading the math view scene, it transitions between the two panes
     * in order to display the math view.
     */
    public void mathMenu() {
        _modeDirector.setType(ModeDirector.Type.MATH);
        _mainPane.setTranslateX(1280);
        _mainPane.setVisible(true);
        pushChild("MathView");

        TranslateTransition transitionF = new TranslateTransition(Duration.millis(200), _frontPane);
        transitionF.fromXProperty().set(0);
        transitionF.toXProperty().set(-1280);

        TranslateTransition transitionM = new TranslateTransition(Duration.millis(200), _mainPane);
        transitionM.fromXProperty().set(1270);
        transitionM.toXProperty().set(0);

        transitionF.play();
        transitionM.play();

    }

    //Action for the settings button. This method calls a method from abstract controller in order to display the settings popup
    public void settings() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "SettingsPopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
