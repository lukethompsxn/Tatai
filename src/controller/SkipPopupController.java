package controller;

import model.ModeDirector;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class SkipPopupController extends AbstractController  {
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static QuestionViewController _qvc;
    private int _iteration;
    private int _numQuestions;
    private int _score;

    /**
     * This constructor is used to set the iteration, number of questions, score and question view controller, as passed
     * in from the question view controller.
     * @param iteration
     * @param numQuestions
     * @param score
     * @param qvc
     */
    public SkipPopupController(int iteration, int numQuestions, int score, QuestionViewController qvc) {
        _iteration = iteration;
        _numQuestions = numQuestions;
        _score = score;
        _qvc = qvc;
    }

    /**
     * This method is the action for the user confirming to skip the question. It first checks which were it should be
     * changing to, depending on the stage of the quiz which the user is it. Then methods are called on the controller
     * in order to prepare the view for the next iteration, before closing the popup by calling a method from abstract
     * controller.
     */
    public void skip() {
        if (_iteration > _numQuestions) {
            pushChild("SummaryView");
        } else {
            _qvc.nextQuestion();
            _qvc.resetBtns();
        }
        closePopup();
    }

    //Action for the "cancel" button. This method calls a method from abstract controller in order to close the pop up
    public void cancel() {
        closePopup();
    }
}
