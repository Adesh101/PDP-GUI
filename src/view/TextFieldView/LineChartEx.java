package view.TextFieldView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

/**
 * A public class for LineChart.
 */
public class LineChartEx extends JFrame implements TextField {

  private JButton home;

  /**
   * A public constructor for LineChartEx.
   * @param map chart data
   * @param scale scale
   */
  public LineChartEx(TreeMap<String, Integer> map, int scale) {
    initUI(map, scale);
  }

  /**
   * A public method to initialise the UI.
   * @param map chart data
   * @param scale scale of the chart
   */
  public void initUI(TreeMap<String, Integer> map, int scale) {
    XYDataset dataset = createDataset(map, scale);
    JFreeChart chart = createChart(dataset, map);

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    chartPanel.setBackground(Color.white);
    add(chartPanel);

    JPanel zerothPanel = new JPanel();
    zerothPanel.add(chartPanel);

    JPanel firstPanel = new JPanel();
    home = new JButton("Home");
    home.setActionCommand("graphHomeButton");
    firstPanel.add(home);

    JPanel panelStructure = new JPanel(new GridLayout(1, 1));
    panelStructure.add(zerothPanel);

    this.add(panelStructure, BorderLayout.CENTER);
    this.add(firstPanel, BorderLayout.AFTER_LAST_LINE);

    this.pack();
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

  }

  @Override
  public void clearField() {

  }

  @Override
  public void resetFocus() {

  }

  public XYDataset createDataset(TreeMap<String, Integer> map, int scale) {
    var series = new XYSeries(scale);
    int temp = 0;
    int i = scale / map.size();
    for (String date : map.keySet()) {
      series.add(temp, map.get(date));
      temp += i;
    }
    var dataset = new XYSeriesCollection();
    dataset.addSeries(series);
    return dataset;
  }

  public JFreeChart createChart(XYDataset dataset, TreeMap<String, Integer> map) {
    JFreeChart chart = ChartFactory.createXYLineChart(
        "Portfolio Performance",
        "Date",
        "Value",
        dataset,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
    );

    XYPlot plot = chart.getXYPlot();

    String[] xAxis = new String[map.size()];

    Set<Entry<String, Integer>> entrySet = map.entrySet();
    Map.Entry<Integer, String>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);

    for (int i = 0; i < entryArray.length; i++) {
      xAxis[i] = String.valueOf(entryArray[i].getKey());
    }

    SymbolAxis rangeAxis = new SymbolAxis("Words", xAxis);

    rangeAxis.setTickUnit(new NumberTickUnit(1));
    rangeAxis.setRange(0, xAxis.length);
    plot.setRangeAxis(rangeAxis);

    var renderer = new XYLineAndShapeRenderer();
    renderer.setSeriesPaint(0, Color.RED);
    renderer.setSeriesStroke(0, new BasicStroke(2.0f));

    plot.setRenderer(renderer);
    plot.setBackgroundPaint(Color.white);

    plot.setRangeGridlinesVisible(true);
    plot.setRangeGridlinePaint(Color.BLACK);

    plot.setDomainGridlinesVisible(true);
    plot.setDomainGridlinePaint(Color.BLACK);

    chart.setTitle(new TextTitle("Portfolio Performance",
            new Font("Serif", java.awt.Font.BOLD, 18)
        )
    );
    return chart;
  }
}