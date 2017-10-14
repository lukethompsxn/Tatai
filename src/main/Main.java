package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CustomCollection;

import java.io.File;
import java.util.Stack;

public class Main extends Application {
    private static Stage _primaryStage;
    private static Stack<Scene> _sceneStack = new Stack<>();
    private static CustomCollection _customCollection = CustomCollection.instance();

    @Override
    public void start(Stage primaryStage) throws Exception{
        _primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource(File.separator  + "view" + File.separator + "MenuView.fxml"));
        primaryStage.setTitle("Tātai");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        if (!(new File(System.getProperty("user.dir") + File.separator + "data").exists())) {
            File dir = new File(System.getProperty("user.dir") + File.separator + "data");
            dir.mkdir();
        }
        _customCollection.importCustomLists();
        launch(args);
    }

    //Called when adding a new scene to the stack and setting it
    public static void pushScene(Scene scene) {
        _sceneStack.add(scene);
        _primaryStage.setScene(_sceneStack.peek());
    }

    //Called when popping the top scene to return to the previous scene
    public static void popScene() {
        _sceneStack.pop();
        _primaryStage.setScene(_sceneStack.peek());
    }
}


