package view.textfieldview;

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
 * A public class for ShowPortfolioPerformance.
 */
public class ShowPortfolioPerformance extends JFrame implements TextField {

  private JTextField portfolioName;
  private JTextField startDate;
  private JTextField endDate;
  private JLabel displayText;
  private JButton showPortfolio;
  private JButton home;

  /**
   * A public constructor for ShowPortfolioPerformance.
   *
   * @param caption string
   */
  public ShowPortfolioPerformance(String caption) {
    super(caption);
    JLabel portfolioNameText;
    JLabel startDateText;
    JLabel endDateText;
    this.setPreferredSize(new Dimension(500, 350));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel firstPanel = new JPanel();
    portfolioNameText = new JLabel("Portfolio Name: ");
    portfolioNameText.setPreferredSize(new Dimension(100, 20));
    portfolioName = new JTextField(20);
    firstPanel.add(portfolioNameText);
    firstPanel.add(portfolioName);

    JPanel secondPanel = new JPanel();
    startDateText = new JLabel("Start Date: ");
    startDateText.setPreferredSize(new Dimension(100, 20));
    startDate = new JTextField(20);
    secondPanel.add(startDateText);
    secondPanel.add(startDate);

    JPanel thirdPanel = new JPanel();
    endDateText = new JLabel("End Date: ");
    endDateText.setPreferredSize(new Dimension(100, 20));
    endDate = new JTextField(20);
    thirdPanel.add(endDateText);
    thirdPanel.add(endDate);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(350, 20));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    showPortfolio = new JButton("Portfolio Performance");
    home = new JButton("Home");
    showPortfolio.setActionCommand("showPortfolioPerformanceButton");
    home.setActionCommand("showHomeButton");
    buttonPanel.add(showPortfolio);
    buttonPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(3, 1));
    panelStructure.add(firstPanel);
    panelStructure.add(secondPanel);
    panelStructure.add(thirdPanel);

    this.add(panelStructure, BorderLayout.PAGE_START);
    this.add(displayPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    showPortfolio.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + startDate.getText() + ":" + endDate.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearField() {
    startDate.setText("");
    endDate.setText("");
    portfolioName.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

//  public void showChart(TreeMap<String, Integer> map, String porfolioName, String startDate,
//      String endDate, int scale) {
//    StringBuilder sb = new StringBuilder();
//    sb.append("Performance of Portfolio " + porfolioName + " From " + startDate + " to " + endDate
//        + "\n");
//    for (String timestamp : map.keySet()) {
//      sb.append(timestamp + ": ");
//      for (int i = 0; i < map.get(timestamp); i++) {
//        sb.append("*");
//      }
//      sb.append("\n");
//    }
//    sb.append("\n");
//    sb.append("Scale: *= $" + scale);
//    setHintMess(sb.toString());
//  }
}
