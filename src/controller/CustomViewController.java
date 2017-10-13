package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.CustomCollection;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomViewController extends AbstractController implements Initializable{
    private CustomCollection _customCollection = CustomCollection.instance();

    @FXML
    ListView listView;

    public void addQuestions() {
        pushChild("CustomAddView");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().addAll(_customCollection.getStoredNames().toArray());
    }
}
