package controller;

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
import java.util.ResourceBundle;

public class CustomAddViewController extends AbstractController implements Initializable {
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static CustomCollection _customModel = CustomCollection.instance();

    private int _first=0;
    private int _second=0;
    private int _answer;

    private String _operator;
    private boolean operatorDone = false;

    private int _digitFirst = 1;
    private int _digitSecond = 1;
    private int _questionNumber = 0;

    //Buttons
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

    //Regular Labels
    @FXML
    private Label equationLbl;
    @FXML
    private Label questionsAddedLbl;

    //List View
    @FXML
    private ListView listView;

    //Button Labels
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

    /**
     * This is the action that occurs when an operator button has been pressed. It disables all operators buttons,
     * adds the operator that was selected to visible equation label and also sets the instance variable _operator to
     * the operator selected.
     * @param event1
     */
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

    /**
     * This action method carries out the necessary operations to add a question once it has been completed and the
     * "add" button has been pressed. The method first sets the instance variable _answer to the answer of the combination
     * of _first and _second numbers along with the _operator. It then checks if the answer is in the allowed range, if
     * so the question along with the answer is stored using a method from CustomCollection. Note that is should not
     * be possible for the answer to not be not in the allowed range and the check is only for safety purposes.
     * Lastly the method will reset all buttons and labels so that a new equation can be entered.
     */
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

        // Check is see if answer is in allowed range
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

