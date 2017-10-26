package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.CustomCollection;

import java.net.URL;
import java.util.ResourceBundle;

public class EnterNamePopupController extends AbstractController implements Initializable {
    private static CustomCollection _customModel = CustomCollection.instance();

    @FXML
    TextField textField;
    @FXML
    Label error;

    /**
     * This method is the action for the submit button. As this is a text entry, multiple checks are conducted on
     * the text field in order to ensure that the user has enetered a name, and that the name does not aleady exist.
     * If either of those occur, a label is updated prompting the user of the error, otherwise the pop up is closed,
     * the list is added to the stored lists file, and the view is changed to the custom view.
     */
    public void close() {
        if (textField.getText().trim().isEmpty()) {
            textField.clear();
            error.setVisible(true);
            error.setText("Error: You must enter a name");
        }
        else if (_customModel.getStoredNames().contains(textField.getText().trim())) {
            error.setVisible(true);
            error.setText("Error: This list already exists");
        }
        else {
            _customModel.configureAdd(textField.getText());
            closePopup();
            popChild();
            pushChild("CustomView");
        }

    }

    //This method was overridden in order to set the visibility of the error label to false on initialisation
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setVisible(false);
    }
}
