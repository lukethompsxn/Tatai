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
        pushChild("QuestionView");
    }

    public void subtract() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_SUB);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_SUB);
        pushChild("QuestionView");
    }

    public void multiply() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_MULT);
        _mathModel.arithmetic(10,10,ModeDirector.Mode.MATH_MULT);
        pushChild("QuestionView");
    }

    public void divide() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_DIV);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_DIV);
        pushChild("QuestionView");
    }

    public void random() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_RANDOM);
        pushChild("CustomView");
    }



}