        // Resetting buttons/labels/variables
        numberBtnCtrl(false);
        addQuestionBtn.setDisable(true);
        equationLbl.setText("");
        operatorDone = false;
        _digitFirst = 1;
        _digitSecond = 1;
        _first = 0;
        _second = 0;
    }

    /**
     * This action method carries out the processes needed to complete the addition of a custom list of questions when
     * the "finish" button is pressed. It firstly resets certain buttons/labels/variables and then calls a method
     * from AbstractController in order to display a popup that allows for naming of the custom list.
     */
    @FXML
    private void finishQuestionsBtnAction() {
        _modeDirector.setNumQuestions(_questionNumber);

        // Resetting various buttons/labels/variables
        numberBtnCtrl(false);
        addQuestionBtn.setDisable(true);
        equationLbl.setText("");
        operatorDone = false;
        _digitFirst = 1;
        _digitSecond = 1;
        _questionNumber = 0;
        _first = 0;
        _second = 0;

        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "EnterNamePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This action method is called when the "clear" button is pressed, it resets all buttons and labels relating to
     * adding a new question to the custom list but still keeping the questions that have already been made.
     */
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
        _digitFirst = 1;
        _digitSecond = 1;
        _first = 0;
        _second = 0;
    }

    /**
     * This action method is invoked whenever one of the number buttons has been pressed. Based on the specific number
     * that has been pressed the method formingDigits is called with the button number being passed in as a parameter.
     * @param event2
     */
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

    /**
     * This method takes an int called "num" as a parameter and based on the state of the view will either add it to the instance
     * variable _first or _second representing the first and second integer of the custom equation being added.
     * The method first checks if the operator has been selected, if not it is adding to _first otherwise adding to
     * _second. The method then checks if _first or _second respectively have already had a number added to the field,
     * if not the number input num is simply added to the instance variable and otherwise the instance variable is
     * multiplied by 10 and then the input variable is added.
     * @param num
     */
    private void formingDigits(int num) {
        equationLbl.setText(equationLbl.getText() + num);

        // Checks if operator has been selected, deals with _first variable
        if (!operatorDone) {

            // Checks if it the first digit being added to the variable _first
            if (_digitFirst == 1) {
                _first += num;
                _digitFirst++;
                operationBtnsCtrl(false);

                // Special functionality required for the zero button
                if (num == 0) {
                    diviBtn.setDisable(true);
                    multBtn.setDisable(true);
                    subBtn.setDisable(true);
                }
            } else {
                _first = (_first*10) + num;
                _digitFirst--;
                numberBtnCtrl(true);
                diviBtn.setDisable(false);
                multBtn.setDisable(false);
                subBtn.setDisable(false);

                // Special functionality required for the zero button.
                if (num == 0) {
                    diviBtn.setDisable(true);
                    multBtn.setDisable(true);
                    subBtn.setDisable(true);
                }
            }
        }

        // Same as above but dealing with _second variable
        else {
            if (_digitSecond == 1) {
                _second += num;
                _digitSecond += 1;
                addQuestionBtn.setDisable(false);
                possibleNumberDisable();

                if ((_operator.equals(" x ") && ((_first * ((_second*10) + 0) > 0) && (_first * ((_second*10) + 0) < 100)))) {
                    zeroBtn.setDisable(false);
                }
                if (_operator.equals(" + ")) {
                    zeroBtn.setDisable(false);
                }
            } else {
                _second = (_second*10) + num;
                _digitSecond--;
                numberBtnCtrl(true);
            }
        }
    }

    /**
     * This method goes through each of the number buttons and checks if they need to be disabled by calling the
     * local needDisable method. If the method returns true the respective button is disabled.
     */
    private void possibleNumberDisable() {
        if (needDisable(0)) {
            zeroBtn.setDisable(true);
        }
        if (needDisable(1)) {
            oneBtn.setDisable(true);
        }
        if (needDisable(2)) {
            twoBtn.setDisable(true);
        }
        if (needDisable(3)) {
            threeBtn.setDisable(true);
        }
        if (needDisable(4)) {
            fourBtn.setDisable(true);
        }
        if (needDisable(5)) {
            fiveBtn.setDisable(true);
        }
        if (needDisable(6)) {
            sixBtn.setDisable(true);
        }
        if (needDisable(7)) {
            sevenBtn.setDisable(true);
        }
        if (needDisable(8)) {
            eightBtn.setDisable(true);
        }
        if (needDisable(9)) {
            nineBtn.setDisable(true);
        }
    }

    /**
     * This method takes a parameter of type int called btnNumber and using this decides whether or not the button needs
     * to be disabled, if so the method returns true otherwise returns false. This is done my checking first if
     * the second number has already been given a value (i.e. start and _second > 0), then from this the method
     * checks all of the possible equations that could be made with the input button and along with the instance
     * variable _first.
     * @param btnNumber
     * @return boolean
     */
    private boolean needDisable(int btnNumber) {

        // The _second number has already has been given a value
        if (_second > 0) {
            // add
            if (_operator.equals(" + ")) {
                if (((_first + ((_second*10) + btnNumber)) <= 0) || ((_first + ((_second*10) + btnNumber)) > 99)) {
                    return true;
                }
            }
            // subtraction
            else if (_operator.equals(" - ")) {
                if (((_first - ((_second*10) + btnNumber)) <= 0) || ((_first - ((_second*10) + btnNumber)) > 99)) {
                    return true;
                }
            }
            // multiplication
            else if (_operator.equals(" x ")) {
                if (((_first * ((_second*10) + btnNumber)) <= 0) || ((_first * ((_second*10) + btnNumber)) > 99)) {
                    return true;
                }
            }
            // Have only allowed division of single digits for simplicity, hence always returns true
            else  {
                numberBtnCtrl(true);
            }
        }
        // The _second number has not been assigned a value
        else {
            // add
            if (_operator.equals(" + ")) {
                if (((_first + btnNumber) <= 0) || ((_first + btnNumber) > 99)) {
                    return true;
                }
            }
            // subtraction
            else if (_operator.equals(" - ")) {
                if (((_first - btnNumber) <= 0) || ((_first - btnNumber) > 99)) {
                    return true;
                }
            }
            // multiplication
            else if (_operator.equals(" x ")) {
                if (((_first * btnNumber) <= 0) || ((_first * btnNumber) > 99)) {
                    return true;
                }
            }
            // division
            else  {
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

    /**
     * This method controls all the operator buttons and either enables or disables them based on the boolean input
     * "option".
     * @param option
     */
    private void operationBtnsCtrl(boolean option) {
        addBtn.setDisable(option);
        subBtn.setDisable(option);
        multBtn.setDisable(option);
        diviBtn.setDisable(option);
    }

    /**
     * This method is invoked when the "home" button is pressed, it calls pushPopup method from AbstractController
     * which loads the "are you sure" popup.
     */
    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will control all of the the number buttons for 0-9, based on the boolean input the method will either
     * disable all of the buttons or enable them all.
     * @param option
     */
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

    /**
     * This method was overridden in order to disable some buttons upon intialisation, to bind the visible property
     * of all the button labels to the buttons disabled property (so they can loook "clickable" and "not clickable"
     * depending on whether the button is disabled) and to load the parent help pop up.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        operationBtnsCtrl(true);
        addQuestionBtn.setDisable(true);
        finishQuestionsBtn.setDisable(true);

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
