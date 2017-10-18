package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import model.CustomCollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnterNamePopupController extends AbstractController implements Initializable {
    private static CustomCollection _customModel = CustomCollection.instance();

    @FXML
    TextField textField;
    @FXML
    Label error;

    public void close() {
        if (!textField.getText().isEmpty()) {
            _customModel.configureAdd(textField.getText());
            closePopup();
            popChild();
        }
        error.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        error.setVisible(false);
        try {
            final Font f1 = Font.loadFont(new FileInputStream(new File("fonts" + File.separator + "MarkerFeltWide.ttf")), 20);
            textField.setFont(f1);
            error.setFont(f1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
