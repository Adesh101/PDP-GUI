package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import model.operation.IOperation;
import view.FunctionalView.MainView;
import view.TextFieldView.AddStockDCAFixed;
import view.TextFieldView.BuyStock;
import view.TextFieldView.CreateFlexiblePortfolio;
import view.FunctionalView.MainViewFunction;
import view.TextFieldView.DollarCostAveragingView;
import view.TextFieldView.ExistingPortfolioFixedDCA;
import view.TextFieldView.InvestmentStrategy;
import view.TextFieldView.LineChartEx;
import view.TextFieldView.NewPortfolioWithFiniteRangeDCA;
import view.TextFieldView.NewPortfolioWithoutEndDateDCA;
import view.TextFieldView.PortfolioValueByDate;
import view.TextFieldView.QueryCostBasis;
import view.TextFieldView.ReadPortfolio;
import view.TextFieldView.SavePortfolio;
import view.TextFieldView.SellStock;
import view.TextFieldView.ShowPortfolioPerformance;
import view.TextFieldView.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

/**
 * A public class which controls the GUI flow.
 */
public class GUIController implements IController, ActionListener {

  private IOperation operation;
  private MainViewFunction mainView;
  private TextField createView;
  private TextField buyStock;
  private TextField sellStock;
  private TextField savePortfolio;
  private TextField queryCostBasis;
  private TextField portfolioValueByDate;
  private TextField readPortfolio;
  private TextField showPortfolioPerformance;
  private TextField graph;
  private TextField investmentStrategy;
  private TextField dollarCostAveraging;
  private TextField existingPortfolioStrategy;
  private TextField newPortfolioWithFiniteRange;
  private TextField newPortfolioWithoutEndDate;
  private TextField nextStock;
  private TextField implementStrategyDCAFixed;
  private TextField newPortfolioWithFiniteRangeDCA;
  private TextField newPortfolioWithoutEndDateDCA;
  private TextField existingPortfolioDCAMainWindow;
  private TextField selectStocks;
  private String str;
  private String portfolioName;
  private Double amount;
  private String date;
  private List<String> tickerNames = new ArrayList<>();
  private List<String> proportions = new ArrayList<>();
  private List<String> fee = new ArrayList<>();
  private Map<String, Runnable> operationMap;

  /**
   * Constructor for the main controller.
   *
   * @param: operation
   * @param: view
   * @throws: IllegalArgumentException
   */
  public GUIController(IOperation operation, MainViewFunction view) {
    this.operation = operation;
    this.mainView = view;
    mainView.addActionListener(this);
    str = "";
  }

