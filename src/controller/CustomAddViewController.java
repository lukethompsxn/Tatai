package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.CustomCollection;
import model.ModeDirector;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomAddViewController extends AbstractController implements Initializable {

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
    private Button clearBtn;
    @FXML
    private Label equationLbl;
    @FXML
    private Label questionsAddedLbl;
    @FXML
    private ListView listView;
    @FXML
    private Label zero;
    @FXML
    private Label one;
    @FXML
    private Label two;
    @FXML
    private Label three;
    @FXML
    private Label four;
    @FXML
    private Label five;
    @FXML
    private Label six;
    @FXML
    private Label seven;
    @FXML
    private Label eight;
    @FXML
    private Label nine;
    @FXML
    private Label plus;
    @FXML
    private Label minus;
    @FXML
    private Label multiply;
    @FXML
    private Label divide;
    @FXML
    private Label add;
    @FXML
    private Label finish;



    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static CustomCollection _customModel = CustomCollection.instance();
    private int _first=0;
    private int _second=0;
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

        possibleNumberDisable();
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
            questionsAddedLbl.setText("Questions Added: " + _questionNumber);
            finishQuestionsBtn.setDisable(false);
            listView.getItems().add(_first + " " + _operator + " " + _second);
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
        //operationBtnsCtrl(false);
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
        _modeDirector.setNumQuestions(_questionNumber);
        //operationBtnsCtrl(false);
        numberBtnCtrl(false);
        addQuestionBtn.setDisable(true);
        equationLbl.setText("");
        operatorDone = false;
        digitFirst = 1;
        digitSecond = 1;
        _questionNumber = 0;
        _first = 0;
        _second = 0;

        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "EnterNamePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearBtnAction() {
        if (_questionNumber > 0) {
            finishQuestionsBtn.setDisable(false);
        }
        numberBtnCtrl(false);
        operationBtnsCtrl(true);
        addQuestionBtn.setDisable(true);
        equationLbl.setText("");
        operatorDone = false;
        digitFirst = 1;
        digitSecond = 1;
        _first = 0;
        _second = 0;
    }

    @FXML
    private void numberSelectAction(ActionEvent event2) {
        finishQuestionsBtn.setDisable(true);

        if (event2.getSource() == oneBtn) {
            formingDigits(1);
        } else if (event2.getSource() == twoBtn) {
            formingDigits(2);
        } else if (event2.getSource() == threeBtn) {
            formingDigits(3);
        } else if (event2.getSource() == fourBtn) {
            formingDigits(4);
        } else if (event2.getSource() == fiveBtn) {
            formingDigits(5);
        } else if (event2.getSource() == sixBtn) {
            formingDigits(6);
        } else if (event2.getSource() == sevenBtn) {
            formingDigits(7);
        } else if (event2.getSource() == eightBtn) {
            formingDigits(8);
        } else if (event2.getSource() == nineBtn) {
            formingDigits(9);
        } else if (event2.getSource() == zeroBtn) {
            formingDigits(0);
        }

    }

    private void formingDigits(int num) {
        equationLbl.setText(equationLbl.getText() + num);
        if (!operatorDone) {
            if (digitFirst == 1) {
                _first += num;
                digitFirst++;
                operationBtnsCtrl(false);
                if (num == 0) {
                    diviBtn.setDisable(true);
                    multBtn.setDisable(true);
                    subBtn.setDisable(true);
                }
            } else {
                _first = (_first*10) + num;
                digitFirst--;
                numberBtnCtrl(true);
                diviBtn.setDisable(false);
                multBtn.setDisable(false);
                subBtn.setDisable(false);
            }
        } else {
            if (digitSecond == 1) {
                _second += num;
                digitSecond += 1;
                addQuestionBtn.setDisable(false);
                possibleNumberDisable();
                if ((_operator.equals(" x ") && ((_first * ((_second*10) + 0) > 0) && (_first * ((_second*10) + 0) < 100)))) {
                    zeroBtn.setDisable(false);
                }
            } else {
                _second = (_second*10) + num;
                digitSecond--;
                numberBtnCtrl(true);
            }
        }
    }

    private void possibleNumberDisable() {
        if (needDisbale(0)) {
            zeroBtn.setDisable(true);
        }
        if (needDisbale(1)) {
            oneBtn.setDisable(true);
        }
        if (needDisbale(2)) {
            twoBtn.setDisable(true);
        }
        if (needDisbale(3)) {
            threeBtn.setDisable(true);
        }
        if (needDisbale(4)) {
            fourBtn.setDisable(true);
        }
        if (needDisbale(5)) {
            fiveBtn.setDisable(true);
        }
        if (needDisbale(6)) {
            sixBtn.setDisable(true);
        }
        if (needDisbale(7)) {
            sevenBtn.setDisable(true);
        }
        if (needDisbale(8)) {
            eightBtn.setDisable(true);
        }
        if (needDisbale(9)) {
            nineBtn.setDisable(true);
        }
    }

    private boolean needDisbale(int btnNumber) {
        if (_second > 0) {
            if (_operator.equals(" + ")) {
                if (((_first + ((_second*10) + btnNumber)) <= 0) || ((_first + ((_second*10) + btnNumber)) > 99)) {
                    return true;
                }
            } else if (_operator.equals(" - ")) {
                if (((_first - ((_second*10) + btnNumber)) <= 0) || ((_first - ((_second*10) + btnNumber)) > 99)) {
                    return true;
                }
            } else if (_operator.equals(" x ")) {
                if (((_first * ((_second*10) + btnNumber)) <= 0) || ((_first * ((_second*10) + btnNumber)) > 99)) {
                    return true;
                }
            } else  {
                //if (_first / ((_second*10) + btnNumber) != 0) {
                //    return true;
                //}
                numberBtnCtrl(true);
            }
        }
        else {
            if (_operator.equals(" + ")) {
                if (((_first + btnNumber) <= 0) || ((_first + btnNumber) > 99)) {
                    return true;
                }
            } else if (_operator.equals(" - ")) {
                if (((_first - btnNumber) <= 0) || ((_first - btnNumber) > 99)) {
                    return true;
                }
            } else if (_operator.equals(" x ")) {
                if (((_first * btnNumber) <= 0) || ((_first * btnNumber) > 99)) {
                    return true;
                }
            } else  {
                if (btnNumber == 0) {
                    return true;
                }
                if (_first % btnNumber != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void operationBtnsCtrl(boolean option) {
        addBtn.setDisable(option);
        subBtn.setDisable(option);
        multBtn.setDisable(option);
        diviBtn.setDisable(option);
    }

    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        operationBtnsCtrl(true);
        addQuestionBtn.setDisable(true);
        finishQuestionsBtn.setDisable(true);
        customizeListView(listView);

        zero.visibleProperty().bind(zeroBtn.disabledProperty().not());
        one.visibleProperty().bind(oneBtn.disabledProperty().not());
        two.visibleProperty().bind(twoBtn.disabledProperty().not());
        three.visibleProperty().bind(threeBtn.disabledProperty().not());
        four.visibleProperty().bind(fourBtn.disabledProperty().not());
        five.visibleProperty().bind(fiveBtn.disabledProperty().not());
        six.visibleProperty().bind(sixBtn.disabledProperty().not());
        seven.visibleProperty().bind(sevenBtn.disabledProperty().not());
        eight.visibleProperty().bind(eightBtn.disabledProperty().not());
        nine.visibleProperty().bind(nineBtn.disabledProperty().not());
        plus.visibleProperty().bind(addBtn.disabledProperty().not());
        minus.visibleProperty().bind(subBtn.disabledProperty().not());
        multiply.visibleProperty().bind(multBtn.disabledProperty().not());
        divide.visibleProperty().bind(diviBtn.disabledProperty().not());
        add.visibleProperty().bind(addQuestionBtn.disabledProperty().not());
        finish.visibleProperty().bind(finishQuestionsBtn.disabledProperty().not());

        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "ParentsHelpPopup.fxml"))), false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
