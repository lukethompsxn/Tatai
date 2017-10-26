package controller;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.CustomCollection;
import model.ModeDirector;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class CustomViewController extends AbstractController implements Initializable{
    private CustomCollection _customCollection = CustomCollection.instance();
    private ModeDirector _modeDirector = ModeDirector.instance();

    //List View
    @FXML
    ListView listView;

    //Buttons
    @FXML
    Button playBtn;
    @FXML
    Button deleteBtn;

    //Button Labels
    @FXML
    Label playLbl;
    @FXML
    Label deleteLbl;
    @FXML
    MaterialDesignIconView playIcon;

    //Action for "Add" button which loads the add view
    public void addQuestions() {
        pushChild("CustomAddView");
    }

    //Action for the "Home" button which loads main menu
    public void mainMenu() {
        pushChild("MenuView");
    }

    /**
     * This method is the action for the "play" button. It first sets the mode inside the mode director, the
     * current list to the list which is currently selected in the list view, the number of questions inide the list,
     * and then loads the question view.
     */
    public void play() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_CUSTOM);
        _customCollection.setCurrentList(listView.getSelectionModel().getSelectedItem().toString());
        _modeDirector.setNumQuestions(_customCollection.getCurrentQuestionMap().size());
        pushChild("QuestionView");
    }

    //Action for the "delete" button which loads the delete pop up, passing in the observable list and the selected item
    public void delete() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(File.separator + "view" + File.separator + "DeletePopup.fxml"));
        loader.setController(new DeletePopupController(listView.getItems(), listView.getSelectionModel().getSelectedItem()));
        try {
            pushPopup(new Scene(loader.load()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method was overridden in order to populate the list view upon initlisation, to disable certain buttons
     * until an item in the list view is selected, and to bind the visibility property of the labels to the disabled
     * property of the corresponding buttons.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (_customCollection.getStatus()) {
            try {
                pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "FileErrorPopup.fxml"))), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            listView.getItems().addAll(_customCollection.getStoredNames().toArray());
        }

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


    }
}
