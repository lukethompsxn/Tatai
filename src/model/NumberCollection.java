package model;

public class NumberCollection {
    private int _totalScore;
    private int _totalIterations;
    private static NumberCollection _numberCollection;
    public enum Mode { RECALL_EASY, RECALL_HARD, MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV, MATH_RANDOM, MATH_CUSTOM };
    public enum Type { RECALL, MATH };
    private Mode _currentMode = Mode.RECALL_EASY;
    private Type _currentType = Type.RECALL;


    //Singleton Constructor
    public static NumberCollection instance() {
        if (_numberCollection == null) {
            _numberCollection = new NumberCollection();
        }
        return _numberCollection;
    }

    //Private Constructor
    private NumberCollection() {
        //anything that needs to constructed
    }

    public void updateStats(int score, int iterations) {
        _totalScore+=score;
        _totalIterations+=iterations;
    }

    public void setMode(Mode mode) {
        _currentMode = mode;
    }

    public void setType(Type type) {
       _currentType = type;
    }

    public Type getType() {
        return _currentType;
    }


}
