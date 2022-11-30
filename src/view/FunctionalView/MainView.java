package view.FunctionalView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame implements MainViewFunction {
  private JButton createFlexiblePortfolio;
  private JButton buyStock;
  private JButton sellStock;
  private JButton showPortfolioPerformance;
  private JButton savePortfolio;
  private JButton readPortfolio;
  private JButton investmentStrategy;
  private JButton queryCostBasis;
  private JButton portfolioValueByDate;
  private JButton quit;

  public MainView (String str) {
    super(str);
    this.setPreferredSize(new Dimension(700, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout(5, 5));

    JPanel panel = new JPanel();;
    panel.setLayout(new GridLayout(10, 1, 10, 0));

    createFlexiblePortfolio = new JButton("Create Flexible Portfolio");
    buyStock = new JButton("Buy Stock");
    sellStock = new JButton("Sell Stock");
    savePortfolio = new JButton("Save Portfolio");
    readPortfolio = new JButton("Read Portfolio from File");
    showPortfolioPerformance = new JButton("Show Portfolio Performance");
    investmentStrategy = new JButton("Invest using Strategy");
    queryCostBasis = new JButton("Query Cost Basis");
    portfolioValueByDate = new JButton("Portfolio Value by Date");
    quit = new JButton("Quit");

    createFlexiblePortfolio.setActionCommand("createFlexiblePortfolio");
    buyStock.setActionCommand("buyStock");
    sellStock.setActionCommand("sellStock");
    savePortfolio.setActionCommand("savePortfolio");
    readPortfolio.setActionCommand("readPortfolio");
    showPortfolioPerformance.setActionCommand("showPortfolioPerformance");
    investmentStrategy.setActionCommand("investmentStrategy");
    queryCostBasis.setActionCommand("queryCostBasis");
    portfolioValueByDate.setActionCommand("portfolioValueByDate");
    quit.setActionCommand("quit");

    panel.add(createFlexiblePortfolio);
    panel.add(buyStock);
    panel.add(sellStock);
    panel.add(savePortfolio);
    panel.add(readPortfolio);
    panel.add(showPortfolioPerformance);
    panel.add(queryCostBasis);
    panel.add(portfolioValueByDate);
    panel.add(investmentStrategy);
    panel.add(quit);

    this.getContentPane().add(panel);
    this.pack();
    setLocationRelativeTo(null);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    createFlexiblePortfolio.addActionListener(listener);
    buyStock.addActionListener(listener);
    sellStock.addActionListener(listener);
    showPortfolioPerformance.addActionListener(listener);
    savePortfolio.addActionListener(listener);
    readPortfolio.addActionListener(listener);
    investmentStrategy.addActionListener(listener);
    queryCostBasis.addActionListener(listener);
    portfolioValueByDate.addActionListener(listener);
    quit.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
