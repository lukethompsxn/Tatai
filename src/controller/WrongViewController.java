package controller;

public class WrongViewController extends AbstractController {

    public void tryAgain() {
        popChild();
        //other logic
    }

    public void skipQuestion() {
        //other logic
        popChild();
    }

}
