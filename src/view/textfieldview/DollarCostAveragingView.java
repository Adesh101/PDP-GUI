package view.textfieldview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A public class for Dollar Cost Averaging view.
 */
public class DollarCostAveragingView extends JFrame implements TextField {

  private JLabel displayText;
  private JButton existingPortfolioStrategy;
  private JButton newPortfolioWithFiniteRange;
  private JButton newPortfolioWithoutEndDate;
  private JButton home;

  /**
   * A public constructor for DollarCostAveragingview.
   * @param caption string
   */
  public DollarCostAveragingView(String caption){
    super(caption);
    this.setPreferredSize(new Dimension(500,350));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400,200));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    existingPortfolioStrategy = new JButton("Strategy on Existing Portfolio");
    newPortfolioWithFiniteRange = new JButton("Strategy on New Portfolio having"
        + " Start/End Date");
    newPortfolioWithoutEndDate = new JButton("Strategy on New Portfolio"
        + " having only Start Date");
    home = new JButton("Home");
    existingPortfolioStrategy.setActionCommand("existingPortfolioDCA");
    newPortfolioWithFiniteRange.setActionCommand("newPortfolioWithFiniteRangeDCA");
    newPortfolioWithoutEndDate.setActionCommand("newPortfolioWithoutEndDateDCA");
    home.setActionCommand("dollarCostAveragingHome");

    buttonPanel.add(existingPortfolioStrategy);
    buttonPanel.add(newPortfolioWithFiniteRange);
    buttonPanel.add(newPortfolioWithoutEndDate);
    buttonPanel.add(home);

    this.add(displayPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.PAGE_START);

    this.getContentPane().add(buttonPanel);
    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);


//    JPanel panelStructure = new JPanel(new GridLayout(4, 4));
//    panelStructure.add(buttonPanel);
//
//    this.add(panelStructure, BorderLayout.PAGE_START);
//    this.add(displayPanel, BorderLayout.CENTER);
//
//    this.pack();
//    setLocationRelativeTo(null);
//    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    existingPortfolioStrategy.addActionListener(listener);
    newPortfolioWithFiniteRange.addActionListener(listener);
    newPortfolioWithoutEndDate.addActionListener(listener);
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
  public void clearField() {

  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
