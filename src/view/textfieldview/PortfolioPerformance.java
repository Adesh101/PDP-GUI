package view.textfieldview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * A public class for portfolio performance.
 */
public class PortfolioPerformance extends JFrame implements TextField {

  private static final long serialVersionUID = 1L;
  public JButton home;

  private DefaultCategoryDataset createDataset(TreeMap<String, Integer> map, int scale) {

    String series1 = String.valueOf(scale);

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    int temp = 0;
    int i = scale / map.size();
    for (String date : map.keySet()) {
      dataset.addValue(map.get(date) * scale, series1, date);
      temp += i;
    }
    return dataset;
  }

  /**
   * A public constructor for PortfolioPerformance.
   *
   * @param: caption
   */
  public PortfolioPerformance(String caption, TreeMap<String, Integer> map, int scale) {
    super(caption);
    DefaultCategoryDataset dataset = createDataset(map, scale);
    JFreeChart chart = ChartFactory.createLineChart("Portfolio Performance", "Date",
        "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
    chart.setBackgroundPaint(Color.white);
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);

    JPanel firstPanel = new JPanel();
    home = new JButton("Home");
    home.setActionCommand("graphHomeButton");
    firstPanel.add(home);

    this.setSize(new Dimension(500, 350));
    this.setTitle("Portfolio Graph");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    home.addActionListener(listener);
  }

  @Override
  public String getInput() {
    return null;
  }

  @Override
  public void setHintMess(String message) {
    System.out.println("This will print a message");
  }

  @Override
  public void clearInput() {
    System.out.println("This will print a message");
  }
}
