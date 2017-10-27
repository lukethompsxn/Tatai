package model;

import java.io.*;
import java.util.LinkedList;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class Statistics {

    private static Statistics _statistics = Statistics.instance();
    private static ModeDirector _modeDirector = ModeDirector.instance();

    // Linked lists of recent scores
    private LinkedList<Integer> _recentScoresMult = new LinkedList<>();
    private LinkedList<Integer> _recentScoresDiv = new LinkedList<>();
    private LinkedList<Integer> _recentScoresSub = new LinkedList<>();
    private LinkedList<Integer> _recentScoresAdd = new LinkedList<>();

    // Score history
    private int _highScoreMult = 0;
    private int _highScoreDiv = 0;
    private int _highScoreSub = 0;
    private int _highScoreAdd = 0;
    private int _addQuestionsAnswered = 0;
    private int _addQuizzesCompleted = 0;
    private int _subQuestionsAnswered = 0;
    private int _subQuizzesCompleted = 0;
    private int _multQuestionsAnswered = 0;
    private int _multQuizzesCompleted = 0;
    private int _divQuestionsAnswered = 0;
    private int _divQuizzesCompleted = 0;

    //Singleton Constructor
    public static Statistics instance() {
        if (_statistics == null) {
            _statistics = new Statistics();
        }
        return _statistics;
    }

    //Private Constructor
    private Statistics() {
        //anything that needs to constructed
    }

    /**
     * This method returns a list containing the recent scores for the math mode multiplication
     * @return list of 10 most recent scores for multiplication
     */
    public LinkedList<Integer> getRecentScoresMult() {
        return _recentScoresMult;
    }

    /**
     * This method takes a parameter score of type int and adds it to the start of the multiplication recent score linked list.
     * If the list size is greater than 10 the last value is removed in order to keep a constant list size of 10.
     * @param score recent score to be added
     */
    public void addRecentScoresMult(int score) {
        if (_recentScoresMult.size() == 10) {
            _recentScoresMult.removeLast();
        }
        _recentScoresMult.addFirst(score);
    }

    /**
     * Refer to getRecentScoresMult(), similar method but for division.
     * @return list of 10 most recent scores for division
     */
    public LinkedList<Integer> getRecentScoresDiv() {
        return _recentScoresDiv;
    }

    /**
     * Refer to addRecentScoresMult(int score), similar method but for division.
     * @param score recent score to be added
     */
    public void addRecentScoresDiv(int score) {
        if (_recentScoresDiv.size() == 10) {
            _recentScoresDiv.removeLast();
        }
        _recentScoresDiv.addFirst(score);
    }

    /**
     * Refer to getRecentScoresMult(), similar method but for subtraction.
     * @return list of 10 most recent scores for subtraction
     */
    public LinkedList<Integer> getRecentScoresSub() {
        return _recentScoresSub;
    }

    /**
     * Refer to addRecentScoresMult(int score), similar method but for subtraction.
     * @param score recent score to be added
     */
    public void addRecentScoresSub(int score) {
        if (_recentScoresSub.size() == 10) {
            _recentScoresSub.removeLast();
        }
        _recentScoresSub.addFirst(score);
    }

    /**
     * Refer to getRecentScoresMult(), similar method but for addition.
     * @return list of 10 most recent scores for addition
     */
    public LinkedList<Integer> getRecentScoresAdd() {
        return _recentScoresAdd;
    }

    /**
     * Refer to addRecentScoresMult(int score), similar method but for addition.
     * @param score recent score to be added
     */
    public void addRecentScoresAdd(int score) {
        if (_recentScoresAdd.size() == 10) {
            _recentScoresAdd.removeLast();
        }
        _recentScoresAdd.addFirst(score);
    }

    // Get and set the high score for multiplication
    public int getHighScoreMult() {
        return _highScoreMult;
    }
    public void setHighScoreMult(int highScoreMult) {
        _highScoreMult = highScoreMult;
    }

    // Get and set the high score for division
    public int getHighScoreDiv() {
        return _highScoreDiv;
    }
    public void setHighScoreDiv(int highScoreDiv) {
        _highScoreDiv = highScoreDiv;
    }

    // Get and set the high score for subtraction
    public int getHighScoreSub() {
        return _highScoreSub;
    }
    public void setHighScoreSub(int highScoreSub) {
        _highScoreSub = highScoreSub;
    }

    // Get and set the high score for addition
    public int getHighScoreAdd() {
        return _highScoreAdd;
    }
    public void setHighScoreAdd(int highScoreAdd) {
        _highScoreAdd = highScoreAdd;
    }

    // Get and set the total number of addition quizzes completed
    public int getAddQuizzesCompleted() {
        return _addQuizzesCompleted;
    }
    public void setAddQuizzesCompleted(int quizzesCompleted) {
        _addQuizzesCompleted = quizzesCompleted;
    }

    // Get and set the total number of subtraction quizzes completed
    public int getSubQuizzesCompleted() {
        return _subQuizzesCompleted;
    }
    public void setSubQuizzesCompleted(int quizzesCompleted) {
        _subQuizzesCompleted = quizzesCompleted;
    }

    // Get and set the total number of multiplication quizzes completed
    public int getMultQuizzesCompleted() {
        return _multQuizzesCompleted;
    }
    public void setMultQuizzesCompleted(int quizzesCompleted) {
        _multQuizzesCompleted = quizzesCompleted;
    }

    // Get and set the total number of division quizzes completed
    public int getDivQuizzesCompleted() {
        return _divQuizzesCompleted;
    }
    public void setDivQuizzesCompleted(int quizzesCompleted) {
        _divQuizzesCompleted = quizzesCompleted;
    }

    // Get and set the number of addition questions answered
    public int getAddQuestionsAnswered() {
        return _addQuestionsAnswered;
    }
    public void setAddQuestionsAnswered(int questionsAnswered) {
        _addQuestionsAnswered = questionsAnswered;
    }

    // Get and set the number of subtraction questions answered
    public int getSubQuestionsAnswered() {
        return _subQuestionsAnswered;
    }
    public void setSubQuestionsAnswered(int questionsAnswered) {
        _subQuestionsAnswered = questionsAnswered;
    }

    // Get and set the number of multiplication questions answered
    public int getMultQuestionsAnswered() {
        return _multQuestionsAnswered;
    }
    public void setMultQuestionsAnswered(int questionsAnswered) {
        _multQuestionsAnswered = questionsAnswered;
    }

    // Get and set number of division questions answered
    public int getDivQuestionsAnswered() {
        return _divQuestionsAnswered;
    }
    public void setDivQuestionsAnswered(int questionsAnswered) {
        _divQuestionsAnswered = questionsAnswered;
    }

    /**
     * This method writes to a file called HighScores the current history and high scores so that they can be saved
     */
    public void writeToFileHighScores() {
        try {
            File file = new File("data" + File.separator + ".HighScores.txt");
            File tempFile = new File("data" + File.separator + ".tempFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            // Reads reader until there is no more line available
            while ((currentLine = bufferedReader.readLine()) != null) {

                // Saves all high scores
                if (currentLine.startsWith("addhigh")) {
                    bufferedWriter.write("addhigh" + _highScoreAdd + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("subhigh")) {
                    bufferedWriter.write("subhigh" + _highScoreSub + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("multhigh")) {
                    bufferedWriter.write("multhigh" + _highScoreMult + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("divhigh")) {
                    bufferedWriter.write("divhigh" + _highScoreDiv + System.getProperty("line.separator"));
                }

                // Saves addition total scores and total iterations
                else if (currentLine.startsWith("addtotscore")) {
                    bufferedWriter.write("addtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_ADD) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("addtotitt")) {
                    bufferedWriter.write("addtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_ADD) + System.getProperty("line.separator"));
                }

                // Saves subtraction total scores and total iterations
                else if (currentLine.startsWith("subtotscore")) {
                    bufferedWriter.write("subtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_SUB) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("subtotitt")) {
                    bufferedWriter.write("subtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_SUB) + System.getProperty("line.separator"));
                }

                // Saves multiplication total scores and total iterations
                else if (currentLine.startsWith("multtotscore")) {
                    bufferedWriter.write("multtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_MULT) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("multtotitt")) {
                    bufferedWriter.write("multtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_MULT) + System.getProperty("line.separator"));
                }

                // Saves division total scores and total iterations
                else if (currentLine.startsWith("divtotscore")) {
                    bufferedWriter.write("divtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_DIV) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("divtotitt")) {
                    bufferedWriter.write("divtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_DIV) + System.getProperty("line.separator"));
                }

                // Saves addition total questions and quizzes completed
                else if (currentLine.startsWith("addquestans")) {
                    bufferedWriter.write("addquestans" + _statistics.getAddQuestionsAnswered() + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("addquizdone")) {
                    bufferedWriter.write("addquizdone" + _statistics.getAddQuizzesCompleted() + System.getProperty("line.separator"));
                }

                // Saves subtraction total questions and quizzes completed
                else if (currentLine.startsWith("subquestans")) {
                    bufferedWriter.write("subquestans" + _statistics.getSubQuestionsAnswered() + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("subquizdone")) {
                    bufferedWriter.write("subquizdone" + _statistics.getSubQuizzesCompleted() + System.getProperty("line.separator"));
                }

                // Saves multiplication total questions and quizzes completed
                else if (currentLine.startsWith("multquestans")) {
                    bufferedWriter.write("multquestans" + _statistics.getMultQuestionsAnswered() + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("multquizdone")) {
                    bufferedWriter.write("multquizdone" + _statistics.getMultQuizzesCompleted() + System.getProperty("line.separator"));
                }

                // Saves division total questions and quizzes completed
                else if (currentLine.startsWith("divquestans")) {
                    bufferedWriter.write("divquestans" + _statistics.getDivQuestionsAnswered() + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("divquizdone")) {
                    bufferedWriter.write("divquizdone" + _statistics.getDivQuizzesCompleted() + System.getProperty("line.separator"));
                }

                else {
                    bufferedWriter.write(currentLine + System.getProperty("line.separator"));
                }
            }

            bufferedReader.close();
            bufferedWriter.close();

            // Renames and overwrites the temp file to the the main file HighScores.txt
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file called HighScores.txt which contains all the score history of the application.
     * When reading the scores it saves the values to the relevant fields.
     */
    public void readFileHighScores() {

        try {
            File file = new File("data" + File.separator + ".HighScores.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String currentLine;
            while((currentLine = reader.readLine()) != null) {

                // Reads highscores
                if (currentLine.startsWith("addhigh")) {
                    _highScoreAdd = Integer.parseInt(currentLine.replaceFirst("addhigh",""));
                } else if (currentLine.startsWith("subhigh")) {
                    _highScoreSub = Integer.parseInt(currentLine.replaceFirst("subhigh",""));
                } else if (currentLine.startsWith("multhigh")) {
                    _highScoreMult = Integer.parseInt(currentLine.replaceFirst("multhigh",""));
                } else if (currentLine.startsWith("divhigh")) {
                    _highScoreDiv = Integer.parseInt(currentLine.replaceFirst("divhigh",""));
                }

                // Reads addition average score information
                else if (currentLine.startsWith("addtotscore")) {
                    _modeDirector.setTotalAddScore(Integer.parseInt(currentLine.replaceFirst("addtotscore","")));
                } else if (currentLine.startsWith("addtotitt")) {
                    _modeDirector.setTotalAddIterations(Integer.parseInt(currentLine.replaceFirst("addtotitt","")));
                }

                // Reads subtraction average score information
                else if (currentLine.startsWith("subtotscore")) {
                   _modeDirector.setTotalSubScore(Integer.parseInt(currentLine.replaceFirst("subtotscore","")));
                } else if (currentLine.startsWith("subtotitt")) {
                    _modeDirector.setTotalSubIterations(Integer.parseInt(currentLine.replaceFirst("subtotitt","")));
                }

                // Reads multiplication average score information
                else if (currentLine.startsWith("multtotscore")) {
                    _modeDirector.setTotalMultScore(Integer.parseInt(currentLine.replaceFirst("multtotscore","")));
                } else if (currentLine.startsWith("multtotitt")) {
                    _modeDirector.setTotalMultIterations(Integer.parseInt(currentLine.replaceFirst("multtotitt","")));
                }

                // Reads division average score information
                else if (currentLine.startsWith("divtotscore")) {
                    _modeDirector.setTotalDivScore(Integer.parseInt(currentLine.replaceFirst("divtotscore","")));
                } else if (currentLine.startsWith("divtotitt")) {
                    _modeDirector.setTotalDivIterations(Integer.parseInt(currentLine.replaceFirst("divtotitt","")));
                }

                // Reads addition total questions answered and quizzes completed
                else if (currentLine.startsWith("addquestans")) {
                    _statistics.setAddQuestionsAnswered(Integer.parseInt(currentLine.replaceFirst("addquestans","")));
                } else if (currentLine.startsWith("addquizdone")) {
                    _statistics.setAddQuizzesCompleted(Integer.parseInt(currentLine.replaceFirst("addquizdone","")));
                }

                // Reads subtraction total questions answered and quizzes completed
                else if (currentLine.startsWith("subquestans")) {
                    _statistics.setSubQuestionsAnswered(Integer.parseInt(currentLine.replaceFirst("subquestans","")));
                } else if (currentLine.startsWith("subquizdone")) {
                    _statistics.setSubQuizzesCompleted(Integer.parseInt(currentLine.replaceFirst("subquizdone","")));
                }

                // Reads multiplication total questions answered and quizzes completed
                else if (currentLine.startsWith("multquestans")) {
                    _statistics.setMultQuestionsAnswered(Integer.parseInt(currentLine.replaceFirst("multquestans","")));
                } else if (currentLine.startsWith("multquizdone")) {
                    _statistics.setMultQuizzesCompleted(Integer.parseInt(currentLine.replaceFirst("multquizdone","")));
                }

                // Reads division total questions answered and quizzes completed
                else if (currentLine.startsWith("divquestans")) {
                    _statistics.setDivQuestionsAnswered(Integer.parseInt(currentLine.replaceFirst("divquestans","")));
                } else if (currentLine.startsWith("divquizdone")) {
                    _statistics.setDivQuizzesCompleted(Integer.parseInt(currentLine.replaceFirst("divquizdone","")));
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes all the recent scores from the math modes addition, subtraction, multiplication and division to
     * a file called RecentScores.txt. This is done by reading all the relevant recent score lists, iterating through
     * them and then writing each score.
     */
    public void writeToFileRecentScores() {
        try {
            File file = new File("data" + File.separator + ".RecentScores.txt");

            // Creates a temp file to write to
            File tempFile = new File("data" + File.separator + ".tempFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                // Writes all recent addition scores
                if (currentLine.startsWith("@add")) {
                    bufferedWriter.write("@add" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {
                        bufferedWriter.write(Integer.toString(_recentScoresAdd.get(i))+ System.getProperty("line.separator"));

                    }
                }
                // Writes all recent subtraction scores
                else if (currentLine.startsWith("@sub")) {
                    bufferedWriter.write("@sub" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {
                        bufferedWriter.write(Integer.toString(_recentScoresSub.get(i))+ System.getProperty("line.separator"));

                    }
                }
                // Writes all recent multiplication scores
                else if (currentLine.startsWith("@mult")) {
                    bufferedWriter.write("@mult" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {

                        bufferedWriter.write(Integer.toString(_recentScoresMult.get(i)) + System.getProperty("line.separator"));

                    }
                }
                // Writes all recent division scores
                else if (currentLine.startsWith("@div")) {
                    bufferedWriter.write("@div" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {
                        bufferedWriter.write(Integer.toString(_recentScoresDiv.get(i))+ System.getProperty("line.separator"));

                    }
                }
            }

            bufferedReader.close();
            bufferedWriter.close();

            // Renames and overwrites the temp file to the the main file HighScores.txt
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file called RecentScores.txt which contains all the recent score history of the application.
     * When the method reads an @ followed by an operator (e.g. add) the method will iterate 10 times adding the scores
     * to the respective lists in the correct order that they were written in.
     */
    public void readFileRecentScores() {

        try {
            File file = new File("data" + File.separator + ".RecentScores.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String currentLine;
            while((currentLine = reader.readLine()) != null) {

                // Reads the last 10 recent addition scores
                if (currentLine.startsWith("@add")) {
                    for (int i = 0; i < 10; i++) {
                        _recentScoresAdd.addLast(Integer.parseInt(reader.readLine()));
                    }
                }
                // Reads the last 10 recent subtraction scores
                else if (currentLine.startsWith("@sub")) {
                    for (int i = 0; i < 10; i++) {
                        _recentScoresSub.addLast(Integer.parseInt(reader.readLine()));
                    }
                }
                // Reads the last 10 recent multiplication scores
                else if (currentLine.startsWith("@mult")) {
                    for (int i = 0; i < 10; i++) {
                        _recentScoresMult.addLast(Integer.parseInt(reader.readLine()));
                    }
                }
                // Reads the last 10 recent division scores
                else if (currentLine.startsWith("@div")) {
                    for (int i = 0; i < 10; i++) {
                        _recentScoresDiv.addLast(Integer.parseInt(reader.readLine()));
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method check if the files required for the statics side of the application exist, if not the method will
     * invoke another method which will create and format the needed file.
     */
    public void createFiles() {
        try {
            File file1 = new File("data" + File.separator + ".RecentScores.txt");
            File file2 = new File("data" + File.separator + ".HighScores.txt");

            // Checks if RecentScores.txt does not exist
            if (!file1.exists()) {

                // Creates file
                file1.createNewFile();

                // Formats file
                formatNewRecentFile();
            } else {
            }

            // Checks if HighScores.txt exists
            if (!file2.exists()) {

                // Creates file
                file2.createNewFile();

                // Formats file
                formatNewHighFile();
            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method formats the file RecentScores.txt following its creation in a specific format.
     */
    private void formatNewRecentFile() {
        try {

            File file = new File("data" + File.separator + ".RecentScores.txt");
            File tempFile = new File("data" + File.separator + ".tempFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));


            writer.write("@add" + System.getProperty("line.separator"));
            for (int i = 0; i < 10; i++) {
                writer.write(0 + System.getProperty("line.separator"));
            }
            writer.write("@sub" + System.getProperty("line.separator"));
            for (int i = 0; i < 10; i++) {
                writer.write(0 + System.getProperty("line.separator"));
            }
            writer.write("@mult" + System.getProperty("line.separator"));
            for (int i = 0; i < 10; i++) {
                writer.write(0 + System.getProperty("line.separator"));
            }
            writer.write("@div" + System.getProperty("line.separator"));
            for (int i = 0; i < 10; i++) {
                writer.write(0 + System.getProperty("line.separator"));
            }

            bufferedReader.close();
            writer.close();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method formats the file HighScores.txt following its creation in a specific format.
     */
    private void formatNewHighFile() {
        try {

            File file = new File("data" + File.separator + ".HighScores.txt");
            File tempFile = new File("data" + File.separator + ".tempFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            writer.write("@highscores" + System.getProperty("line.separator"));
            writer.write("addhigh0" + System.getProperty("line.separator"));
            writer.write("subhigh0" + System.getProperty("line.separator"));
            writer.write("multhigh0" + System.getProperty("line.separator"));
            writer.write("divhigh0" + System.getProperty("line.separator"));
            writer.write("@averages" + System.getProperty("line.separator"));
            writer.write("addtotscore0" + System.getProperty("line.separator"));
            writer.write("addtotitt0" + System.getProperty("line.separator"));
            writer.write("subtotscore0" + System.getProperty("line.separator"));
            writer.write("subtotitt0" + System.getProperty("line.separator"));
            writer.write("multtotscore0" + System.getProperty("line.separator"));
            writer.write("multtotitt0" + System.getProperty("line.separator"));
            writer.write("divtotscore0" + System.getProperty("line.separator"));
            writer.write("divtotitt0" + System.getProperty("line.separator"));
            writer.write("@rand" + System.getProperty("line.separator"));
            writer.write("addquestans0" + System.getProperty("line.separator"));
            writer.write("addquizdone0" + System.getProperty("line.separator"));
            writer.write("subquestans0" + System.getProperty("line.separator"));
            writer.write("subquizdone0" + System.getProperty("line.separator"));
            writer.write("multquestans0" + System.getProperty("line.separator"));
            writer.write("multquizdone0" + System.getProperty("line.separator"));
            writer.write("divquestans0" + System.getProperty("line.separator"));
            writer.write("divquizdone0" + System.getProperty("line.separator"));

            bufferedReader.close();
            writer.close();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
