package controller;

public class AreYouSurePopupController extends AbstractController  {

    /**
     * This method is the action for the "Home" button in the "Are you sure" pop up. It calls methods from abstract
     * controller the close the pop up, and push the child "MenuView" in order to return to the main menu.
     */
    public void home() {
        closePopup();
        pushChild("MenuView");
    }

    /**
     * This method is the action for the "Cancel" button in the "Are you sure" pop up. It calls the method closePop()
     * from the abstract controller.
     */
    public void cancel() {
        closePopup();
    }
}
