package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsPopupController extends AbstractController implements Initializable{
    private static ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    CheckBox playback;

    public void close() {
        closePopup();
        _modeDirector.setPlaybackEnabled(playback.isSelected());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playback.setSelected(_modeDirector.getPlaybackEnabled());
    }
}
