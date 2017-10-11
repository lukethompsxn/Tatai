package model;

public class NumberCollection {
    private int _totalScore;
    private int _totalIterations;
    private static NumberCollection _numberCollection;
    public enum Mode { PRACTICE_EASY, PRACTICE_HARD, MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV, MATH_RANDOM, MATH_CUSTOM };
    public enum Type { PRACTICE, MATH };
    private Mode _currentMode = Mode.PRACTICE_EASY;
    private Type _currentType = Type.PRACTICE;


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
