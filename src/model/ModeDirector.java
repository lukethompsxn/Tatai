package model;

public class ModeDirector {

    private static ModeDirector _modeDirector = ModeDirector.instance();

    public enum Mode { PRACTICE_EASY, PRACTICE_HARD, MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV, MATH_RANDOM, MATH_CUSTOM };
    public enum RandomModes { MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV };
    public enum Type { PRACTICE, MATH };
    private ModeDirector.Mode _currentMode;
    private ModeDirector.Type _currentType;

    private int _score = 0;
    private int _iteration = 0;
    private int _numQuestions;
    private String _currentAnswer;
    private String _currentAttempt;

    private int _totalAddScore = 0;
    private int _totalSubScore = 0;
    private int _totalMultScore = 0;
    private int _totalDivScore = 0;
    private int _totalAddIterations = 0;
    private int _totalSubIterations = 0;
    private int _totalMultIterations = 0;
    private int _totalDivIterations = 0;
    private int _totalScore = 0;
    private int _totalIterations = 0;

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
        if (_currentMode == Mode.MATH_ADD) {
            _totalAddScore+=score;
            _totalAddIterations+=iterations;
        }
        else if (_currentMode == Mode.MATH_SUB) {
            _totalSubScore+=score;
            _totalSubIterations+=iterations;
        }
        else if (_currentMode == Mode.MATH_MULT) {
            _totalMultScore+=score;
            _totalMultIterations+=iterations;
        }
        else if (_currentMode == Mode.MATH_DIV) {
            _totalDivScore+=score;
            _totalDivIterations+=iterations;
        }
        _totalScore+=score;
        _totalIterations+=iterations;
    }

    public int getStats(Mode mode) {
        if (mode == Mode.MATH_ADD) {
            if (_totalAddIterations == 0) {
                return 0;
            }
            return _totalAddScore/_totalAddIterations;
        }
        else if (mode == Mode.MATH_SUB) {
            if (_totalSubIterations == 0) {
                return 0;
            }
            return _totalSubScore/_totalSubIterations;
        }
        else if (mode == Mode.MATH_MULT) {
            if (_totalMultIterations == 0) {
                return 0;
            }
            return _totalMultScore/_totalMultIterations;
        }
        else if (mode == Mode.MATH_DIV) {
            if (_totalDivIterations == 0) {
                return 0;
            }
            return _totalDivScore/_totalDivIterations;
        }
        return 0;
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

    public void setIteration(int iteration) {
        _iteration = iteration;
    }

    public int getIteration() {
        return _iteration;
    }

    public void setScore(int score) {
        _score = score;
    }

    public int getScore() {
        return _score;
    }

    public void setCurrentAnswer(String attempt, String answer) {
        _currentAnswer = answer;
        _currentAttempt = attempt;
    }

    public String getCurrentAnswer() {
        return _currentAnswer;
    }

    public String getCurrentAttempt() {
        return _currentAttempt;
    }
}

