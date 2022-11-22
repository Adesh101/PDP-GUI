package view.FunctionalView;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame implements MainViewFunction {
  private JButton createPortfolio;
  private JButton buyStock;
  private JButton sellStock;
  private JButton showPortfolioPerformance;
  private JButton savePortfolioToFile;
  private JButton readPortfolioFromFile;
  private JButton quit;

  public MainView (String str) {
    super(str);
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    GridLayout layout = new GridLayout(3, 2);
    JPanel panel;
    panel = new JPanel();
    panel.setLayout(layout);

    createPortfolio = new JButton("Create Portfolio");
    buyStock = new JButton("Buy Stock");
    sellStock = new JButton("Sell Stock");
    savePortfolioToFile = new JButton("Save Portfolio");
    readPortfolioFromFile = new JButton("Read Portfolio From File");
    showPortfolioPerformance = new JButton("Show Portfolio Performance");
    quit = new JButton("Quit");


    createPortfolio.setActionCommand("createPortfolio");
    buyStock.setActionCommand("buyStockChooseAWay");
    sellStock = new JButton("Sell Stock");
    savePortfolioToFile.setActionCommand("savePortfolioToFile");
    readPortfolioFromFile.setActionCommand("readPortfolioFromFile");
    showPortfolioPerformance = new JButton("Show Portfolio Performance");
    quit.setActionCommand("quit");

    panel.add(createPortfolio);
    panel.add(buyStock);
    panel.add(sellStock);
    panel.add(savePortfolioToFile);
    panel.add(readPortfolioFromFile);
    panel.add(showPortfolioPerformance);
    panel.add(quit);

    this.getContentPane().add(panel);
    this.setVisible(true);
    this.pack();
  }

  @Override
  public void addActionListener(ActionListener listener) {
    createPortfolio.addActionListener(listener);
    buyStock.addActionListener(listener);
    sellStock.addActionListener(listener);
    showPortfolioPerformance.addActionListener(listener);
    quit.addActionListener(listener);
    savePortfolioToFile.addActionListener(listener);
    readPortfolioFromFile.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
