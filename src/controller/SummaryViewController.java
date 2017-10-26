package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.ModeDirector;
import model.Statistics;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class SummaryViewController extends AbstractController implements Initializable {

    @FXML
    private Label scoreLbl;

    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static Statistics _statistics = Statistics.instance();

    /**
     * This action method returns the application to the menu screen once the "return" button is pressed. This is done
     * by calling the AbstractController method popChild() twice, removing the top two children scenes of the stack.
     */
    @FXML
    private void returnBtnAction() {
        _modeDirector.setIteration(0);
        popChild();
        popChild();
    }

    /**
     * This method is invoked when the "home" button is pressed, it calls pushPopup method from AbstractController
     * which loads the "are you sure" popup.
     */
    public void mainMenu(){
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method was overridden in order to intialise certain labels and variables based on the state of the application.
     * As well as this the method also updates the statistics with the use of the Statistics class, including high
     * scores, recent questions total number of questions and total number of quizzes.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Gets the overall score of the current quiz
        int score = _modeDirector.getScore();
        scoreLbl.setText(score + "/" + _modeDirector.getNumQuestions());

        // Updates addition statistics
        if (_modeDirector.getMode() == ModeDirector.Mode.MATH_ADD) {
            if (score > _statistics.getHighScoreAdd()) {
                _statistics.setHighScoreAdd(score);
            }
            _statistics.setAddQuestionsAnswered(_statistics.getAddQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setAddQuizzesCompleted(_statistics.getAddQuizzesCompleted() + 1);
            _statistics.addRecentScoresAdd(score);
        }
        // Updates subtraction statistics
        else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_SUB) {
            if (score > _statistics.getHighScoreSub()) {
                _statistics.setHighScoreSub(score);
            }
            _statistics.addRecentScoresSub(score);
            _statistics.setSubQuestionsAnswered(_statistics.getSubQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setSubQuizzesCompleted(_statistics.getSubQuizzesCompleted() + 1);
        }
        // Updates division statistics
        else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_DIV) {
            if (score > _statistics.getHighScoreDiv()) {
                _statistics.setHighScoreDiv(score);
            }
            _statistics.setDivQuestionsAnswered(_statistics.getDivQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setDivQuizzesCompleted(_statistics.getDivQuizzesCompleted() + 1);
            _statistics.addRecentScoresDiv(score);
        }
        // Updates multiplication statistics
        else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_MULT) {
            if (score > _statistics.getHighScoreMult()) {
                _statistics.setHighScoreMult(score);
            }
            _statistics.setMultQuestionsAnswered(_statistics.getMultQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setMultQuizzesCompleted(_statistics.getMultQuizzesCompleted() + 1);
            _statistics.addRecentScoresMult(score);
        }

        // Updates the save files of the statistics
        _modeDirector.updateStats(score, _modeDirector.getNumQuestions());
        _statistics.writeToFileHighScores();
        _statistics.writeToFileRecentScores();

    }
}
