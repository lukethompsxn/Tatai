package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.MathsCollection;
import model.ModeDirector;
import model.NumberCollection;

import java.io.File;
import java.io.IOException;

public class MathViewController extends AbstractController{
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static MathsCollection _mathModel = MathsCollection.instance();

    public void add() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_ADD);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_ADD, false);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void subtract() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_SUB);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_SUB, false);
        _modeDirector.setNumQuestions(10);_modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void multiply() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_MULT);
        _mathModel.arithmetic(10,10,ModeDirector.Mode.MATH_MULT, false);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void divide() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_DIV);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_DIV, false);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void custom() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_CUSTOM);
        _modeDirector.setNumQuestions(10); //WILL NOT BE 10 IT WILL DEPEND ON THE LENGTH OF THE LAST/HASHMAP ETC
        pushChild("CustomView");
    }

    public void random() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_RANDOM);
        _mathModel.randomArithmetic(10);
        _modeDirector.setNumQuestions(10); //WILL NOT BE 10 IT WILL DEPEND ON THE LENGTH OF THE LAST/HASHMAP ETC
        pushChild("QuestionView");
    }

    public void mainMenu() {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stats() {
        pushChild("StatsView");
    }



}
