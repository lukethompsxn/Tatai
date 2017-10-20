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
    private Label highScoreLbl;
    @FXML
    private Label averageLbl;
    @FXML
    private Label typeLbl;

    @FXML
    private Label questionsAnsweredLbl;
    @FXML
    private Label quizzesCompletedLbl;


    @FXML
    private Button addStatsBtn;
    @FXML
    private Button subStatsBtn;
    @FXML
    private Button multStatsBtn;
    @FXML
    private Button divStatsBtn;

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

    //private XYChart.Series<String, Integer> addSeries = new XYChart.Series<>();
    //private XYChart.Series<String, Integer> subSeries = new XYChart.Series<>();
    //private XYChart.Series<String, Integer> divSeries = new XYChart.Series<>();
    //private XYChart.Series<String, Integer> multSeries = new XYChart.Series<>();

    @FXML
    private void addStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresAdd();
        //if (!addPressed) {
        //    addSeries = new XYChart.Series<>();
        //}
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        //series.setName("add");

        int i = 0;

        for (int score : recentScores) {
            series.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

        //barChart.getData().add(series);

        barChart.getData().clear();
        if (!addPressed) {
            barChart.getData().add(series);
            addPressed = true;
        } else {
            barChart.getData().clear();
            addPressed = false;
        }

        questionsAnsweredLbl.setText(Integer.toString(_statistics.getAddQuestionsAnswered()));
        quizzesCompletedLbl.setText(Integer.toString(_statistics.getAddQuizzesCompleted()));
        highScoreLbl.setText(Integer.toString(_statistics.get_highScoreAdd()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_ADD) + "%");
        typeLbl.setText("Addition");
    }

    @FXML
    private void subStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresSub();
        //if (!subPressed) {
        //    subSeries = new XYChart.Series<>();
        //}
        //subSeries.setName("sub");
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        int i = 0;

        for (int score : recentScores) {
            series.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

        barChart.getData().clear();
        if (!subPressed) {
            barChart.getData().add(series);
            subPressed = true;
        } else {
            barChart.getData().clear();
            subPressed = false;
        }

        questionsAnsweredLbl.setText(Integer.toString(_statistics.getSubQuestionsAnswered()));
        quizzesCompletedLbl.setText(Integer.toString(_statistics.getSubQuizzesCompleted()));
        highScoreLbl.setText(Integer.toString(_statistics.get_highScoreSub()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_SUB) + "%");
        typeLbl.setText("Subtraction");
    }

    @FXML
    private void multStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresMult();
        //if (!multPressed) {
        //    multSeries = new XYChart.Series<>();
        //}
        //multSeries.setName("mult");
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        int i = 0;

        System.out.println(recentScores.size());
        for (int score : recentScores) {
            series.getData().add(new XYChart.Data<>(xRange.get(i), score));
            System.out.println(score + " " + i);
            i++;
        }

        barChart.getData().clear();
        if (!multPressed) {
            barChart.getData().add(series);
            multPressed = true;
        } else {
            barChart.getData().clear();
            multPressed = false;
        }

        questionsAnsweredLbl.setText(Integer.toString(_statistics.getMultQuestionsAnswered()));
        quizzesCompletedLbl.setText(Integer.toString(_statistics.getMultQuizzesCompleted()));
        highScoreLbl.setText(Integer.toString(_statistics.get_highScoreMult()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_MULT) + "%");
        typeLbl.setText("Multiplication");
    }

    @FXML
    private void divStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.get_recentScoresDiv();
        //if (!divPressed) {
        //    divSeries = new XYChart.Series<>();
        //}
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        //divSeries.setName("div");

        int i = 0;
        for (int score : recentScores) {
            series.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

        barChart.getData().clear();
        if (!divPressed) {
            barChart.getData().add(series);
            divPressed = true;
        } else {
            barChart.getData().clear();
            divPressed = false;
        }

        questionsAnsweredLbl.setText(Integer.toString(_statistics.getDivQuestionsAnswered()));
        quizzesCompletedLbl.setText(Integer.toString(_statistics.getDivQuizzesCompleted()));
        highScoreLbl.setText(Integer.toString(_statistics.get_highScoreDiv()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_DIV) + "%");
        typeLbl.setText("Division");
    }

    //private void setSavedScores() {
    //    addHighScoreLbl.setText("Add High Score: " + Integer.toString(_statistics.get_highScoreAdd()));
    //    addAverageLbl.setText("Add Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_ADD) + "%");
    //    subHighScoreLbl.setText("Sub High Score: " + Integer.toString(_statistics.get_highScoreSub()));
    //    subAverageLbl.setText("Sub Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_SUB) + "%");
    //    multHighScoreLbl.setText("Mult High Score: " + Integer.toString(_statistics.get_highScoreMult()));
    //    multAverageLbl.setText("Mult Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_MULT) + "%");
    //   divHighScoreLbl.setText("Div High Score: " + Integer.toString(_statistics.get_highScoreDiv()));
    //   divAverageLbl.setText("Div Average Score: " + _modeDirector.getStats(ModeDirector.Mode.MATH_DIV) + "%");
    //    questionsAnsweredLbl.setText("Total Questions Answered: " + _statistics.getQuestionsAnswered());
    //    quizzesCompletedLbl.setText("Total Quizzes Completed: " + _statistics.getQuizzesCompleted());
    //}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPressed = false;
        subPressed = false;
        multPressed = false;
        divPressed = false;

        barChart.setTitle("");
        _statistics.readFileHighScores();
        //_statistics.readFileRecentScores();
        //setSavedScores();
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
