package view.TextFieldView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A public class for Reading Portfolio.
 */
public class ReadPortfolio extends JFrame implements TextField {

  private JLabel portfolioNameText;
  private JLabel displayText;

  private JTextField portfolioName;

  private JButton readPortfolio;
  private JButton home;

  /**
   * A public constructor for ReadPortfolio.
   * @param caption string
   */
  public ReadPortfolio(String caption) {
    super(caption);
    this.setPreferredSize(new Dimension(450, 300));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel firstPanel = new JPanel();
    portfolioNameText = new JLabel("Portfolio Name: ");
    portfolioName = new JTextField(30);
    firstPanel.add(portfolioNameText);
    firstPanel.add(portfolioName);

    JPanel secondPanel = new JPanel();
    readPortfolio = new JButton("Read Portfolio");
    home = new JButton("Home");
    readPortfolio.setActionCommand("readPortfolioButton");
    home.setActionCommand("readHomeButton");
    secondPanel.add(readPortfolio);
    secondPanel.add(home);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayPanel.add(displayText);

    JPanel panelStructure = new JPanel(new GridLayout(1, 1));
    panelStructure.add(firstPanel);

    this.add(panelStructure, BorderLayout.PAGE_START);
    this.add(displayPanel, BorderLayout.CENTER);
    this.add(secondPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    readPortfolio.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearField() {
    readPortfolio.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
