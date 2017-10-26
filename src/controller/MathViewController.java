package controller;

import model.MathsCollection;
import model.ModeDirector;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class MathViewController extends AbstractController{
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static MathsCollection _mathModel = MathsCollection.instance();

    /**
     * This action method carries out the required steps to start the addition math mode quiz. This includes setting the
     * mode in ModeDirector, generating the set of 10 questions to be inside the quiz and finally pushing the QuestionView
     * scene to the top of the stack so it is visible so that the quiz can begin.
     */
    public void add() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_ADD);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_ADD, false);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    /**
     * This action method carries out the required steps to start the subtraction math mode quiz. This includes setting the
     * mode in ModeDirector, generating the set of 10 questions to be inside the quiz and finally invokes the
     * AbstractController pushChild() method which pushes the QuestionView scene to the top of the stack so it is
     * visible so that the quiz can begin.
     */
    public void subtract() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_SUB);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_SUB, false);
        _modeDirector.setNumQuestions(10);_modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    /**
     * This action method carries out the required steps to start the multiplication math mode quiz. This includes setting the
     * mode in ModeDirector, generating the set of 10 questions to be inside the quiz and finally invokes the
     * AbstractController pushChild() method which pushes the QuestionView scene to the top of the stack so it is
     * visible so that the quiz can begin.
     */
    public void multiply() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_MULT);
        _mathModel.arithmetic(10,10,ModeDirector.Mode.MATH_MULT, false);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    /**
     * This action method carries out the required steps to start the division math mode quiz. This includes setting the
     * mode in ModeDirector, generating the set of 10 questions to be inside the quiz and finally invokes the
     * AbstractController pushChild() method which pushes the QuestionView scene to the top of the stack so it is
     * visible so that the quiz can begin.
     */
    public void divide() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_DIV);
        _mathModel.arithmetic(99,10,ModeDirector.Mode.MATH_DIV, false);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    /**
     * This action method carries out the required steps to start the custom math mode quiz. This includes setting the
     * mode in ModeDirector, generating the set of 10 questions to be inside the quiz and finally invokes the
     * AbstractController pushChild() method which pushes the QuestionView scene to the top of the stack so it is
     * visible so that the quiz can begin.
     */
    public void random() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_RANDOM);
        _mathModel.randomArithmetic(10);
        _modeDirector.setNumQuestions(10);
        pushChild("QuestionView");
    }

    /**
     * This action method carries out the initial steps to start the custom math mode. This method is different to
     * the other modes in that it does not generate the questions for this quiz in this method. This method invokes the
     * AbstractController pushChild() method which pushes the CustomView scene to the top of the stack which is where
     * all the custom quizzes are controlled.
     */
    public void custom() {
        _modeDirector.setMode(ModeDirector.Mode.MATH_CUSTOM);
        _modeDirector.setNumQuestions(10);
        pushChild("CustomView");
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
