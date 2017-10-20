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

public class MenuViewController extends AbstractController implements Initializable {
    private static ModeDirector _modeDirector = ModeDirector.instance();
    //private static MathsCollection _mathModel = MathsCollection.instance();

    @FXML
    Button mathBtn;
    @FXML
    Button settingsBtn;

    //Action for practice button
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

    public void info() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "InfoPopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Action for math button
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

    public void settings() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "SettingsPopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
