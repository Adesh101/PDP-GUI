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
 * A public class to buy stocks.
 */
public class BuyStock extends JFrame implements TextField {

  private JLabel portfolioNameText;
  private JLabel stockTickerText;
  private JLabel stockQuantityText;
  private JLabel buyingDateText;
  private JLabel comissionFeeText;
  private JTextField portfolioName;
  private JTextField stockTicker;
  private JTextField stockQuantity;
  private JTextField buyingDate;
  private JTextField comissionFee;
  private JLabel displayText;
  private JButton addStock;
  private JButton home;

  /**
   * A public constructor for BuyStock.
   * @param caption string
   */
  public BuyStock(String caption) {
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
    stockTickerText = new JLabel("Ticker: ");
    stockTickerText.setPreferredSize(new Dimension(100, 20));
    stockTicker = new JTextField(20);
    secondPanel.add(stockTickerText);
    secondPanel.add(stockTicker);

    JPanel thirdPanel = new JPanel();
    stockQuantityText = new JLabel("Quantity: ");
    stockQuantityText.setPreferredSize(new Dimension(100, 20));
    stockQuantity = new JTextField(20);
    thirdPanel.add(stockQuantityText);
    thirdPanel.add(stockQuantity);

    JPanel fourthPanel = new JPanel();
    buyingDateText = new JLabel("Buying Date: ");
    buyingDateText.setPreferredSize(new Dimension(100, 20));
    buyingDate = new JTextField(20);
    fourthPanel.add(buyingDateText);
    fourthPanel.add(buyingDate);

    JPanel fifthPanel = new JPanel();
    comissionFeeText = new JLabel("Commission Fee: ");
    comissionFeeText.setPreferredSize(new Dimension(100, 20));
    comissionFee = new JTextField(20);
    fifthPanel.add(comissionFeeText);
    fifthPanel.add(comissionFee);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400, 20));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    addStock = new JButton("Add Stock");
    home = new JButton("Home");
    addStock.setActionCommand("addStock");
    home.setActionCommand("addHomeButton");
    buttonPanel.add(addStock);
    buttonPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(6, 1));
    panelStructure.add(firstPanel);
    panelStructure.add(secondPanel);
    panelStructure.add(thirdPanel);
    panelStructure.add(fourthPanel);
    panelStructure.add(fifthPanel);

    this.add(panelStructure, BorderLayout.PAGE_START);
    this.add(displayPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    addStock.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + stockTicker.getText() + ":" + stockQuantity.getText()
        + ":" + buyingDate.getText() + ":" + comissionFee.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearField() {
    portfolioName.setText("");
    stockTicker.setText("");
    stockQuantity.setText("");
    buyingDate.setText("");
    comissionFee.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
