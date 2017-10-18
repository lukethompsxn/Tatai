package controller;

import javafx.collections.ObservableList;
import model.CustomCollection;

public class DeletePopupController extends AbstractController  {
    private static CustomCollection _customCollection = CustomCollection.instance();
    private ObservableList _list;
    private Object _item;

    public DeletePopupController(ObservableList list, Object item) {
        _list = list;
        _item = item;
    }

    public DeletePopupController() {

    }

    public void delete() {
        closePopup();
        _list.remove(_item);
        _customCollection.deleteCustomList(_item.toString());
    }

    public void cancel() {
        closePopup();
    }
}
