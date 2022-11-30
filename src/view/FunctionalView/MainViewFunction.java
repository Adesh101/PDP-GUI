package view.FunctionalView;

import java.awt.event.ActionListener;

/**
 * A Public interface for MainViewFunction.
 */
public interface MainViewFunction {

  /**
   * Add the provided listener.
   * @param listener provided listener.
   */
  void addActionListener(ActionListener listener);

  /**
   * Reset the focus on the appropriate part of the view.
   */
  void resetFocus();
}
