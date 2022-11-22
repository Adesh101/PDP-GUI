package view;

import controller.GUIController;

public interface IView {
  /**
   * A method to print welcome message.
   */

  void showWelcomeMessage();

  /**
   * A method to print error message.
   */

  void showError();

  /**
   * A method to print the menu.
   */

  void showMenu();

  void addFeatures(GUIController guiController);
}
