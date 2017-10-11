package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import model.NumberCollection;

import java.net.URL;
import java.util.ResourceBundle;

public class MainContainerViewController extends AbstractController implements Initializable{
    private static NumberCollection _model = NumberCollection.instance();

    @FXML
    Pane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _mainPane = mainPane;

        if (_model.getType() == NumberCollection.Type.RECALL) {
            pushChild("RecallView");
        }
        else {
            pushChild("MathView");
        }

    }
}
