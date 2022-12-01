import controller.Controller;
import controller.IController;
import controller.GUIController;
import java.io.InputStreamReader;
import java.util.Scanner;
import model.operation.IOperation;
import model.operation.Operation;
import model.plot.ILineChart;
import model.plot.LineChart;
import model.portfolio.FlexiblePortfolio;
import model.portfolio.IFlexiblePortfolio;
import model.portfolio.IInflexiblePortfolio;
import model.portfolio.InflexiblePortfolio;
import model.stocks.IStocks;
import model.stocks.Stocks;
import model.strategy.DollarCostAveraging;
import model.strategy.IDollarCostAveraging;
import view.TextUI;
import view.View;
import view.functionalview.MainView;
import view.functionalview.MainViewFunction;


/**
 * Class to run our main Stock software.
 */
public class MVCStocks {

  /**
   * main method to run the program.
   *
   * @param: args
   */
  public static void main(String[] args) {
    MainViewFunction mainViewFunction;
    UpdateStockData data = new UpdateStockData();
    data.getFiles();
    IStocks stocks = new Stocks();
    IInflexiblePortfolio inflexiblePortfolio = new InflexiblePortfolio();
    IFlexiblePortfolio flexiblePortfolio = new FlexiblePortfolio();
    ILineChart lineChart = new LineChart();
    IDollarCostAveraging dollarCostAveraging = new DollarCostAveraging(flexiblePortfolio);
    IOperation operation = new Operation(inflexiblePortfolio, flexiblePortfolio, stocks, lineChart);
    TextUI view;
    IController controller;
    while (true) {
      System.out.println("Choose a view type:\n");
      System.out.println("1 - Text Based UI");
      System.out.println("2 - Graphical User Interface");
      Scanner sc = new Scanner(System.in);
      String choice = sc.next();
      if (choice.equals("1")) {
        view = new View(new InputStreamReader(System.in), System.out);
        controller = new Controller(operation, view);
        controller.operate(operation);
        break;
      }
      else if (choice.equals("2")) {
        mainViewFunction = new MainView("Menu");
        controller = new GUIController(operation, mainViewFunction);
        controller.operate(operation);
        break;
      }
      else {
        System.out.println("Enter a valid choice.\n");
      }
    }
  }
}
