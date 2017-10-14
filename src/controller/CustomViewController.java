package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.CustomCollection;
import model.ModeDirector;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomViewController extends AbstractController implements Initializable{
    private CustomCollection _customCollection = CustomCollection.instance();
    private ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    ListView listView;

    public void addQuestions() {
        pushChild("CustomAddView");
    }


    public void play() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_CUSTOM);
        _customCollection.setCurrentList(listView.getSelectionModel().getSelectedItem().toString());
        _modeDirector.setNumQuestions(_customCollection.getCurrentQuestionMap().size());
        pushChild("QuestionView");
    }

    public void delete() {
        _customCollection.deleteCustomList(listView.getSelectionModel().getSelectedItem().toString());
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem().toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _customCollection.importCustomLists();
        listView.getItems().addAll(_customCollection.getStoredNames().toArray());
    }
}
