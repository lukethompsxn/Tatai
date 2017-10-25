package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    /**
     * Loads a new scene based on the input parameter, then sets add this scene to the top of the stack. The children of
     * the main pane is set to the top of the stack. This is used for setting the scene on the main pane. This method is
     * called from the subclasses of abstract controller in order to set the visible scene.
     * @param name
     */
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

    /**
     * This method is used to return to the previous scene. This method is called from the subclasses of abstract
     * controller. It takes no parameters. The top scene on the stack is "popped" and then the scene at the top of
     * the stack is set as the child to the main pane. This determines what is visisble on the screen.
     */
    protected void popChild() {
        _parentStack.pop();
        _mainPane.getChildren().setAll(_parentStack.peek());
    }

    /**
     * This method is used to load and display pop ups, it is called from the sub classes of abstract controller.
     * Takes a scene and boolean as parameters, then sets the pop up pane (container) and darkned fade overlay to be
     * visible. If transitions are enabled, then the overlay pane and pop up pane opacity is transitioned in, and the
     * pop up pane's scale is also transitioned in. If transitions are not enabled then the overlay pane, and pop up
     * container pane are instantly set to their values, instead of transitioning in.
     * @param scene
     * @param transitions
     */
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

    /**
     * This method is used to close the current pop up on screen. It is called from subclasses of abstract controller.
     * It takes no parameters and reverse transitions the opacity of both the overlay pane and the pop up container.
     */
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

    /**
     * This method is used to set the number of attempts stored inside abstract controller. This method is used for
     * setting the the number of attempts from question view controller, so the getter can get the number of attempts
     * inside wrong view controller.
     */
    protected void setSuperAttempts(int num) {
        _attempts = num;
    }

    /**
     * This method is used to get the number of attempts stored inside the abstract controller. This method is used for
     * getting the number of attempts from the wrong view controller in order to determine which label to display.
     * @return
     */
    protected int getSuperAttempts() {
        return _attempts;
    }
}
