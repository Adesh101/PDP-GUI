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
 * A public class to add stocks for DCA.
 */
public class AddStockDCARecurringFinite extends JFrame implements TextField {
  private JLabel stockTickerText;
  private JLabel proportionsText;
  private JLabel commissionFeeText;
  private JTextField stockTicker;
  private JTextField proportions;
  private JTextField commissionFee;
  private JLabel displayText;
  private JButton next;
  private JButton implementStrategy;
  private JButton home;

  /**
   * A public constructor for AddStockDCARecurringFinite.
   * @param caption string
   */
  public AddStockDCARecurringFinite(String caption){
    super(caption);
    this.setPreferredSize(new Dimension(500,350));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel firstPanel = new JPanel();
    stockTickerText = new JLabel("Stock Name: ");
    stockTickerText.setPreferredSize(new Dimension(100,20));
    stockTicker = new JTextField(20);
    firstPanel.add(stockTickerText);
    firstPanel.add(stockTicker);

    JPanel secondPanel = new JPanel();
    proportionsText = new JLabel("Weightage: ");
    proportionsText.setPreferredSize(new Dimension(100,20));
    proportions = new JTextField(20);
    secondPanel.add(proportionsText);
    secondPanel.add(proportions);

    JPanel thirdPanel = new JPanel();
    commissionFeeText = new JLabel("Commission Fee: ");
    commissionFeeText.setPreferredSize(new Dimension(100,20));
    commissionFee = new JTextField(20);
    thirdPanel.add(commissionFeeText);
    thirdPanel.add(commissionFee);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400,20));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    next = new JButton("Next Stock");
    implementStrategy = new JButton("Implement Strategy");
    home = new JButton("Home");
    next.setActionCommand("selectStocksFinite");
    implementStrategy.setActionCommand("implementStrategyDCARecurringFinite");
    home.setActionCommand("AddStockDCAHomeButton");
    buttonPanel.add(next);
    buttonPanel.add(implementStrategy);
    buttonPanel.add(home);


    JPanel panelStructure = new JPanel(new GridLayout(4,1));
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
    next.addActionListener(listener);
    implementStrategy.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return stockTicker.getText() + ":" + proportions.getText() + ":" + commissionFee.getText();

  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearField() {
    stockTicker.setText("");
    proportions.setText("");
    commissionFee.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
