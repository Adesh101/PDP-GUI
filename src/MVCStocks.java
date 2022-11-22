import controller.IController;
import controller.GUIController;
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
import view.FunctionalView.MainView;
import view.FunctionalView.MainViewFunction;
import view.IView;
import view.JFrameView;


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
    IOperation operation = new Operation(inflexiblePortfolio, flexiblePortfolio, stocks, lineChart);
    //IView view = new View(new InputStreamReader(System.in), System.out);
    mainViewFunction = new MainView("Hello World");

    IController controller = new GUIController(operation, mainViewFunction);
    controller.operate(operation);
  }
}
