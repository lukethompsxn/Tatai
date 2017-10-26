package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class MainContainerViewController extends AbstractController implements Initializable{
    @FXML
    Pane mainPane;
    @FXML
    Pane popupPaneContainer;
    @FXML
    AnchorPane popupPane;
    @FXML
    Pane overlay;
    @FXML
    Pane frontPane;

    /**
     * This method has been overridden in order to set the static panes from abstract controller to the panes of the
     * this class. These are then used in methods inside abstract controller which are called from its subclasses from
     * various uses. This method also loads by default the menu view and sets it to the front pane, this allows for
     * transitions between the panes.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _mainPane = mainPane;
        _popupPaneContainer = popupPaneContainer;
        _popupPane = popupPane;
        _overlay = overlay;
        _frontPane = frontPane;

        try {
            _frontPane.getChildren().add(new Scene(FXMLLoader.load(getClass().getResource("/view/MenuView.fxml"))).getRoot());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
