package view.TextFieldView;

import java.awt.event.ActionListener;

/**
 * A public interface for TextField.
 */
public interface TextField {
  /**
   * Add a listener to button
   * @param: listener the provided listener.
   */
  void addActionListener(ActionListener listener);

  /**
   * Get the content of text field that user typed.
   * @return: the content of text field that user typed.
   */
  String getInput();

  /**
   * Take a given a message, and show it on the view.
   * @param: message A given string message.
   */
  void setHintMess(String message);

  /**
   * Clear the text field.
   */
  void clearField();

  /**
   * Reset focus.
   */
  void resetFocus();
}