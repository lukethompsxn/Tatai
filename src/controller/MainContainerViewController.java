package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.ModeDirector;
import model.NumberCollection;
import model.PracticeCollection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainContainerViewController extends AbstractController implements Initializable{
    @FXML
    Pane mainPane;
    @FXML
    Pane popupPaneContainer;
    @FXML
    AnchorPane popupPane;
    @FXML
    Pane overlay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _mainPane = mainPane;
        _popupPaneContainer = popupPaneContainer;
        _popupPane = popupPane;
        _overlay = overlay;

        pushChild("MenuView");

    }

}