  private Map<String, Runnable> initializeMap() {
    operationMap = new HashMap<>();

    operationMap.put("createFlexiblePortfolio", () -> {
      createView = new CreateFlexiblePortfolio("Create Portfolio");
      createView.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("createPortfolioButton", () -> {
      if (createView.getInput().length() == 1) {
        createView.setHintMess("Enter valid portfolio details.");
        return;
      }
      String[] portfolioDetails = createView.getInput().split(":");
      String portfolioName = portfolioDetails[0];
      String portfolioDate = portfolioDetails[1];
      if (portfolioName.length() == 0) {
        createView.setHintMess("Portfolio name cannot be empty.");
        return;
      }
      try {
        operation.checkValidDate(portfolioDate);
        operation.createFlexiblePortfolio(portfolioName, portfolioDate);
        createView.setHintMess("Portfolio " + portfolioName + " on " + portfolioDate + " created.");
        createView.clearField();
      } catch (IllegalArgumentException e) {
        createView.setHintMess(e.getMessage());
      }
    });

    operationMap.put("buyStock", () -> {
      buyStock = new BuyStock("Buy Stock");
      buyStock.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("addStock", () -> {
      try {
        String input = buyStock.getInput();
        stockHelper(input);
        String[] addStockDetails = input.split(":");
        String portfolioName = addStockDetails[0];
        String ticker = addStockDetails[1];
        int quantity = Integer.parseInt(addStockDetails[2]);
        String date = addStockDetails[3];
        int commissionFee = Integer.parseInt(addStockDetails[4]);
        double price = Double.parseDouble(operation.callStockAPI(ticker, date)[3]);
        operation.addStockToFlexiblePortfolio(portfolioName, ticker, quantity, price, date,
            commissionFee);
        buyStock.setHintMess(quantity + " units of " + " stock " + ticker + " added to portfolio "
            + portfolioName + " on " + date);
        buyStock.clearField();
      } catch (IllegalArgumentException e) {
        buyStock.setHintMess(e.getMessage());
      }
    });

    operationMap.put("sellStock", () -> {
      sellStock = new SellStock("Sell Stock");
      sellStock.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("sellStockButton", () -> {
      try {
        String input = sellStock.getInput();
        stockHelper(input);
        String[] sellStockDetails = input.split(":");
        String portfolioName = sellStockDetails[0];
        String ticker = sellStockDetails[1];
        int quantity = Integer.parseInt(sellStockDetails[2]);
        String sellDate = sellStockDetails[3];
        int commissionFee = Integer.parseInt(sellStockDetails[4]);
        operation.checkValidDate(sellDate);
        double price = Double.parseDouble(operation.callStockAPI(ticker, sellDate)[3]);
        operation.sellStock(portfolioName, ticker, quantity, price, sellDate, commissionFee);
        sellStock.setHintMess(quantity + " units of " + " stock " + ticker + " added to portfolio "
            + portfolioName + " on " + sellDate);
        sellStock.clearField();
      } catch (IllegalArgumentException e) {
        sellStock.setHintMess(e.getMessage());
      }
    });

    operationMap.put("savePortfolio", () -> {
      savePortfolio = new SavePortfolio("Save Portfolio");
      savePortfolio.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("savePortfolioButton", () -> {
      String portfolioName = savePortfolio.getInput();
      if (portfolioName.length() == 1) {
        savePortfolio.setHintMess("Portfolio name cannot be empty.");
        return;
      }

      try {
        if (operation.checkWhetherFlexible(portfolioName)) {
          operation.writeToCSV(portfolioName);
          savePortfolio.setHintMess("Portfolio " + portfolioName + " saved successfully.");
          savePortfolio.clearField();
        } else {
          savePortfolio.setHintMess("Enter valid portfolio name.");
        }
      } catch (IllegalArgumentException e) {
        savePortfolio.setHintMess(e.getMessage());
      }
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("readPortfolio", () -> {
      readPortfolio = new ReadPortfolio("Read Portfolio");
      readPortfolio.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("readPortfolioButton", () -> {
      String portfolioName = readPortfolio.getInput();
      if (portfolioName.length() == 1) {
        readPortfolio.setHintMess("Portfolio name cannot be empty.");
        return;
      }

      try {
        operation.readFromFile(portfolioName);
        readPortfolio.setHintMess("Portfolio " + portfolioName + " read successfully.");
        readPortfolio.clearField();
      } catch (IllegalArgumentException e) {
        readPortfolio.setHintMess(e.getMessage());
      }

      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("showPortfolioPerformance", () -> {
      showPortfolioPerformance = new ShowPortfolioPerformance("Portfolio Performance");
      showPortfolioPerformance.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("showPortfolioPerformanceButton", () -> {
      String[] performanceDetails = showPortfolioPerformance.getInput().split(":");
      if (performanceDetails.length == 2) {
        showPortfolioPerformance.setHintMess("Enter valid portfolio details.");
        return;
      }
      String portfolioName = performanceDetails[0];
      String startDate = performanceDetails[1];
      String endDate = performanceDetails[2];
      if (portfolioName.length() == 0 || !operation.checkWhetherFlexible(portfolioName)) {
        showPortfolioPerformance.setHintMess("Enter valid portfolio name");
        return;
      }
      try {
        operation.checkValidDate(startDate);
        operation.checkValidDate(endDate);
        TreeMap<String, Integer> map = operation.getGraph(portfolioName, startDate, endDate);
        graph = new LineChartEx(map, operation.getLineChartScale());
        graph.addActionListener(this);
      } catch (Exception ex) {
        readPortfolio.setHintMess(ex.getMessage());
      }
    });

    operationMap.put("queryCostBasis", () -> {
      queryCostBasis = new QueryCostBasis("Cost Basis");
      queryCostBasis.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("costBasisButton", () -> {
      String[] costBasis = queryCostBasis.getInput().split(":");
      if (costBasis.length == 1) {
        queryCostBasis.setHintMess("Enter valid details.");
        return;
      }
      String portfolioName = costBasis[0];
      String costBasisDate = costBasis[1];

      if (portfolioName.length() == 0 || !operation.checkWhetherFlexible(portfolioName)) {
        queryCostBasis.setHintMess("Enter valid portfolio name");
        return;
      }
      try {
        operation.checkValidDate(costBasisDate);
        double costBasisValue = operation.costBasisByDate(portfolioName, costBasisDate);
        queryCostBasis.setHintMess("Cost Basis on " + costBasisDate + " is: "
            + costBasisValue);
      } catch (IllegalArgumentException ex) {
        queryCostBasis.setHintMess(ex.getMessage());
      }
    });

    operationMap.put("portfolioValueByDate", () -> {
      portfolioValueByDate = new PortfolioValueByDate("Portfolio Value");
      portfolioValueByDate.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("portfolioValueByDateButton", () -> {
      String[] portfolioValue = portfolioValueByDate.getInput().split(":");
      if (portfolioValue.length == 1) {
        portfolioValueByDate.setHintMess("Enter valid details.");
        return;
      }
      String portfolioName = portfolioValue[0];
      String portfolioDate = portfolioValue[1];

      if (portfolioName.length() == 0 || !operation.checkWhetherFlexible(portfolioName)) {
        portfolioValueByDate.setHintMess("Enter valid portfolio name");
        return;
      }
      try {
        operation.checkValidDate(portfolioDate);
        double finalValue = operation.getPortfolioByDate(portfolioName, portfolioDate);
        portfolioValueByDate.setHintMess("Value of " + portfolioName + " on "
            + portfolioDate + " is : " + finalValue);
      } catch (IllegalArgumentException ex) {
        portfolioValueByDate.setHintMess(ex.getMessage());
      }
    });

    operationMap.put("investmentStrategy", () -> {
      investmentStrategy = new InvestmentStrategy("Investment Strategy");
      investmentStrategy.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("dollarCostAveraging", () -> {
      dollarCostAveraging = new DollarCostAveragingView("Dollar Cost Averaging");
      dollarCostAveraging.addActionListener(this);
      ((JFrame) this.investmentStrategy).dispose();
    });

    operationMap.put("existingPortfolioDCA", () -> {
      existingPortfolioStrategy = new ExistingPortfolioFixedDCA("Existing Portfolio Fixed Amount");
      existingPortfolioStrategy.addActionListener(this);
      ((JFrame) this.dollarCostAveraging).dispose();
    });

    operationMap.put("selectStocksExisting", () -> {
      if (existingPortfolioStrategy.getInput().length() == 3) {
        existingPortfolioStrategy.setHintMess("Enter valid details.");
        return;
      }

      String[] details = existingPortfolioStrategy.getInput().split(":");
      String portfolioName = details[0];
      this.date = details[2];
      this.amount = (double) 0;

      if (portfolioName.length() == 0 || !operation.checkWhetherFlexible(portfolioName)) {
        existingPortfolioStrategy.setHintMess("Enter valid portfolio name");
        return;
      }

      try {
        this.amount = Double.parseDouble(details[1]);
      } catch (Exception e) {
        existingPortfolioStrategy.setHintMess("Amount should be numeric value");
      }

      if (amount <= 0.0) {
        existingPortfolioStrategy.setHintMess("Amount should be greater than Zero");
        return;
      } else if (date.length() == 0) {
        existingPortfolioStrategy.setHintMess("Date cannot be empty.");
        return;
      }

      try {
        operation.checkValidDate(this.date);
      } catch (Exception ex) {
        existingPortfolioStrategy.setHintMess("Enter valid date.");
        return;
      }

      selectStocks = new AddStockDCAFixed("Add Stock");
      selectStocks.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("nextStock", () -> {
      if (selectStocks.getInput().length() != 3) {
        selectStocks.setHintMess("Enter valid portfolio details.");
        return;
      }
      String[] details = selectStocks.getInput().split(":");
      tickerNames.add(details[0]); // validate
      double proportion = 0.0;
      double comFee = 0.0;
      try {
        proportion = Double.parseDouble(details[1]);
        proportions.add(details[1]);
      } catch (Exception e) {
        selectStocks.setHintMess("Proportion should be numeric value");
      }
      try {
        comFee = Double.parseDouble(details[2]);
        fee.add(details[2]);
      } catch (Exception e) {
        selectStocks.setHintMess("Commission fee should be a numeric value");
      }

      if (details[0].length() == 0) {
        selectStocks.setHintMess("Ticker cannot be empty");
      } else if (comFee == 0 || comFee < 0) {
        selectStocks.setHintMess("Commission fee cannot be empty or negative");
      } else if (proportion == 0 || proportion < 0) {
        selectStocks.setHintMess("Weightage cannot be empty or negative");
      }
      implementStrategyDCAFixed = new AddStockDCAFixed("Add Stock");
      implementStrategyDCAFixed.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("implementStrategyDCAFixed", () -> {
      try {
        operation.implementFixedDCAExistingPortfolio(portfolioName, this.amount, this.date, tickerNames,
            proportions, fee);
        selectStocks.setHintMess("Strategy applied successfully");
        selectStocks.clearField();
      } catch (IllegalArgumentException e) {
        selectStocks.setHintMess(e.getMessage());
      }
    });

    operationMap.put("newPortfolioWithFiniteRangeDCA", () -> {
      newPortfolioWithFiniteRange = new NewPortfolioWithFiniteRangeDCA(
          "New Portfolio with End Date");
      newPortfolioWithFiniteRange.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("selectStocksFiniteRecurring", () -> {
      if (newPortfolioWithFiniteRange.getInput().length() == 5) {
        newPortfolioWithFiniteRange.setHintMess("Enter valid details.");
      }

      String[] stockDetails = newPortfolioWithFiniteRange.getInput().split(":");
      String portfolioName = stockDetails[0];
      String amount = stockDetails[1];
      double amountValue = 0;
      String startDate = stockDetails[2];
      String endDate = stockDetails[3];
      String intervalInDays = stockDetails[4];
      int intervalInDaysValue = 0;

      try {
        amountValue = Double.parseDouble(amount);
      } catch (Exception ex) {
        newPortfolioWithFiniteRange.setHintMess("Enter valid amount.");
      }

      try {
        operation.checkValidDate(startDate);
        operation.checkValidDate(endDate);
      } catch (Exception ex) {
        throw new IllegalArgumentException("Enter a valid date.");
      }

      try {
        intervalInDaysValue = Integer.parseInt(intervalInDays);
      } catch (Exception ex) {
        newPortfolioWithFiniteRange.setHintMess("Interval in days should be positive number only.");
      }

      try{
        operation.implementRecurringDCANewPortfolioFinite(portfolioName, tickerNames,
            amountValue, proportions, startDate, endDate, intervalInDaysValue, fee);
        newPortfolioWithFiniteRange.setHintMess("");
      } catch (Exception ex) {
        newPortfolioWithFiniteRange.setHintMess("Enter valid details");
      }
    });

    operationMap.put("newPortfolioWithoutEndDateDCA", () -> {
      newPortfolioWithoutEndDate = new NewPortfolioWithoutEndDateDCA(
          "New Portfolio without End Date");
      newPortfolioWithoutEndDate.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("createHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.createView).dispose();
    });

    operationMap.put("addHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.buyStock).dispose();
    });

    operationMap.put("sellHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.sellStock).dispose();
    });

    operationMap.put("saveHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.savePortfolio).dispose();
    });

    operationMap.put("readHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.readPortfolio).dispose();
    });

    operationMap.put("showHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.showPortfolioPerformance).dispose();
    });

    operationMap.put("graphHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.graph).dispose();
    });

    operationMap.put("strategyHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.investmentStrategy).dispose();
    });

    operationMap.put("dollarCostAveragingHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.dollarCostAveraging).dispose();
    });

    operationMap.put("existingPortfolioFixedDCAHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.investmentStrategy).dispose();
    });


    operationMap.put("newPortfolioWithFiniteRangeDCAHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.investmentStrategy).dispose();
    });


    operationMap.put("newPortfolioWithoutEndDateDCAHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.investmentStrategy).dispose();
    });

    operationMap.put("portfolioHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.portfolioValueByDate).dispose();
    });

    operationMap.put("costHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.queryCostBasis).dispose();
    });

    operationMap.put("AddStockDCAHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.selectStocks).dispose();
    });


    operationMap.put("dollarCostAveragingHome", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) this.selectStocks).dispose();
    });


    operationMap.put("quit", () -> {
      System.exit(0);
    });

    return operationMap;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.str = e.getActionCommand();
    operationMap.get(str).run();
  }

  @Override
  public void operate(IOperation operation) {
    initializeMap();
  }

  private void stockHelper(String input) {
    if (input.length() == 4) {
      throw new IllegalArgumentException("Enter valid input.");
    }
    String[] stockDetails = input.split(":");
    int quantityValue = 0;
    int feeValue = 0;
    if (stockDetails[0].length() == 0 || !operation.checkWhetherFlexible(stockDetails[0])) {
      throw new IllegalArgumentException("Enter valid portfolio name");
    }
    if (stockDetails[1].length() == 0 || !operation.isTickerValid(stockDetails[1])) {
      throw new IllegalArgumentException("Enter valid ticker.");
    }
    try {
      quantityValue = Integer.parseInt(stockDetails[2]);
      if (quantityValue == 0 || quantityValue < 0) {
        throw new IllegalArgumentException();
      }
    } catch (Exception ex) {
      throw new IllegalArgumentException("Quantity should be a positive numeric value.");
    }
    try {
      feeValue = Integer.parseInt(stockDetails[4]);
      if (feeValue == 0 || feeValue < 0) {
        throw new IllegalArgumentException();
      }
    } catch (Exception ex) {
      throw new IllegalArgumentException("Commission fee should be a positive numeric value.");
    }
    try {
      operation.checkValidDate(stockDetails[3]);
    } catch (Exception ex) {
      throw new IllegalArgumentException("Enter a valid date.");
    }
  }
}
