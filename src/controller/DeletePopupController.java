package controller;

import javafx.collections.ObservableList;
import model.CustomCollection;

/**
 * @author Joel Clarke, Luke Thompson
 */
public class DeletePopupController extends AbstractController  {
    private static CustomCollection _customCollection = CustomCollection.instance();
    private ObservableList _list;
    private Object _item;

    /**
     * Constructor so that the observable list and the currently selected item can be passed into this controller.
     * This is so that if the user selects delete, the item is able to be deleted.
     * @param list
     * @param item
     */
    public DeletePopupController(ObservableList list, Object item) {
        _list = list;
        _item = item;
    }

    //Default Constructor
    public DeletePopupController() { }

    /**
     * Action for the delete button. It first closes the pop up by calling the method from abstract controller,
     * then using the observable list which was passed into the constructor it removes the item, then calls a method
     * from custom collection in order to delete the list from the stored lists file
     */
    public void delete() {
        closePopup();
        _list.remove(_item);
        _customCollection.deleteCustomList(_item.toString());
    }

    //Action for the cancel button. This method closes the pop up
    public void cancel() {
        closePopup();
    }
}
