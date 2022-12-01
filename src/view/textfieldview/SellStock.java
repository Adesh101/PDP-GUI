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
 * A public class for selling the stock.
 */
public class SellStock extends JFrame implements TextField {

  private JTextField portfolioName;
  private JTextField stockTicker;
  private JTextField stockQuantity;
  private JTextField sellingDate;
  private JTextField commissionFee;
  private JLabel displayText;
  private JButton sellStock;
  private JButton home;

  /**
   * A public constructor for SellStock.
   *
   * @param caption string
   */
  public SellStock(String caption) {
    super(caption);
    JLabel portfolioNameText;
    JLabel stockTickerText;
    JLabel stockQuantityText;
    JLabel buyingDateText;
    JLabel commissionFeeText;
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
    buyingDateText = new JLabel("Selling Date: ");
    buyingDateText.setPreferredSize(new Dimension(100, 20));
    sellingDate = new JTextField(20);
    fourthPanel.add(buyingDateText);
    fourthPanel.add(sellingDate);

    JPanel fifthPanel = new JPanel();
    commissionFeeText = new JLabel("Commission Fee: ");
    commissionFeeText.setPreferredSize(new Dimension(100, 20));
    commissionFee = new JTextField(20);
    fifthPanel.add(commissionFeeText);
    fifthPanel.add(commissionFee);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(350, 20));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    sellStock = new JButton("Sell Stock");
    home = new JButton("Home");
    sellStock.setActionCommand("sellStockButton");
    home.setActionCommand("sellHomeButton");
    buttonPanel.add(sellStock);
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
    sellStock.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + stockTicker.getText() + ":" + stockQuantity.getText()
        + ":" + sellingDate.getText() + ":" + commissionFee.getText();
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
    sellingDate.setText("");
    commissionFee.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
