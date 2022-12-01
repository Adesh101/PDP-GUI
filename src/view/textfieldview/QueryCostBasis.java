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
 * A public class for querying cost basis.
 */
public class QueryCostBasis extends JFrame implements TextField {


  private JLabel displayText;

  private JTextField portfolioName;
  private JTextField costBasisDate;

  private JButton costBasis;
  private JButton home;

  /**
   * A public constructor for QueryCostBasis.
   *
   * @param caption string
   */
  public QueryCostBasis(String caption) {
    super(caption);
    JLabel portfolioNameText;
    JLabel costBasisDateText;
    this.setPreferredSize(new Dimension(500, 350));

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel firstPanel = new JPanel();
    portfolioNameText = new JLabel("Portfolio Name: ");
    portfolioNameText.setPreferredSize(new Dimension(100, 20));
    portfolioName = new JTextField(20);
    firstPanel.add(portfolioNameText);
    firstPanel.add(portfolioName);

    JPanel secondPanel = new JPanel();
    costBasisDateText = new JLabel("Date: ");
    costBasisDateText.setPreferredSize(new Dimension(100, 20));
    costBasisDate = new JTextField(20);
    secondPanel.add(costBasisDateText);
    secondPanel.add(costBasisDate);

    JPanel displayPanel = new JPanel();
    displayText = new JLabel("");
    displayText.setPreferredSize(new Dimension(400, 20));
    displayPanel.add(displayText);

    JPanel buttonPanel = new JPanel();
    costBasis = new JButton("Cost Stock");
    home = new JButton("Home");
    costBasis.setActionCommand("costBasisButton");
    home.setActionCommand("costHomeButton");
    buttonPanel.add(costBasis);
    buttonPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(6, 1));
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
    costBasis.addActionListener(listener);
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return portfolioName.getText() + ":" + costBasisDate.getText();
  }

  @Override
  public void setHintMess(String message) {
    displayText.setText(message);
  }

  @Override
  public void clearInput() {
    portfolioName.setText("");
    costBasisDate.setText("");
  }
}
