package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import main.Main;
import model.NumberCollection;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

public abstract class AbstractController {
    protected static Pane _mainPane;
    protected static Stack<Parent> _parentStack = new Stack<>();
    protected static int _attempts;

    //Called from subclasses to set a new scene inside the container
    protected void pushChild(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(File.separator + "view" + File.separator + name + ".fxml"));
            Parent root = loader.load();
            _parentStack.add(root);
            _mainPane.getChildren().setAll(_parentStack.peek());
            //_mainPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Called from subclasses to return to the previous scene inside the container
    protected void popChild() {
        _parentStack.pop();
        _mainPane.getChildren().setAll(_parentStack.peek());
    }

    //Helper method for changing the scene, takes a string of the scene name as a parameter
    protected void sceneChange(String scene) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource( File.separator + "view" + File.separator + scene + ".fxml"));
            Main.pushScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void setSuperAttempts(int num) {
        _attempts = num;
    }

    protected int getSuperAttempts() {
        return _attempts;
    }
}
