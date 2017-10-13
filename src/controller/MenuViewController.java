package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import main.Main;
import model.MathsCollection;
import model.ModeDirector;
import model.NumberCollection;
import model.PracticeCollection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuViewController implements Initializable {
    private static ModeDirector _modeDirector = ModeDirector.instance();
    //private static MathsCollection _mathModel = MathsCollection.instance();

    @FXML
    Button mathBtn;

    //Action for practice button
    public void practiceMenu() {
        _modeDirector.setType(ModeDirector.Type.PRACTICE);
        sceneChange("MainContainerView");
    }

    //Action for math button
    public void mathMenu() {
        _modeDirector.setType(ModeDirector.Type.MATH);
        sceneChange("MainContainerView");
    }

    public void settings() {
        //pop out from side to display settings menu
    }

    //Helper method for changing the scene, takes a string of the scene name as a parameter
    private void sceneChange(String scene) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource( File.separator + "view" + File.separator + scene + ".fxml"));
            Main.pushScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
