package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ModeDirector;
import model.Statistics;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int score = _modeDirector.getScore();
        scoreLbl.setText("You scored " + score + "/" + _modeDirector.getNumQuestions());
        _statistics.readFileRecentScores();

        if (_modeDirector.getMode() == ModeDirector.Mode.MATH_ADD) {
            if (score > _statistics.get_highScoreAdd()) {
                _statistics.set_highScoreAdd(score);
            }
            _statistics.addRecentScoresAdd(score);
        } else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_SUB) {
            if (score > _statistics.get_highScoreSub()) {
                _statistics.set_highScoreSub(score);
            }
            _statistics.addRecentScoresSub(score);
        } else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_DIV) {
            if (score > _statistics.get_highScoreDiv()) {
                _statistics.set_highScoreDiv(score);
            }
            _statistics.addRecentScoresDiv(score);
        } else if (_modeDirector.getMode() == ModeDirector.Mode.MATH_MULT) {
            if (score > _statistics.get_highScoreMult()) {
                _statistics.set_highScoreMult(score);
            }
            _statistics.addRecentScoresMult(score);
        }
        _statistics.setQuestionsAnswered(_statistics.getQuestionsAnswered() + _modeDirector.getNumQuestions());
        _statistics.setQuizzesCompleted(_statistics.getQuizzesCompleted() + 1);
        _statistics.writeToFileHighScores();
        _statistics.writeToFileRecentScores();

    }
}
