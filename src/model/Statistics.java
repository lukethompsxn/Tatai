package model;

import java.io.*;
import java.util.LinkedList;

public class Statistics {

    private static Statistics _statistics = Statistics.instance();
    private static ModeDirector _modeDirector = ModeDirector.instance();

    private LinkedList<Integer> _recentScoresMult = new LinkedList<>();
    private LinkedList<Integer> _recentScoresDiv = new LinkedList<>();
    private LinkedList<Integer> _recentScoresSub = new LinkedList<>();
    private LinkedList<Integer> _recentScoresAdd = new LinkedList<>();

    private int _highScoreMult = 0;
    private int _highScoreDiv = 0;
    private int _highScoreSub = 0;
    private int _highScoreAdd = 0;
    private int _questionsAnswered = 0;
    private int _quizzesCompleted = 0;

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

    public LinkedList<Integer> get_recentScoresMult() {
        return _recentScoresMult;
    }

    public void addRecentScoresMult(int score) {
        if (_recentScoresMult.size() == 10) {
            _recentScoresMult.removeLast();
        }
        _recentScoresMult.addFirst(score);
    }

    public LinkedList<Integer> get_recentScoresDiv() {
        return _recentScoresDiv;
    }

    public void addRecentScoresDiv(int score) {
        if (_recentScoresDiv.size() == 10) {
            _recentScoresDiv.removeLast();
        }
        _recentScoresDiv.addFirst(score);
    }

    public LinkedList<Integer> get_recentScoresSub() {
        return _recentScoresSub;
    }

    public void addRecentScoresSub(int score) {
        if (_recentScoresSub.size() == 10) {
            _recentScoresSub.removeLast();
        }
        _recentScoresSub.addFirst(score);
    }

    public LinkedList<Integer> get_recentScoresAdd() {
        return _recentScoresAdd;
    }

    public void addRecentScoresAdd(int score) {
        if (_recentScoresAdd.size() == 10) {
            _recentScoresAdd.removeLast();
        }
        _recentScoresAdd.addFirst(score);
    }

    public int get_highScoreMult() {
        return _highScoreMult;
    }

    public void set_highScoreMult(int highScoreMult) {
        _highScoreMult = highScoreMult;
    }

    public int get_highScoreDiv() {
        return _highScoreDiv;
    }

    public void set_highScoreDiv(int highScoreDiv) {
        _highScoreDiv = highScoreDiv;
    }

    public int get_highScoreSub() {
        return _highScoreSub;
    }

    public void set_highScoreSub(int highScoreSub) {
        _highScoreSub = highScoreSub;
    }

    public int get_highScoreAdd() {
        return _highScoreAdd;
    }

    public void set_highScoreAdd(int highScoreAdd) {
        _highScoreAdd = highScoreAdd;
    }

    public int getQuizzesCompleted() {
        return _quizzesCompleted;
    }

    public void setQuizzesCompleted(int quizzesCompleted) {
        _quizzesCompleted = quizzesCompleted;
    }

    public int getQuestionsAnswered() {
        return _questionsAnswered;
    }

    public void setQuestionsAnswered(int questionsAnswered) {
        _questionsAnswered = questionsAnswered;
    }

