package view.textfieldview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A public class for investment strategy.
 */
public class InvestmentStrategy extends JFrame implements TextField {

  private JLabel displayText;
  private JButton dollarCostAveraging;
  private JButton home;

  /**
   * A public constructor for InvestmentStrategy.
   *
   * @param caption string
   */
  public InvestmentStrategy(String caption) {
    super(caption);
    this.setPreferredSize(new Dimension(500, 350));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400, 200));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    dollarCostAveraging = new JButton("Dollar Cost Averaging");
    home = new JButton("Home");
    dollarCostAveraging.setActionCommand("dollarCostAveraging");
    home.setActionCommand("strategyHomeButton");
    buttonPanel.add(dollarCostAveraging);
    buttonPanel.add(home);

    this.add(buttonPanel, BorderLayout.PAGE_START);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    dollarCostAveraging.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return null;
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearInput() {
    System.out.println("This will print a message");
  }
}
