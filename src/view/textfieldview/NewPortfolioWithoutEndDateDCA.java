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
 * A public class for DCA for new portfolio for infinite range.
 */
public class NewPortfolioWithoutEndDateDCA extends JFrame implements TextField {
  private JLabel portfolioNameText;
  //private JLabel stockTickerText;
  private JLabel amountText;
  //private JLabel proportionsText;
  private JLabel startDateText;
  private JLabel intervalText;
  //private JLabel commissionFeeText;
  private JTextField portfolioName;
  //private JTextField stockTicker;
  private JTextField amount;
  //private JTextField proportions;
  private JTextField startDate;
  private JTextField interval;
  //private JTextField commissionFee;
  private JLabel displayText;
  //private JButton implementStrategy;
  private JButton addStocks;
  private JButton home;

  /**
   * A public constructor for NewPortfolioWithoutEndDateDCA.
   * @param caption string
   */
  public NewPortfolioWithoutEndDateDCA(String caption){
    super(caption);
    this.setPreferredSize(new Dimension(500,350));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel firstPanel = new JPanel();
    portfolioNameText = new JLabel("Portfolio Name: ");
    portfolioNameText.setPreferredSize(new Dimension(100,20));
    portfolioName = new JTextField(20);
    firstPanel.add(portfolioNameText);
    firstPanel.add(portfolioName);


    JPanel thirdPanel = new JPanel();
    amountText = new JLabel("Total Amount: ");
    amountText.setPreferredSize(new Dimension(100,20));
    amount = new JTextField(20);
    thirdPanel.add(amountText);
    thirdPanel.add(amount);


    JPanel fifthPanel = new JPanel();
    startDateText = new JLabel("Start Date: ");
    startDateText.setPreferredSize(new Dimension(100,20));
    startDate = new JTextField(20);
    fifthPanel.add(startDateText);
    fifthPanel.add(startDate);


    JPanel sixthPanel = new JPanel();
    intervalText= new JLabel("Interval in Days: ");
    intervalText.setPreferredSize(new Dimension(100,20));
    interval = new JTextField(20);
    sixthPanel.add(intervalText);
    sixthPanel.add(interval);


    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400,20));
    displayPanel.add(displayText);

    JPanel buttonPanel =new JPanel();
    //implementStrategy = new JButton("Implement Strategy");
    addStocks = new JButton("Select Stocks");
    home = new JButton("Home");
    //implementStrategy.setActionCommand("newPortfolioWithoutEndDateDCA");
    addStocks.setActionCommand("selectStocksInfiniteRecurring");
    home.setActionCommand("newPortfolioWithoutEndDateDCAHomeButton");
    //buttonPanel.add(implementStrategy);
    buttonPanel.add(addStocks);
    buttonPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(9,1));
    panelStructure.add(firstPanel);
    //panelStructure.add(secondPanel);
    panelStructure.add(thirdPanel);
    //panelStructure.add(fourthPanel);
    panelStructure.add(fifthPanel);
    panelStructure.add(sixthPanel);
    //panelStructure.add(seventhPanel);

    this.add(panelStructure, BorderLayout.PAGE_START);
    this.add(displayPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);

  }
  @Override
  public void addActionListener(ActionListener listener) {
    //implementStrategy.addActionListener(listener);
    addStocks.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + amount.getText() + ":"  + startDate.getText() + ":" + interval.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearField() {
    portfolioName.setText("");
    //stockTicker.setText("");
    amount.setText("");
    //proportions.setText("");
    startDate.setText("");
    interval.setText("");
    //commissionFee.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
