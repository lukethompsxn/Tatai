package controller;

import model.ModeDirector;

public class SkipPopupController extends AbstractController  {
    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static QuestionViewController _qvc;
    private int _iteration;
    private int _numQuestions;
    private int _score;

    public SkipPopupController(int iteration, int numQuestions, int score, QuestionViewController qvc) {
        _iteration = iteration;
        _numQuestions = numQuestions;
        _score = score;
        _qvc = qvc;
    }

    public void skip() {
        if (_iteration > _numQuestions) {
            _modeDirector.updateStats(_score, _iteration);
            pushChild("SummaryView");
        } else {
            _qvc.nextQuestion();
            _qvc.resetBtns();
        }
        closePopup();
    }

    public void cancel() {
        closePopup();
    }
}
