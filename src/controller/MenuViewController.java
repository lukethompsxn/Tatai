package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import main.Main;
import model.NumberCollection;
import model.PracticeCollection;

import java.io.File;
import java.io.IOException;

public class MenuViewController {
    private static NumberCollection _model = PracticeCollection.instance();

    //Action for practice button
    public void practiceMenu() {
        _model.setType(NumberCollection.Type.PRACTICE);
        sceneChange("MainContainerView");
    }

    //Action for math button
    public void mathMenu() {
        _model.setType(NumberCollection.Type.MATH);
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
}
