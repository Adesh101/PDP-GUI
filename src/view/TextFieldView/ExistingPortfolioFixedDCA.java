package view.TextFieldView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A public class for DCA for existing portfolio.
 */
public class ExistingPortfolioFixedDCA extends JFrame implements TextField {
  private JLabel portfolioNameText;
//  private JLabel stockTickerText;
  private JLabel amountText;
  //private JLabel proportionsText;
  private JLabel strategyDateText;
  //private JLabel commissionFeeText;
  private JTextField portfolioName;
  //private JTextField stockTicker;
  private JTextField amount;
  //private JTextField proportions;
  private JTextField strategyDate;
  //private JTextField commissionFee;
  private JLabel displayText;
  private JButton addStock;
  //private JButton implementStrategy;
  private JButton home;

  /**
   * A public constructor for ExistingPortfolioFixeDCA.
   * @param caption string
   */
  public ExistingPortfolioFixedDCA(String caption){
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
    strategyDateText = new JLabel("Date: ");
    strategyDateText.setPreferredSize(new Dimension(100,20));
    strategyDate = new JTextField(20);
    fifthPanel.add(strategyDateText);
    fifthPanel.add(strategyDate);

//    JPanel secondPanel = new JPanel();
//    stockTickerText = new JLabel("Stock Name: ");
//    stockTickerText.setPreferredSize(new Dimension(100,20));
//    stockTicker = new JTextField(20);
//    secondPanel.add(stockTickerText);
//    secondPanel.add(stockTicker);
//
//    JPanel fourthPanel = new JPanel();
//    proportionsText = new JLabel("Weightage: ");
//    proportionsText.setPreferredSize(new Dimension(100,20));
//    proportions = new JTextField(20);
//    fourthPanel.add(proportionsText);
//    fourthPanel.add(proportions);
//
//    JPanel sixthPanel = new JPanel();
//    commissionFeeText = new JLabel("Commission Fee: ");
//    commissionFeeText.setPreferredSize(new Dimension(100,20));
//    commissionFee = new JTextField(20);
//    sixthPanel.add(commissionFeeText);
//    sixthPanel.add(commissionFee);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400,20));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    addStock = new JButton("Select Stocks");
    //implementStrategy = new JButton("Implement Strategy");
    home = new JButton("Home");
    addStock.setActionCommand("selectStocksExisting");
    //implementStrategy.setActionCommand("implementExistingPortfolioFixedDCA");
    home.setActionCommand("existingPortfolioFixedDCAHomeButton");
    buttonPanel.add(addStock);
    //buttonPanel.add(implementStrategy);
    buttonPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(7,1));
    panelStructure.add(firstPanel);
    panelStructure.add(thirdPanel);
    panelStructure.add(fifthPanel);
//    panelStructure.add(secondPanel);
//    panelStructure.add(fourthPanel);
//    panelStructure.add(sixthPanel);

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
    addStock.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + amount.getText() + ":" + strategyDate.getText();
    //+ ":" + stockTicker.getText() + ":" + proportions.getText() + ":" + commissionFee.getText();
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
    strategyDate.setText("");
    //commissionFee.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
