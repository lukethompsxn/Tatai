package controller;

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
        pushChild("PracticeView");
    }

    //Action for math button
    public void mathMenu() {
        _modeDirector.setType(ModeDirector.Type.MATH);
        pushChild("MathView");
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
