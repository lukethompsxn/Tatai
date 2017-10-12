package controller;

import model.ModeDirector;
import model.NumberCollection;
import model.PracticeCollection;

public class PracticeViewController extends AbstractController {

    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static PracticeCollection _pracModel = PracticeCollection.instance();

    public void easy() {
        _pracModel.setCollection(10);
        _modeDirector.setMode(ModeDirector.Mode.PRACTICE_EASY);
        pushChild("QuestionView");
    }

    public void hard() {
        _pracModel.setCollection(99);
        _modeDirector.setMode(ModeDirector.Mode.PRACTICE_HARD);
        pushChild("QuestionView");
    }
}
