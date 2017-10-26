package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class SettingsPopupController extends AbstractController implements Initializable{
    private static ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    private CheckBox playback;

    /**
     * Action for the close button. This method calls a method from abstract controller in order to close the pop up. It
     * then sets the playback enabled field inside the mode director using the setPlaybackEnabled method.
     */
    public void close() {
        closePopup();
        _modeDirector.setPlaybackEnabled(playback.isSelected());
    }

    //This method was overridden in order to retain the users changes regarding enabling/disabling playback
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playback.setSelected(_modeDirector.getPlaybackEnabled());
    }
}
