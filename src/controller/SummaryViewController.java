package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ModeDirector;
import model.Statistics;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SummaryViewController extends AbstractController implements Initializable {

    @FXML
    private Button returnBtn;
    @FXML
    private Label scoreLbl;

    private static ModeDirector _modeDirector = ModeDirector.instance();
    private static Statistics _statistics = Statistics.instance();

    @FXML
    private void returnBtnAction() {
        _modeDirector.setIteration(0);
        popChild();
        popChild();
    }


    public void mainMenu(){
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "AreYouSurePopup.fxml"))),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int score = _modeDirector.getScore();
        scoreLbl.setText(score + "/" + _modeDirector.getNumQuestions());
        //_statistics.readFileRecentScores();

        if (_modeDirector.getMode() == ModeDirector.Mode.MATH_ADD) {
            if (score > _statistics.get_highScoreAdd()) {
                _statistics.set_highScoreAdd(score);
            }
            _statistics.setAddQuestionsAnswered(_statistics.getAddQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setAddQuizzesCompleted(_statistics.getAddQuizzesCompleted() + 1);
            _statistics.addRecentScoresAdd(score);
        } else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_SUB) {
            if (score > _statistics.get_highScoreSub()) {
                _statistics.set_highScoreSub(score);
            }
            _statistics.addRecentScoresSub(score);
            _statistics.setSubQuestionsAnswered(_statistics.getSubQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setSubQuizzesCompleted(_statistics.getSubQuizzesCompleted() + 1);
        } else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_DIV) {
            if (score > _statistics.get_highScoreDiv()) {
                _statistics.set_highScoreDiv(score);
            }
            _statistics.setDivQuestionsAnswered(_statistics.getDivQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setDivQuizzesCompleted(_statistics.getDivQuizzesCompleted() + 1);
            _statistics.addRecentScoresDiv(score);
        } else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_MULT) {
            if (score > _statistics.get_highScoreMult()) {
                _statistics.set_highScoreMult(score);
            }
            _statistics.setMultQuestionsAnswered(_statistics.getMultQuestionsAnswered() + _modeDirector.getNumQuestions());
            _statistics.setMultQuizzesCompleted(_statistics.getMultQuizzesCompleted() + 1);
            _statistics.addRecentScoresMult(score);
        }
        //_statistics.setQuestionsAnswered(_statistics.getQuestionsAnswered() + _modeDirector.getNumQuestions());
        //_statistics.setQuizzesCompleted(_statistics.getQuizzesCompleted() + 1);
        _statistics.writeToFileHighScores();
        _statistics.writeToFileRecentScores();

    }
}
