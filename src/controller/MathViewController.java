package controller;

import model.MathsCollection;
import model.NumberCollection;

public class MathViewController extends AbstractController{
    //private static NumberCollection _model = NumberCollection.instance();
    private static MathsCollection _mathModel = MathsCollection.instance();

    public void add() {
        _mathModel.setMode(NumberCollection.Mode.MATH_ADD);
        _mathModel.arithmetic(99,10,NumberCollection.Mode.MATH_ADD);
        pushChild("QuestionView");
    }

    public void subtract() {
        _mathModel.setMode(NumberCollection.Mode.MATH_SUB);
        _mathModel.arithmetic(99,10,NumberCollection.Mode.MATH_SUB);
        pushChild("QuestionView");
    }

    public void multiply() {
        _mathModel.setMode(NumberCollection.Mode.MATH_MULT);
        _mathModel.arithmetic(10,10,NumberCollection.Mode.MATH_MULT);
        pushChild("QuestionView");
    }

    public void divide() {
        _mathModel.setMode(NumberCollection.Mode.MATH_DIV);
        _mathModel.arithmetic(99,10,NumberCollection.Mode.MATH_DIV);
        pushChild("QuestionView");
    }

    public void random() {
        _mathModel.setMode(NumberCollection.Mode.MATH_RANDOM);
        pushChild("CustomView");
    }



}
