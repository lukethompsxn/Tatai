package controller;

import model.MathsCollection;
import model.ModeDirector;

public class MathViewController extends AbstractController{
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static MathsCollection _mathModel = MathsCollection.instance();

    //JOEL
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

    //Action for the "home" button. This method loads the menu view
    public void mainMenu() {
        pushChild("MenuView");
    }

    //Action for the "stats" button. This method calls a method from abstract controller to set the child to stats view
    public void stats() {
        pushChild("StatsView");
    }



}
