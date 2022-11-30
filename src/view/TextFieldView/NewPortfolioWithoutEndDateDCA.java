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

public class NewPortfolioWithoutEndDateDCA extends JFrame implements TextField {
  private JLabel portfolioNameText;
  private JLabel stockTickerText;
  private JLabel amountText;
  private JLabel proportionsText;
  private JLabel startDateText;
  private JLabel intervalText;
  private JLabel commissionFeeText;
  private JTextField portfolioName;
  private JTextField stockTicker;
  private JTextField amount;
  private JTextField proportions;
  private JTextField startDate;
  private JTextField interval;
  private JTextField commissionFee;
  private JLabel displayText;
  private JButton implementStrategy;
  private JButton home;

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

    JPanel secondPanel = new JPanel();
    stockTickerText = new JLabel("Stock Name: ");
    stockTickerText.setPreferredSize(new Dimension(100,20));
    stockTicker = new JTextField(20);
    secondPanel.add(stockTickerText);
    secondPanel.add(stockTicker);

    JPanel thirdPanel = new JPanel();
    amountText = new JLabel("Total Amount to be invested: ");
    amountText.setPreferredSize(new Dimension(100,20));
    amount = new JTextField(20);
    thirdPanel.add(amountText);
    thirdPanel.add(amount);

    JPanel fourthPanel = new JPanel();
    proportionsText = new JLabel("Weightage: ");
    proportionsText.setPreferredSize(new Dimension(100,20));
    proportions = new JTextField(20);
    fourthPanel.add(proportionsText);
    fourthPanel.add(proportions);

    JPanel fifthPanel = new JPanel();
    startDateText = new JLabel("Start Date: ");
    startDateText.setPreferredSize(new Dimension(100,20));
    startDate = new JTextField(20);
    fifthPanel.add(startDateText);
    fifthPanel.add(startDate);


    JPanel sixthPanel = new JPanel();
    intervalText= new JLabel("Interval in days: ");
    intervalText.setPreferredSize(new Dimension(100,20));
    interval = new JTextField(20);
    sixthPanel.add(intervalText);
    sixthPanel.add(interval);

    JPanel seventhPanel = new JPanel();
    commissionFeeText = new JLabel("Commission Fee: ");
    commissionFeeText.setPreferredSize(new Dimension(100,20));
    commissionFee = new JTextField(20);
    seventhPanel.add(commissionFeeText);
    seventhPanel.add(commissionFee);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400,20));
    displayPanel.add(displayText);

    JPanel buttonPanel =new JPanel();
    implementStrategy = new JButton("Implement Strategy");
    home = new JButton("Home");
    implementStrategy.setActionCommand("newPortfolioWithoutEndDateDCA");
    home.setActionCommand("newPortfolioWithoutEndDateDCAHomeButton");
    buttonPanel.add(implementStrategy);
    buttonPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(9,1));
    panelStructure.add(firstPanel);
    panelStructure.add(secondPanel);
    panelStructure.add(thirdPanel);
    panelStructure.add(fourthPanel);
    panelStructure.add(fifthPanel);
    panelStructure.add(sixthPanel);
    panelStructure.add(seventhPanel);

    this.add(panelStructure, BorderLayout.PAGE_START);
    this.add(displayPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);

  }
  @Override
  public void addActionListener(ActionListener listener) {
    implementStrategy.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + stockTicker.getText()
        + ":" + amount.getText() + ":" + proportions.getText()
        + ":" + startDate.getText() + ":" + interval.getText()
        + ":" + commissionFee.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearField() {
    portfolioName.setText("");
    stockTicker.setText("");
    amount.setText("");
    proportions.setText("");
    startDate.setText("");
    interval.setText("");
    commissionFee.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}