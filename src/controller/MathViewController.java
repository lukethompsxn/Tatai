package controller;

import model.NumberCollection;

public class MathViewController extends AbstractController{
    private static NumberCollection _model = NumberCollection.instance();

    public void add() {
        _model.setMode(NumberCollection.Mode.MATH_ADD);
        pushChild("QuestionView");
    }

    public void subtract() {
        _model.setMode(NumberCollection.Mode.MATH_SUB);
        pushChild("QuestionView");
    }

    public void multiply() {
        _model.setMode(NumberCollection.Mode.MATH_MULT);
        pushChild("QuestionView");
    }

    public void divide() {
        _model.setMode(NumberCollection.Mode.MATH_DIV);
        pushChild("QuestionView");
    }

    public void random() {
        _model.setMode(NumberCollection.Mode.MATH_RANDOM);
        pushChild("QuestionView");
    }



}
