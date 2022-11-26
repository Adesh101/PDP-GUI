package view.TextFieldView;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class PortfolioPerformance extends JFrame implements TextField{

  private static final long serialVersionUID = 1L;

  private DefaultCategoryDataset createDataset() {

    String series1 = "Visitor";
    String series2 = "Unique Visitor";

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    dataset.addValue(200, series1, "2016-12-19");
    dataset.addValue(150, series1, "2016-12-20");
    dataset.addValue(100, series1, "2016-12-21");
    dataset.addValue(210, series1, "2016-12-22");
    dataset.addValue(240, series1, "2016-12-23");
    dataset.addValue(195, series1, "2016-12-24");
    dataset.addValue(245, series1, "2016-12-25");

    dataset.addValue(150, series2, "2016-12-19");
    dataset.addValue(130, series2, "2016-12-20");
    dataset.addValue(95, series2, "2016-12-21");
    dataset.addValue(195, series2, "2016-12-22");
    dataset.addValue(200, series2, "2016-12-23");
    dataset.addValue(180, series2, "2016-12-24");
    dataset.addValue(230, series2, "2016-12-25");

    return dataset;
  }


  public PortfolioPerformance(String caption) {
    super(caption);
    // Create dataset
    DefaultCategoryDataset dataset = createDataset();
    // Create chart
    JFreeChart chart = ChartFactory.createLineChart("Portfolio Performance", "Date",
        "Value", dataset, PlotOrientation.VERTICAL, true, true, false);

   // chart.setBackgroundPaint(Color.white);
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  @Override
  public void addActionListener(ActionListener listener) {

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
}
