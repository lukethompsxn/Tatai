package model;

public class ModeDirector {

    private static ModeDirector _modeDirector = ModeDirector.instance();
    public enum Mode { PRACTICE_EASY, PRACTICE_HARD, MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV, MATH_RANDOM, MATH_CUSTOM };
    public enum Type { PRACTICE, MATH };
    private ModeDirector.Mode _currentMode;
    private ModeDirector.Type _currentType;
    private int _totalScore;
    private int _totalIterations;
    private int _numQuestions;

    //Singleton Constructor
    public static ModeDirector instance() {
        if (_modeDirector == null) {
            _modeDirector = new ModeDirector();
        }
        return _modeDirector;
    }

    //Private Constructor
    private ModeDirector() {
        //anything that needs to constructed
    }

    public void updateStats(int score, int iterations) {
        _totalScore+=score;
        _totalIterations+=iterations;
    }

    public void setMode(ModeDirector.Mode mode) {
        _currentMode = mode;
    }

    public ModeDirector.Mode getMode() {
        return _currentMode;
    }

    public void setType(ModeDirector.Type type) {
        _currentType = type;
    }

    public ModeDirector.Type getType() {
        return _currentType;
    }

    public int getNumQuestions() {
        return _numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        _numQuestions = numQuestions;
    }


}
