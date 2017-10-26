package model;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class ModeDirector {
    private static ModeDirector _modeDirector = ModeDirector.instance();

    // Variables relating to the state of the application
    public enum Mode { PRACTICE_EASY, PRACTICE_HARD, MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV, MATH_RANDOM, MATH_CUSTOM }
    public enum RandomModes { MATH_ADD, MATH_SUB, MATH_MULT, MATH_DIV }
    public enum Type { PRACTICE, MATH };
    private ModeDirector.Mode _currentMode;
    private ModeDirector.Type _currentType;

    // Current question information
    private int _score = 0;
    private int _iteration = 0;
    private int _numQuestions;
    private String _currentAnswer;
    private String _currentAttempt;

    // Long term saved question information
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

    private boolean _playbackEnabled = true;

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

    /**
     * This method updates the long term number of iteration and score based on the what the current mode is for the
     * application. It takes two inputs of type int and adds them to their corresponding instance variable.
     * @param score most recent score to add to total score
     * @param iterations most recent number of iterations to add the total number of iterations.
     */
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

    /**
     * This method returns an int representing the average score of the current mode which the application is in. It uses
     * two instances variables total iterations and total score and from this calculates the average which is then returned.
     * @param mode what mode the application is in
     * @return
     */
    public int getStats(Mode mode) {
        if (mode == Mode.MATH_ADD) {
            if (_totalAddIterations == 0) {
                return 0;
            }
            return 100*_totalAddScore/_totalAddIterations;
        }
        else if (mode == Mode.MATH_SUB) {
            if (_totalSubIterations == 0) {
                return 0;
            }
            return 100*_totalSubScore/_totalSubIterations;
        }
        else if (mode == Mode.MATH_MULT) {
            if (_totalMultIterations == 0) {
                return 0;
            }
            return 100*_totalMultScore/_totalMultIterations;
        }
        else if (mode == Mode.MATH_DIV) {
            if (_totalDivIterations == 0) {
                return 0;
            }
            return 100*_totalDivScore/_totalDivIterations;
        }
        return 0;
    }

    // These methods set and get the current mode of the application
    public void setMode(ModeDirector.Mode mode) {
        _currentMode = mode;
    }
    public ModeDirector.Mode getMode() {
        return _currentMode;
    }

    // Get and set the current type of the application
    public void setType(ModeDirector.Type type) {
        _currentType = type;
    }
    public ModeDirector.Type getType() {
        return _currentType;
    }

    // Get and set the total number of total number of questions in the current quiz
    public int getNumQuestions() {
        return _numQuestions;
    }
    public void setNumQuestions(int numQuestions) {
        _numQuestions = numQuestions;
    }

    // Get and set the current iteration or question the application is up to
    public void setIteration(int iteration) {
        _iteration = iteration;
    }
    public int getIteration() {
        return _iteration;
    }

    // Get and set the current score of a quiz
    public void setScore(int score) {
        _score = score;
    }
    public int getScore() {
        return _score;
    }

    // Sets the current answer and attempt to instance variables
    public void setCurrentAnswer(String attempt, String answer) {
        _currentAnswer = answer;
        _currentAttempt = attempt;
    }

    // Get current answer and current attempt of the voice
    public String getCurrentAnswer() {
        return _currentAnswer;
    }
    public String getCurrentAttempt() {
        return _currentAttempt;
    }

    // Get and set whether the play back of audio is enabled.
    public void setPlaybackEnabled(Boolean bool) {
        _playbackEnabled = bool;
    }
    public boolean getPlaybackEnabled() {
        return _playbackEnabled;
    }

    // These four methods set the total score for the respective operator, e.g. add/sub/mult/div
    public void setTotalAddScore(int score) {
        _totalAddScore = score;
    }
    public void setTotalSubScore(int score) {
        _totalSubScore = score;
    }
    public void setTotalMultScore(int score) {
        _totalMultScore = score;
    }
    public void setTotalDivScore(int score) {
        _totalDivScore = score;
    }

    /**
     * This method returns an int which represents the total score of the specified mode which is passed in as a parameter.
     * @param mode current mode of the application.
     * @return the total score, type int
     */
    public int getTotalScores(ModeDirector.Mode mode) {
        if (mode == Mode.MATH_ADD) {
            return _totalAddScore;
        }
        else if (mode == Mode.MATH_SUB) {
            return _totalSubScore;
        }
        else if (mode == Mode.MATH_MULT) {
            return _totalMultScore;
        }
        else if (mode == Mode.MATH_DIV) {
            return _totalDivScore;
        }
        return 0;
    }

    // These four methods set the total number iteration for the respective operator, e.g. add/sub/mult/div
    public void setTotalAddIterations(int iterations) {
        _totalAddIterations = iterations;
    }
    public void setTotalSubIterations(int iterations) {
        _totalSubIterations = iterations;
    }
    public void setTotalMultIterations(int iterations) {
        _totalMultIterations = iterations;
    }
    public void setTotalDivIterations(int iterations) {
        _totalDivIterations = iterations;
    }

    /**
     * This method returns an int which represents the total number of iterations of the specified mode which is passed in as a parameter.
     * @param mode current mode of the application.
     * @return
     */
    public int getTotalIterations(ModeDirector.Mode mode) {
        if (mode == Mode.MATH_ADD) {
            return _totalAddIterations;
        }
        else if (mode == Mode.MATH_SUB) {
            return _totalSubIterations;
        }
        else if (mode == Mode.MATH_MULT) {
            return _totalMultIterations;
        }
        else if (mode == Mode.MATH_DIV) {
            return _totalDivIterations;
        }
        return 0;
    }
}


