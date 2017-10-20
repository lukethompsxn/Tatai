package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import model.ModeDirector;
import model.Statistics;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;

public class StatsViewController extends AbstractController implements Initializable {
    private static ModeDirector _modeDirector = ModeDirector.instance();

    @FXML
    Label addLbl;
    @FXML
    Label subLbl;
    @FXML
    Label multLbl;
    @FXML
    Label divLbl;

    @FXML
    private Label addHighScoreLbl;
    @FXML
    private Label subHighScoreLbl;
    @FXML
    private Label multHighScoreLbl;
    @FXML
    private Label divHighScoreLbl;
    @FXML
    private Label addAverageLbl;
    @FXML
    private Label subAverageLbl;
    @FXML
    private Label multAverageLbl;
    @FXML
    private Label divAverageLbl;
    @FXML
    private Label questionsAnsweredLbl;
    @FXML
    private Label quizzesCompletedLbl;


    @FXML
    private ToggleButton addStatsBtn;
    @FXML
    private ToggleButton subStatsBtn;
    @FXML
    private ToggleButton multStatsBtn;
    @FXML
    private ToggleButton divStatsBtn;

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> xRange = FXCollections.observableArrayList();
    private static Statistics _statistics = Statistics.instance();
    private boolean addPressed;
    private boolean subPressed;
    private boolean multPressed;
    private boolean divPressed;

    private XYChart.Series<String, Integer> addSeries = new XYChart.Series<>();
    private XYChart.Series<String, Integer> subSeries = new XYChart.Series<>();
    private XYChart.Series<String, Integer> divSeries = new XYChart.Series<>();
    private XYChart.Series<String, Integer> multSeries = new XYChart.Series<>();

    @FXML
    private void addStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresAdd();
        if (!addPressed) {
            addSeries = new XYChart.Series<>();
        }

        addSeries.setName("add");

        int i = 0;

        for (int score : recentScores) {
            addSeries.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

        if (!addPressed) {
            barChart.getData().add(addSeries);
            addPressed = true;
        } else {
            barChart.getData().removeAll(addSeries);
            addPressed = false;
        }

        addHighScoreLbl.setText("Add High Score: " + Integer.toString(_statistics.get_highScoreAdd()));
        addAverageLbl.setText("Add Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_ADD) + "%");
    }

    @FXML
    private void subStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresSub();
        if (!subPressed) {
            subSeries = new XYChart.Series<>();
        }
        subSeries.setName("sub");

        int i = 0;

        for (int score : recentScores) {
            subSeries.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

        if (!subPressed) {
            barChart.getData().add(subSeries);
            subPressed = true;
        } else {
            barChart.getData().removeAll(subSeries);
            subPressed = false;
        }

        subHighScoreLbl.setText("Sub High Score: " + Integer.toString(_statistics.get_highScoreSub()));
        subAverageLbl.setText("Sub Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_SUB) + "%");
    }

    @FXML
    private void multStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresMult();
        if (!multPressed) {
            multSeries = new XYChart.Series<>();
        }
        multSeries.setName("mult");

        int i = 0;

        for (int score : recentScores) {
            multSeries.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

        if (!multPressed) {
            barChart.getData().add(multSeries);
            multPressed = true;
        } else {
            barChart.getData().removeAll(multSeries);
            multPressed = false;
        }

        multHighScoreLbl.setText("Mult High Score: " + Integer.toString(_statistics.get_highScoreMult()));
        multAverageLbl.setText("Mult Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_MULT) + "%");
    }

    @FXML
    private void divStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresDiv();
        if (!divPressed) {
            divSeries = new XYChart.Series<>();
        }

        divSeries.setName("div");

        int i = 0;
        for (int score : recentScores) {
            divSeries.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

        if (!divPressed) {
            barChart.getData().add(divSeries);
            divPressed = true;
        } else {
            barChart.getData().removeAll(divSeries);
            divPressed = false;
        }

        divHighScoreLbl.setText("Div High Score: " + Integer.toString(_statistics.get_highScoreDiv()));
        divAverageLbl.setText("Div Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_DIV) + "%");
    }

    private void setSavedScores() {
        addHighScoreLbl.setText("Add High Score: " + Integer.toString(_statistics.get_highScoreAdd()));
        addAverageLbl.setText("Add Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_ADD) + "%");
        subHighScoreLbl.setText("Sub High Score: " + Integer.toString(_statistics.get_highScoreSub()));
        subAverageLbl.setText("Sub Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_SUB) + "%");
        multHighScoreLbl.setText("Mult High Score: " + Integer.toString(_statistics.get_highScoreMult()));
        multAverageLbl.setText("Mult Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_MULT) + "%");
        divHighScoreLbl.setText("Div High Score: " + Integer.toString(_statistics.get_highScoreDiv()));
        divAverageLbl.setText("Div Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_DIV) + "%");
        questionsAnsweredLbl.setText("Total Questions Answered: " + _statistics.getQuestionsAnswered());
        quizzesCompletedLbl.setText("Total Quizzes Completed: " + _statistics.getQuizzesCompleted());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPressed = false;
        subPressed = false;
        multPressed = false;
        divPressed = false;

        barChart.setTitle("Recent Scores: ");
        _statistics.readFileHighScores();
        _statistics.readFileRecentScores();
        setSavedScores();
        String[] array = {Integer.toString(1),Integer.toString(2),Integer.toString(3),Integer.toString(4),
                Integer.toString(5),Integer.toString(6),Integer.toString(7),Integer.toString(8),
                Integer.toString(9),Integer.toString(10)};
        xRange.addAll(Arrays.asList(array));
        xAxis.setCategories(xRange);
    }

    public void returnToMathView() {
        popChild();
    }
}
