package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

public abstract class AbstractController {
    Pane _mainPane;
    Stack<Parent> _parentStack = new Stack<>();

    //Called from subclasses to set a new scene inside the container
    protected void pushChild(String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(File.separator + "view" + File.separator + name + ".fxml"));
            Parent root = loader.load();
            _parentStack.add(root);
            _mainPane.getChildren().setAll(_parentStack.peek());
            //_mainPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Called from subclasses to return to the previous scene inside the container
    protected void popChild() {
        _parentStack.pop();
        _mainPane.getChildren().setAll(_parentStack.peek());
    }
}
