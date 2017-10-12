package controller;

import model.MathsCollection;
import model.ModeDirector;
import model.NumberCollection;

public class MathViewController extends AbstractController{
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static MathsCollection _mathModel = MathsCollection.instance();

    public void add() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_ADD);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_ADD);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void subtract() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_SUB);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_SUB);
        _modeDirector.setNumQuestions(10);_modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void multiply() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_MULT);
        _mathModel.arithmetic(10,10,ModeDirector.Mode.MATH_MULT);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void divide() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_DIV);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_DIV);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    public void random() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_RANDOM);
        _modeDirector.setNumQuestions(10); //WILL NOT BE 10 IT WILL DEPEND ON THE LENGTH OF THE LAST/HASHMAP ETC
        pushChild("CustomView");
    }



}
