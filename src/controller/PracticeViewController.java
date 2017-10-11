package controller;

import model.NumberCollection;

public class PracticeViewController extends AbstractController {

    private static NumberCollection _model = NumberCollection.instance();

    public void easy() {
        _model.setCollection(10);
        _model.setMode(NumberCollection.Mode.PRACTICE_EASY);
        pushChild("QuestionView");
    }

    public void hard() {
        _model.setCollection(99);
        _model.setMode(NumberCollection.Mode.PRACTICE_HARD);
        pushChild("QuestionView");
    }
}
