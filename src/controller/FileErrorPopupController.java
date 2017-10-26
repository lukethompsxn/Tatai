package controller;

import model.CustomCollection;

import java.io.File;

public class FileErrorPopupController extends AbstractController  {
    private static CustomCollection _customModel = CustomCollection.instance();

    //Action for the close button. This method calls a method from abstract controller in order to close the pop up
    public void close() {
        closePopup();
    }

    public void fix() {
        File file = new File("data" + File.separator + "CustomQuestions.txt");
        file.delete();

        _customModel.reset();
        closePopup();
    }
}
