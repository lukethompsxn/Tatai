package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import main.Main;
import model.NumberCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    protected void setFonts(Label l1, int s1, Label l2, int s2, Label l3, int s3, Label l4, int s4, int num) {
        try {
            if (num == 1) {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
            }
            else if (num == 2) {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
                final Font f2 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s2);
                l2.setFont(f2);
            }
            else if (num == 3) {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
                final Font f2 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s2);
                l2.setFont(f2);
                final Font f3 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s3);
                l3.setFont(f3);
            }
            else {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
                final Font f2 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s2);
                l2.setFont(f2);
                final Font f3 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s3);
                l3.setFont(f3);
                final Font f4 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), s4);
                l4.setFont(f4);
            }



        } catch (FileNotFoundException e) {
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
