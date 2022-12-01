package view.textfieldview;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A public class to create flexible portfolio.
 */
public class CreateFlexiblePortfolio extends JFrame implements TextField{
  private JTextField portfolioName;
  private JLabel displayText;
  private JLabel portfolioText;
  private JLabel portfolioDateText;
  private JTextField portfolioDate;
  private JButton createPortfolio;
  private JButton homeButton;

  /**
   * A public constructor for CreateFlexiblePortfolio.
   * @param caption string
   */
  public CreateFlexiblePortfolio(String caption) {
    super(caption);
    this.setPreferredSize(new Dimension(450, 300));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel firstPanel = new JPanel();
    portfolioText = new JLabel("Portfolio Name: ");
    portfolioName = new JTextField(30);
    firstPanel.add(portfolioText);
    firstPanel.add(portfolioName);

    JPanel secondPanel = new JPanel();
    portfolioDateText = new JLabel("Portfolio Date: ");
    portfolioDate = new JTextField(30);
    secondPanel.add(portfolioDateText);
    secondPanel.add(portfolioDate);

    JPanel thirdPanel = new JPanel();
    displayText = new JLabel("");
    thirdPanel.add(displayText);

    JPanel fourthPanel = new JPanel();
    createPortfolio = new JButton("Create Portfolio");
    homeButton = new JButton("Home");
    createPortfolio.setActionCommand("createPortfolioButton");
    homeButton.setActionCommand("createHomeButton");
    fourthPanel.add(createPortfolio);
    fourthPanel.add(homeButton);

    JPanel panelStructure = new JPanel(new GridLayout(2, 1));
    panelStructure.add(firstPanel);
    panelStructure.add(secondPanel);

    this.add(panelStructure, BorderLayout.PAGE_START);
    this.add(thirdPanel, BorderLayout.CENTER);
    this.add(fourthPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    createPortfolio.addActionListener(listener);
    homeButton.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + portfolioDate.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearField() {
    portfolioName.setText("");
    portfolioDate.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
