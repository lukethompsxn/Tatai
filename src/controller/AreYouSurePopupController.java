package controller;

import javafx.collections.ObservableList;
import model.CustomCollection;

public class AreYouSurePopupController extends AbstractController  {

    public void home() {
        closePopup();
        pushChild("MenuView");
    }

    public void cancel() {
        closePopup();
    }
}
