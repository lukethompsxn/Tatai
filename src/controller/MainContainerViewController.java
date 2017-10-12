package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import model.ModeDirector;
import model.NumberCollection;
import model.PracticeCollection;

import java.net.URL;
import java.util.ResourceBundle;

public class MainContainerViewController extends AbstractController implements Initializable{
    private static ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    Pane mainPane;
    @FXML
    Pane menuPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _mainPane = mainPane;
        menuPane.setVisible(false);
        menuPane.disableProperty().bind(menuPane.visibleProperty());

        if (_modeDirector.getType().equals(ModeDirector.Type.PRACTICE)) {
            pushChild("PracticeView");
        }
        else {
            pushChild("MathView");
        }

    }

    public void toggleMenu() {
        menuPane.setVisible(!menuPane.isVisible());
    }
}
