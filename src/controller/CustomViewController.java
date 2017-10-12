package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import model.CustomCollection;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomViewController extends AbstractController implements Initializable {

    @FXML
    private Button oneBtn;
    @FXML
    private Button twoBtn;
    @FXML
    private Button threeBtn;
    @FXML
    private Button fourBtn;
    @FXML
    private Button fiveBtn;
    @FXML
    private Button sixBtn;
    @FXML
    private Button sevenBtn;
    @FXML
    private Button eightBtn;
    @FXML
    private Button nineBtn;
    @FXML
    private Button zeroBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button subBtn;
    @FXML
    private Button multBtn;
    @FXML
    private Button diviBtn;
    @FXML
    private Button addQuestionBtn;
    @FXML
    private Button finishQuestionsBtn;
    @FXML
    private Label equationLbl;

    private static CustomCollection _customModel = CustomCollection.instance();
    private int _first;
    private int _second;
    private int _answer;
    private String _operator;
    private boolean operatorDone = false;
    private int digitFirst = 1;
    private int digitSecond = 1;
    private int _questionNumber = 0;

    @FXML
    private void operationAction(ActionEvent event1) {
        operatorDone = true;
        operationBtnsCtrl(true);
        numberBtnCtrl(false);

        if (event1.getSource() == addBtn) {
            _operator = " + ";
            equationLbl.setText(equationLbl.getText() + " + ");
        } else if(event1.getSource() == subBtn) {
            _operator = " - ";
            equationLbl.setText(equationLbl.getText() + " - ");
        } else if (event1.getSource() == multBtn) {
            _operator = " x ";
            equationLbl.setText(equationLbl.getText() + " x ");
        } else {
            _operator = " / ";
            equationLbl.setText(equationLbl.getText() + " / ");
        }
    }

    @FXML
    private void addQuestionBtnAction() {
        if (_operator.equals(" + ")) {
            _answer = _first + _second;
        } else if (_operator.equals(" - ")) {
            _answer = _first - _second;
        } else if (_operator.equals(" x ")) {
            _answer = _first * _second;
        } else {
            _answer = _first / _second;
            if (_first % _second != 0) {
                _answer = 0;
            }
        }

        if ((_answer < 100) && (_answer > 0)) {
            _customModel.addCustomQuestion(_questionNumber, _first + _operator + _second, _answer);
            _questionNumber++;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Answer was not greater than zero and less than 100",
                    ButtonType.OK);
            alert.setHeaderText("Answer Error");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait().ifPresent((ButtonType response) -> {
                if (response == ButtonType.OK) {
                }
            });
        }
        operationBtnsCtrl(false);
        numberBtnCtrl(false);
        addQuestionBtn.setDisable(true);
        equationLbl.setText("");
        operatorDone = false;
        digitFirst = 1;
        digitSecond = 1;
        _first = 0;
        _second = 0;
    }

    @FXML
    private void finishQuestionsBtnAction() {
        operationBtnsCtrl(false);
        numberBtnCtrl(false);
        addQuestionBtn.setDisable(true);
        equationLbl.setText("");
        operatorDone = false;
        digitFirst = 1;
        digitSecond = 1;
        _first = 0;
        _second = 0;
        popChild();
        pushChild("QuestionView");
    }

    @FXML
    private void numberSelectAction(ActionEvent event2) {

        if (event2.getSource() == oneBtn) {
            idkmethod(1);
        } else if (event2.getSource() == twoBtn) {
            idkmethod(2);
        } else if (event2.getSource() == threeBtn) {
            idkmethod(3);
        } else if (event2.getSource() == fourBtn) {
            idkmethod(4);
        } else if (event2.getSource() == fiveBtn) {
            idkmethod(5);
        } else if (event2.getSource() == sixBtn) {
            idkmethod(6);
        } else if (event2.getSource() == sevenBtn) {
            idkmethod(7);
        } else if (event2.getSource() == eightBtn) {
            idkmethod(8);
        } else if (event2.getSource() == nineBtn) {
            idkmethod(9);
        } else if (event2.getSource() == zeroBtn) {
            idkmethod(0);
        }

    }

    private void idkmethod(int num) {
        equationLbl.setText(equationLbl.getText() + num);
        if (!operatorDone) {
            if (digitFirst == 1) {
                _first += num;
                digitFirst++;
            } else {
                _first = (_first*10) + num;
                digitFirst--;
                numberBtnCtrl(true);
            }
        } else {
            if (digitSecond == 1) {
                _second += num;
                digitSecond += 1;
                addQuestionBtn.setDisable(false);
            } else {
                _second = (_second*10) + num;
                digitSecond--;
                numberBtnCtrl(true);
            }
        }
    }

    private void operationBtnsCtrl(boolean option) {
        addBtn.setDisable(option);
        subBtn.setDisable(option);
        multBtn.setDisable(option);
        diviBtn.setDisable(option);
    }

    private void numberBtnCtrl(boolean option) {
        oneBtn.setDisable(option);
        twoBtn.setDisable(option);
        threeBtn.setDisable(option);
        fourBtn.setDisable(option);
        fiveBtn.setDisable(option);
        sixBtn.setDisable(option);
        sevenBtn.setDisable(option);
        eightBtn.setDisable(option);
        nineBtn.setDisable(option);
        zeroBtn.setDisable(option);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addQuestionBtn.setDisable(true);
    }
}
