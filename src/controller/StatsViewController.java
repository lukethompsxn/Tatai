package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ModeDirector;
import model.Statistics;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.ResourceBundle;

public class StatsViewController extends AbstractController implements Initializable {
    private static ModeDirector _modeDirector = ModeDirector.instance();

    private ObservableList<String> xRange = FXCollections.observableArrayList();
    private static Statistics _statistics = Statistics.instance();

    private boolean addPressed;
    private boolean subPressed;
    private boolean multPressed;
    private boolean divPressed;

    //Sign Labels
    @FXML
    Label addLbl;
    @FXML
    Label subLbl;
    @FXML
    Label multLbl;
    @FXML
    Label divLbl;

    //Regular Labels
    @FXML
    private Label highScoreLbl;
    @FXML
    private Label averageLbl;
    @FXML
    private Label typeLbl;

    //Answered, Completed Labels
    @FXML
    private Label questionsAnsweredLbl;
    @FXML
    private Label quizzesCompletedLbl;

    // Graph variables
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;

    /**
     * This action method takes all the addition statistic of the application and applies them to the StatsView to
     * be seen. This includes 10 most recent scores, high score, average score, total questions answered and total
     * quizzes completed.
     */
    @FXML
    private void addStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.getRecentScoresAdd();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        int i = 0;

        for (int score : recentScores) {
            series.getData().add(new XYChart.Data<>(xRange.get(i), score));
            i++;
        }

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
        highScoreLbl.setText(Integer.toString(_statistics.getHighScoreAdd()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_ADD) + "%");
        typeLbl.setText("Addition");
    }

    /**
     * This action method takes all the addition statistic of the subtraction and applies them to the StatsView to
     * be seen. This includes 10 most recent scores, high score, average score, total questions answered and total
     * quizzes completed.
     */
    @FXML
    private void subStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.getRecentScoresSub();
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
        highScoreLbl.setText(Integer.toString(_statistics.getHighScoreSub()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_SUB) + "%");
        typeLbl.setText("Subtraction");
    }

    /**
     * This action method takes all the multiplication statistic of the application and applies them to the StatsView to
     * be seen. This includes 10 most recent scores, high score, average score, total questions answered and total
     * quizzes completed.
     */
    @FXML
    private void multStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.getRecentScoresMult();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        int i = 0;

        for (int score : recentScores) {
            series.getData().add(new XYChart.Data<>(xRange.get(i), score));
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
        highScoreLbl.setText(Integer.toString(_statistics.getHighScoreMult()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_MULT) + "%");
        typeLbl.setText("Multiplication");
    }

    /**
     * This action method takes all the division statistic of the application and applies them to the StatsView to
     * be seen. This includes 10 most recent scores, high score, average score, total questions answered and total
     * quizzes completed.
     */
    @FXML
    private void divStatsBtnAction() {

        LinkedList<Integer> recentScores = _statistics.getRecentScoresDiv();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

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
        highScoreLbl.setText(Integer.toString(_statistics.getHighScoreDiv()));
        averageLbl.setText(_modeDirector.getStats(ModeDirector.Mode.MATH_DIV) + "%");
        typeLbl.setText("Division");
    }

    /**
     * This method was overridden in order to intialise certain labels and buttons based on the state of the application.
     * As well as this the method also reads the file containing the save high score information and sets up one of the
     * axis required for the recent scores graph.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            pushPopup(new Scene(FXMLLoader.load(getClass().getResource(File.separator + "view" + File.separator + "ParentsHelpPopup.fxml"))),false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addPressed = false;
        subPressed = false;
        multPressed = false;
        divPressed = false;

        barChart.setTitle("");
        _statistics.readFileHighScores();

        String[] array = {Integer.toString(1),Integer.toString(2),Integer.toString(3),Integer.toString(4),
                Integer.toString(5),Integer.toString(6),Integer.toString(7),Integer.toString(8),
                Integer.toString(9),Integer.toString(10)};
        xRange.addAll(Arrays.asList(array));
        xAxis.setCategories(xRange);
    }

    //Action for the "home" button. This method calls a method from abstract controller to return to the main menu
    public void mainMenu() {
        pushChild("MenuView");
    }
}
