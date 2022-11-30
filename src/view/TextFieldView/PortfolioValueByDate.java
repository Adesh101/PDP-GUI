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

public class PortfolioValueByDate extends JFrame implements TextField {

  private JLabel portfolioNameText;
  private JLabel portfolioValueDateText;
  private JLabel displayText;

  private JTextField portfolioName;
  private JTextField portfolioValueDate;

  private JButton portfolioValue;
  private JButton home;

  public PortfolioValueByDate(String caption) {
    super(caption);
    this.setPreferredSize(new Dimension(500, 350));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel firstPanel = new JPanel();
    portfolioNameText = new JLabel("Portfolio Name: ");
    portfolioNameText.setPreferredSize(new Dimension(100, 20));
    portfolioName = new JTextField(20);
    firstPanel.add(portfolioNameText);
    firstPanel.add(portfolioName);

    JPanel secondPanel = new JPanel();
    portfolioValueDateText = new JLabel("Date: ");
    portfolioValueDateText.setPreferredSize(new Dimension(100, 20));
    portfolioValueDate = new JTextField(20);
    secondPanel.add(portfolioValueDateText);
    secondPanel.add(portfolioValue);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400, 20));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    portfolioValue = new JButton("Portfolio Value");
    home = new JButton("Home");
    portfolioValue.setActionCommand("portfolioValueByDateButton");
    home.setActionCommand("portfolioHomeButton");
    buttonPanel.add(portfolioValue);
    buttonPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(2, 1));
    panelStructure.add(firstPanel);
    panelStructure.add(secondPanel);

    this.add(panelStructure, BorderLayout.PAGE_START);
    this.add(displayPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    portfolioValue.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + portfolioValueDate.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText("");
  }

  @Override
  public void clearField() {
    portfolioValueDate.setText("");
    portfolioName.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