    public void writeToFileHighScores() {
        //String f1, int f2, boolean append
        try {
            File file = new File("data" + File.separator + "HighScores.txt");
            File tempFile = new File("data" + File.separator + "tempFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                if (currentLine.startsWith("addhigh")) {
                    bufferedWriter.write("addhigh" + _highScoreAdd + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("subhigh")) {
                    bufferedWriter.write("subhigh" + _highScoreSub + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("multhigh")) {
                    bufferedWriter.write("multhigh" + _highScoreMult + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("divhigh")) {
                    bufferedWriter.write("divhigh" + _highScoreDiv + System.getProperty("line.separator"));
                }

                else if (currentLine.startsWith("addtotscore")) {
                    bufferedWriter.write("addtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_ADD) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("addtotitt")) {
                    bufferedWriter.write("addtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_ADD) + System.getProperty("line.separator"));
                }

                else if (currentLine.startsWith("subtotscore")) {
                    bufferedWriter.write("subtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_SUB) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("subtotitt")) {
                    bufferedWriter.write("subtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_SUB) + System.getProperty("line.separator"));
                }

                else if (currentLine.startsWith("multtotscore")) {
                    bufferedWriter.write("multtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_MULT) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("multtotitt")) {
                    bufferedWriter.write("multtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_MULT) + System.getProperty("line.separator"));
                }

                else if (currentLine.startsWith("divtotscore")) {
                    bufferedWriter.write("divtotscore" + _modeDirector.getTotalScores(ModeDirector.Mode.MATH_DIV) + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("divtotitt")) {
                    bufferedWriter.write("divtotitt" + _modeDirector.getTotalIterations(ModeDirector.Mode.MATH_DIV) + System.getProperty("line.separator"));
                }

                else if (currentLine.startsWith("questans")) {
                    bufferedWriter.write("questans" + _statistics.getQuestionsAnswered() + System.getProperty("line.separator"));
                } else if (currentLine.startsWith("quizdone")) {
                    bufferedWriter.write("quizdone" + _statistics.getQuizzesCompleted() + System.getProperty("line.separator"));
                }

                else {
                    bufferedWriter.write(currentLine + System.getProperty("line.separator"));
                }
            }

            bufferedReader.close();
            bufferedWriter.close();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFileHighScores() {

        try {
            File file = new File("data" + File.separator + "HighScores.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("addhigh")) {
                    _highScoreAdd = Integer.parseInt(currentLine.replaceFirst("addhigh",""));
                } else if (currentLine.startsWith("subhigh")) {
                    _highScoreSub = Integer.parseInt(currentLine.replaceFirst("subhigh",""));
                } else if (currentLine.startsWith("multhigh")) {
                    _highScoreMult = Integer.parseInt(currentLine.replaceFirst("multhigh",""));
                } else if (currentLine.startsWith("divhigh")) {
                    _highScoreDiv = Integer.parseInt(currentLine.replaceFirst("divhigh",""));
                }

                else if (currentLine.startsWith("addtotscore")) {
                    _modeDirector.setTotalAddScore(Integer.parseInt(currentLine.replaceFirst("addtotscore","")));
                } else if (currentLine.startsWith("addtotitt")) {
                    _modeDirector.setTotalAddIterations(Integer.parseInt(currentLine.replaceFirst("addtotitt","")));
                }

                else if (currentLine.startsWith("subtotscore")) {
                   _modeDirector.setTotalSubScore(Integer.parseInt(currentLine.replaceFirst("subtotscore","")));
                } else if (currentLine.startsWith("subtotitt")) {
                    _modeDirector.setTotalSubIterations(Integer.parseInt(currentLine.replaceFirst("subtotitt","")));
                }

                else if (currentLine.startsWith("multtotscore")) {
                    _modeDirector.setTotalMultScore(Integer.parseInt(currentLine.replaceFirst("multtotscore","")));
                } else if (currentLine.startsWith("multtotitt")) {
                    _modeDirector.setTotalMultIterations(Integer.parseInt(currentLine.replaceFirst("multtotitt","")));
                }

                else if (currentLine.startsWith("divtotscore")) {
                    _modeDirector.setTotalDivScore(Integer.parseInt(currentLine.replaceFirst("divtotscore","")));
                } else if (currentLine.startsWith("divtotitt")) {
                    _modeDirector.setTotalDivIterations(Integer.parseInt(currentLine.replaceFirst("divtotitt","")));
                }

                else if (currentLine.startsWith("questans")) {
                    _statistics.setQuestionsAnswered(Integer.parseInt(currentLine.replaceFirst("questans","")));
                } else if (currentLine.startsWith("quizdone")) {
                    _statistics.setQuizzesCompleted(Integer.parseInt(currentLine.replaceFirst("quizdone","")));
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFileRecentScores() {
        try {
            File file = new File("data" + File.separator + "RecentScores.txt");
            File tempFile = new File("data" + File.separator + "tempFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {

                if (currentLine.startsWith("@add")) {
                    bufferedWriter.write("@add" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {
                        bufferedWriter.write(Integer.toString(_recentScoresAdd.get(i))+ System.getProperty("line.separator"));

                    }
                } else if (currentLine.startsWith("@sub")) {
                    bufferedWriter.write("@sub" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {
                        bufferedWriter.write(Integer.toString(_recentScoresSub.get(i))+ System.getProperty("line.separator"));

                    }
                } else if (currentLine.startsWith("@mult")) {
                    bufferedWriter.write("@mult" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {

                        bufferedWriter.write(Integer.toString(_recentScoresMult.get(i)) + System.getProperty("line.separator"));

                    }
                } else if (currentLine.startsWith("@div")) {
                    bufferedWriter.write("@div" + System.getProperty("line.separator"));
                    for (int i = 0; i < 10; i++) {
                        bufferedWriter.write(Integer.toString(_recentScoresDiv.get(i))+ System.getProperty("line.separator"));

                    }
                }
            }

            bufferedReader.close();
            bufferedWriter.close();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFileRecentScores() {

        try {
            File file = new File("data" + File.separator + "RecentScores.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                if (currentLine.startsWith("@add")) {
                    for (int i = 0; i < 10; i++) {
                        _recentScoresAdd.addLast(Integer.parseInt(reader.readLine()));
                    }
                } else if (currentLine.startsWith("@sub")) {
                    for (int i = 0; i < 10; i++) {
                        _recentScoresSub.addLast(Integer.parseInt(reader.readLine()));
                    }
                } else if (currentLine.startsWith("@mult")) {
                    for (int i = 0; i < 10; i++) {
                        _recentScoresMult.addLast(Integer.parseInt(reader.readLine()));
                    }
                } else if (currentLine.startsWith("@div")) {
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

    public void createFiles() {
        try {
            File file1 = new File("data" + File.separator + "RecentScores.txt");
            File file2 = new File("data" + File.separator + "HighScores.txt");

            if (!file1.exists()) {
                file1.createNewFile();
                formatNewRecentFile();
            } else {
                System.out.println("exists");
            }
            if (!file2.exists()) {
                file2.createNewFile();
                formatNewHighFile();
            } else {
                System.out.println("exists");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void formatNewRecentFile() {
        try {

            File file = new File("data" + File.separator + "RecentScores.txt");
            File tempFile = new File("data" + File.separator + "tempFile.txt");

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

    public void formatNewHighFile() {
        try {

            File file = new File("data" + File.separator + "HighScores.txt");
            File tempFile = new File("data" + File.separator + "tempFile.txt");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            writer.write("@highscores" + System.getProperty("line.separator"));
            writer.write("addhigh0" + System.getProperty("line.separator"));
            writer.write("subhigh0" + System.getProperty("line.separator"));
            writer.write("multhigh0" + System.getProperty("line.separator"));
            writer.write("divhigh0" + System.getProperty("line.separator"));
            writer.write("@averages" + System.getProperty("line.separator"));
            writer.write("addtotsccore0" + System.getProperty("line.separator"));
            writer.write("addtotitt0" + System.getProperty("line.separator"));
            writer.write("subtotsccore0" + System.getProperty("line.separator"));
            writer.write("subtotitt0" + System.getProperty("line.separator"));
            writer.write("multtotsccore0" + System.getProperty("line.separator"));
            writer.write("multtotitt0" + System.getProperty("line.separator"));
            writer.write("divtotsccore0" + System.getProperty("line.separator"));
            writer.write("divtotitt0" + System.getProperty("line.separator"));
            writer.write("@rand" + System.getProperty("line.separator"));
            writer.write("questans0" + System.getProperty("line.separator"));
            writer.write("quizdone0" + System.getProperty("line.separator"));

            bufferedReader.close();
            writer.close();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}