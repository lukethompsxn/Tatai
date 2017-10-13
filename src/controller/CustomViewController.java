package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomViewController extends AbstractController implements Initializable{

    @FXML
    ListView listView;

    public void addQuestions() {
        pushChild("CustomAddView");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
