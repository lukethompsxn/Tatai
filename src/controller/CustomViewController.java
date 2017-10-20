package controller;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import model.CustomCollection;
import model.ModeDirector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomViewController extends AbstractController implements Initializable{
    private CustomCollection _customCollection = CustomCollection.instance();
    private ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    ListView listView;
    @FXML
    Button playBtn;
    @FXML
    Button deleteBtn;
    @FXML
    Label playLbl;
    @FXML
    Label deleteLbl;
    @FXML
    MaterialDesignIconView playIcon;

    public void addQuestions() {
        pushChild("CustomAddView");
    }

    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_CUSTOM);
        _customCollection.setCurrentList(listView.getSelectionModel().getSelectedItem().toString());
        _modeDirector.setNumQuestions(_customCollection.getCurrentQuestionMap().size());
        pushChild("QuestionView");
    }

    public void delete() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(File.separator + "view" + File.separator + "DeletePopup.fxml"));
        loader.setController(new DeletePopupController(listView.getItems(), listView.getSelectionModel().getSelectedItem()));
        try {
            pushPopup(new Scene(loader.load()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().addAll(_customCollection.getStoredNames().toArray());

        //Adds listener for selection events
        listView.getSelectionModel().selectedItemProperty().addListener((obj, before, now) -> {
            if (now != null) {
                playBtn.setDisable(false);
                deleteBtn.setDisable(false);
            } else {
                playBtn.setDisable(true);
                deleteBtn.setDisable(true);
            }
        });

        playBtn.setDisable(true);
        deleteBtn.setDisable(true);
        playLbl.visibleProperty().bind(playBtn.disabledProperty().not());
        deleteLbl.visibleProperty().bind(deleteBtn.disabledProperty().not());
        playIcon.visibleProperty().bind(playBtn.disabledProperty().not());

        customizeListView(listView);
    }
}
