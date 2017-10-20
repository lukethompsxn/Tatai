package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import main.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

public abstract class AbstractController {
    protected static Pane _mainPane;
    protected static Pane _popupPaneContainer;
    protected static Pane _frontPane;
    protected static AnchorPane _popupPane;
    protected static Pane _overlay;
    protected static Stack<Parent> _parentStack = new Stack<>();
    protected static int _attempts;

    //Called from subclasses to set a new scene inside the container
    protected void pushChild(String name) {
        _mainPane.setVisible(true);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(File.separator + "view" + File.separator + name + ".fxml"));
            Parent root = loader.load();
            _parentStack.add(root);
            _mainPane.getChildren().setAll(_parentStack.peek());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void pushPopup(Scene scene, boolean transitions) {
        _popupPaneContainer.setVisible(true);
        _overlay.setVisible(true);

        if(transitions) {
            FadeTransition fadeOverlay = new FadeTransition(Duration.millis(150), _overlay);
            fadeOverlay.setFromValue(0.0);
            fadeOverlay.setToValue(0.6);

            FadeTransition fadePopup = new FadeTransition(Duration.millis(150), _popupPaneContainer);
            fadePopup.setFromValue(0.0);
            fadePopup.setToValue(1);

            ScaleTransition scalePopup = new ScaleTransition(Duration.millis(150), _popupPaneContainer);
            scalePopup.setFromX(0);
            scalePopup.setFromY(0);
            scalePopup.setToX(1);
            scalePopup.setToY(1);


            fadeOverlay.play();
            scalePopup.play();
            fadePopup.play();
        }
        else {
            _overlay.setOpacity(0.6);
            _popupPaneContainer.setOpacity(1);
        }
        _popupPane.getChildren().setAll(scene.getRoot());

    }

    protected void closePopup() {
        _popupPaneContainer.setVisible(false);
        FadeTransition fadeOverlay = new FadeTransition(Duration.millis(100), _overlay);
        fadeOverlay.setFromValue(0.6);
        fadeOverlay.setToValue(0.0);

        FadeTransition fadePopup = new FadeTransition(Duration.millis(150), _popupPaneContainer);
        fadePopup.setFromValue(1);
        fadePopup.setToValue(0);

        fadeOverlay.play();
        fadePopup.play();
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
    /*
    protected void setFonts(Label l1, int s1, Label l2, int s2, Label l3, int s3, Label l4, int s4, int num) {
        try {
            if (num == 1) {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
            }
            else if (num == 2) {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
                final Font f2 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s2);
                l2.setFont(f2);
            }
            else if (num == 3) {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
                final Font f2 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s2);
                l2.setFont(f2);
                final Font f3 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s3);
                l3.setFont(f3);
            }
            else {
                final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s1);
                l1.setFont(f1);
                final Font f2 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s2);
                l2.setFont(f2);
                final Font f3 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s3);
                l3.setFont(f3);
                final Font f4 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), s4);
                l4.setFont(f4);
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    */
    protected void customizeListView(ListView listView) {
        /*listView.setCellFactory((Object cell) -> {
            return new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item);
                        Font f1 = null;
                        try {
                            f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "fonts/MarkerFeltWide.ttf")), 20);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        setFont(f1);
                    }
                    else {
                        setText("");
                    }
                }
            };
        });*/
    }

    protected void setSuperAttempts(int num) {
        _attempts = num;
    }

    protected int getSuperAttempts() {
        return _attempts;
    }
}
