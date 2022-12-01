package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import model.operation.IOperation;
import view.functionalview.MainView;
import view.textfieldview.AddStockDCAFixed;
import view.textfieldview.AddStockDCARecurringFinite;
import view.textfieldview.AddStockDCARecurringInfinite;
import view.textfieldview.BuyStock;
import view.textfieldview.CreateFlexiblePortfolio;
import view.functionalview.MainViewFunction;
import view.textfieldview.DollarCostAveragingView;
import view.textfieldview.ExistingPortfolioFixedDCA;
import view.textfieldview.InvestmentStrategy;
import view.textfieldview.NewPortfolioWithFiniteRangeDCA;
import view.textfieldview.NewPortfolioWithoutEndDateDCA;
import view.textfieldview.PortfolioPerformance;
import view.textfieldview.PortfolioValueByDate;
import view.textfieldview.QueryCostBasis;
import view.textfieldview.ReadPortfolio;
import view.textfieldview.SavePortfolio;
import view.textfieldview.SellStock;
import view.textfieldview.ShowPortfolioPerformance;
import view.textfieldview.TextField;

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
  private TextField addStockRecurringFinite;
  private TextField addStockRecurringInfinite;
  private TextField addStockDCARecurringInfinite;
  private TextField nextStock;
  private TextField implementStrategyDCAFixed;
  private TextField newPortfolioWithFiniteRangeDCA;
  private TextField newPortfolioWithoutEndDateDCA;
  private TextField existingPortfolioDCAMainWindow;
  private TextField selectStocks;
  private String str;
  private String portfolioName;
  private Double amount;
  private int intervalInDays;
  private String date;
  private String startDate;
  private String endDate;
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
        createView.clearInput();
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
        buyStock.clearInput();
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
        sellStock.clearInput();
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
          savePortfolio.clearInput();
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
        readPortfolio.clearInput();
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
        graph = new PortfolioPerformance("Portfolio Graph", map, operation.getLineChartScale());
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
      existingPortfolioStrategy = new ExistingPortfolioFixedDCA("Existing Portfolio "
          + "Fixed Amount");
      existingPortfolioStrategy.addActionListener(this);
      ((JFrame) this.dollarCostAveraging).dispose();
    });

    operationMap.put("selectStocksExisting", () -> {
      if (existingPortfolioStrategy.getInput().length() == 2) {
        existingPortfolioStrategy.setHintMess("Enter valid details.");
        return;
      }

      String[] details = existingPortfolioStrategy.getInput().split(":");
      this.portfolioName = details[0];
      this.date = details[2];
      this.amount = (double) 0;

      if (portfolioName.length() == 0 || !operation.checkWhetherFlexible(portfolioName)) {
        existingPortfolioStrategy.setHintMess("Enter valid portfolio name");
        return;
      }

      try {
        this.amount = Double.parseDouble(details[1]);
        if (amount <= 0.0) {
          existingPortfolioStrategy.setHintMess("Amount should be greater than Zero");
          return;
        }
      } catch (Exception e) {
        existingPortfolioStrategy.setHintMess("Amount should be numeric value");
      }

      try {
        operation.checkValidDate(this.date);
      } catch (Exception ex) {
        existingPortfolioStrategy.setHintMess("Enter valid date");
        return;
      }

      selectStocks = new AddStockDCAFixed("Add Stock");
      selectStocks.addActionListener(this);
      ((JFrame) this.existingPortfolioStrategy).dispose();
    });

    operationMap.put("selectStocks", () -> {
      double totalProportion = 0;
      if (selectStocks.getInput().length() == 2) {
        selectStocks.setHintMess("Enter valid portfolio details.");
        return;
      }

      String[] details = selectStocks.getInput().split(":");

      if (!operation.isTickerValid(details[0]) || details[0].length() == 0) {
        selectStocks.setHintMess("Enter valid ticker");
        return;
      }

      tickerNames.add(details[0]);
      double proportion = 0.0;
      double comFee = 0.0;

      try {
        proportion = Double.parseDouble(details[1]);
        if (proportion == 0 || proportion < 0) {
          selectStocks.setHintMess("Weightage cannot be empty or negative");
          return;
        }
        this.proportions.add(details[1]);
        totalProportion += proportion;
        if (totalProportion > 100) {
          selectStocks.setHintMess("Proportion cannot exceed 100.");
          return;
        }
      } catch (Exception e) {
        selectStocks.setHintMess("Proportion should be numeric value");
      }

      try {
        comFee = Double.parseDouble(details[2]);
        if (comFee == 0 || comFee < 0) {
          selectStocks.setHintMess("Commission fee cannot be empty or negative");
          return;
        }
        fee.add(details[2]);
      } catch (Exception e) {
        selectStocks.setHintMess("Commission fee should be a numeric value");
      }

      selectStocks.setHintMess("Stock added to strategy.");
      selectStocks.clearInput();
    });

    operationMap.put("implementStrategyDCAFixed", () -> {
      if (tickerNames.size() == 0) {
        selectStocks.setHintMess("Add stocks to the strategy first.");
        return;
      }
      try {
        operation.implementFixedDCAExistingPortfolio(this.portfolioName, this.amount, this.date,
            this.tickerNames, this.proportions, this.fee);
        selectStocks.setHintMess("Strategy applied successfully");
        selectStocks.clearInput();
      } catch (IllegalArgumentException e) {
        selectStocks.setHintMess(e.getMessage());
      }
    });

    operationMap.put("implementStrategyDCARecurringFinite", () -> {
      if (this.tickerNames.size() == 0) {
        addStockRecurringFinite.setHintMess("Add stocks to strategy first.");
        return;
      }
      try {
        operation.implementRecurringDCANewPortfolioFinite(this.portfolioName, this.tickerNames,
            this.amount,
            this.proportions, this.startDate, this.endDate, this.intervalInDays, this.fee);
        addStockRecurringFinite.setHintMess("Strategy applied successfully");
        addStockRecurringFinite.clearInput();
      } catch (IllegalArgumentException e) {
        addStockRecurringFinite.setHintMess(e.getMessage());
      }
    });

    operationMap.put("implementStrategyDCARecurringInfinite", () -> {
      if (this.tickerNames.size() == 0) {
        addStockDCARecurringInfinite.setHintMess("Add stocks to strategy first.");
        return;
      }
      try {
        operation.implementRecurringDCANewPortfolioInfinite(this.portfolioName, this.tickerNames,
            this.amount,
            this.proportions, this.startDate, this.intervalInDays, this.fee);
        addStockDCARecurringInfinite.setHintMess("Strategy applied successfully");
        addStockDCARecurringInfinite.clearInput();
      } catch (IllegalArgumentException e) {
        addStockDCARecurringInfinite.setHintMess(e.getMessage());
      }
    });

    operationMap.put("newPortfolioWithFiniteRangeDCA", () -> {
      newPortfolioWithFiniteRange = new NewPortfolioWithFiniteRangeDCA(
          "New Portfolio with End Date");
      newPortfolioWithFiniteRange.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });

    operationMap.put("selectStocksFiniteRecurring", () -> {
      if (newPortfolioWithFiniteRange.getInput().length() == 4) {
        newPortfolioWithFiniteRange.setHintMess("Enter valid details.");
        return;
      }

      String[] stockDetails = newPortfolioWithFiniteRange.getInput().split(":");
      this.portfolioName = stockDetails[0];
      String amount = stockDetails[1];
      String startDate = stockDetails[2];
      String endDate = stockDetails[3];
      String intervalInDays = stockDetails[4];

      try {
        this.amount = Double.parseDouble(amount);
        if (this.amount <= 0) {
          newPortfolioWithFiniteRange.setHintMess("Amount should be positive.");
          return;
        }
      } catch (Exception ex) {
        newPortfolioWithFiniteRange.setHintMess("Enter valid amount.");
        return;
      }

      try {
        operation.checkValidDate(startDate);
        operation.checkValidDate(endDate);
        this.startDate = startDate;
        this.endDate = endDate;
      } catch (Exception ex) {
        newPortfolioWithFiniteRange.setHintMess("Enter valid date.");
        return;
      }

      try {
        this.intervalInDays = Integer.parseInt(intervalInDays);
      } catch (Exception ex) {
        newPortfolioWithFiniteRange.setHintMess("Interval in days should be positive number only.");
        return;
      }

      addStockRecurringFinite = new AddStockDCARecurringFinite("Add Stock");
      addStockRecurringFinite.addActionListener(this);
      ((JFrame) this.newPortfolioWithFiniteRange).dispose();
    });

    operationMap.put("newPortfolioWithoutEndDateDCA", () -> {
      newPortfolioWithoutEndDate = new NewPortfolioWithoutEndDateDCA(
          "New Portfolio without End Date");
      newPortfolioWithoutEndDate.addActionListener(this);
      ((JFrame) this.dollarCostAveraging).dispose();
    });

    operationMap.put("selectStocksFinite", () -> {
      double totalProportion = 0;
      if (addStockRecurringFinite.getInput().length() == 2) {
        addStockRecurringFinite.setHintMess("Enter valid portfolio details.");
        return;
      }

      String[] details = addStockRecurringFinite.getInput().split(":");

      if (!operation.isTickerValid(details[0]) || details[0].length() == 0) {
        addStockRecurringFinite.setHintMess("Enter valid ticker");
        return;
      }

      this.tickerNames.add(details[0]);
      double proportion = 0.0;
      double comFee = 0.0;

      try {
        proportion = Double.parseDouble(details[1]);
        if (proportion == 0 || proportion < 0) {
          addStockRecurringFinite.setHintMess("Weightage cannot be empty or negative");
          return;
        }
        totalProportion += proportion;
        if (totalProportion > 100) {
          addStockRecurringFinite.setHintMess("Proportion cannot exceed 100.");
          return;
        }
        this.proportions.add(details[1]);
      } catch (Exception e) {
        addStockRecurringFinite.setHintMess("Proportion should be numeric value");
      }

      try {
        comFee = Double.parseDouble(details[2]);
        if (comFee == 0 || comFee < 0) {
          addStockRecurringFinite.setHintMess("Commission fee cannot be empty or negative");
          return;
        }
        this.fee.add(details[2]);
      } catch (Exception e) {
        addStockRecurringFinite.setHintMess("Commission fee should be a numeric value");
      }

      addStockRecurringFinite.setHintMess("Stock added to strategy.");
      addStockRecurringFinite.clearInput();
    });

    operationMap.put("selectStocksInfinite", () -> {
      double totalProportion = 0;
      if (addStockDCARecurringInfinite.getInput().length() == 2) {
        addStockDCARecurringInfinite.setHintMess("Enter valid portfolio details.");
        return;
      }

      String[] details = addStockDCARecurringInfinite.getInput().split(":");

      if (!operation.isTickerValid(details[0]) || details[0].length() == 0) {
        addStockDCARecurringInfinite.setHintMess("Enter valid ticker");
        return;
      }

      this.tickerNames.add(details[0]);
      double proportion = 0.0;
      double comFee = 0.0;

      try {
        proportion = Double.parseDouble(details[1]);
        if (proportion == 0 || proportion < 0) {
          addStockDCARecurringInfinite.setHintMess("Weightage cannot be empty or negative");
          return;
        }
        totalProportion += proportion;
        if (totalProportion > 100) {
          addStockDCARecurringInfinite.setHintMess("Proportion cannot exceed 100.");
          return;
        }
        this.proportions.add(details[1]);
      } catch (Exception e) {
        addStockDCARecurringInfinite.setHintMess("Proportion should be numeric value");
      }

      try {
        comFee = Double.parseDouble(details[2]);
        if (comFee == 0 || comFee < 0) {
          addStockDCARecurringInfinite.setHintMess("Commission fee cannot be empty or negative");
          return;
        }
        this.fee.add(details[2]);
      } catch (Exception e) {
        addStockDCARecurringInfinite.setHintMess("Commission fee should be a numeric value");
      }

      addStockDCARecurringInfinite.setHintMess("Stock added to strategy.");
      addStockDCARecurringInfinite.clearInput();
    });

    operationMap.put("selectStocksInfiniteRecurring", () -> {
      if (newPortfolioWithoutEndDate.getInput().length() == 4) {
        newPortfolioWithoutEndDate.setHintMess("Enter valid details.");
        return;
      }

      String[] stockDetails = newPortfolioWithoutEndDate.getInput().split(":");
      this.portfolioName = stockDetails[0];

      try {
        this.amount = Double.parseDouble(stockDetails[1]);
        if (this.amount <= 0) {
          newPortfolioWithoutEndDate.setHintMess("Amount should be positive.");
          return;
        }
      } catch (Exception ex) {
        newPortfolioWithoutEndDate.setHintMess("Enter valid amount.");
        return;
      }

      try {
        operation.checkValidDate(stockDetails[2]);
        this.startDate = stockDetails[2];
      } catch (Exception ex) {
        newPortfolioWithoutEndDate.setHintMess("Enter valid date.");
        return;
      }

      try {
        this.intervalInDays = Integer.parseInt(stockDetails[3]);
      } catch (Exception ex) {
        newPortfolioWithoutEndDate.setHintMess("Interval in days should be positive number only.");
        return;
      }

      addStockDCARecurringInfinite = new AddStockDCARecurringInfinite("Add Stock");
      addStockDCARecurringInfinite.addActionListener(this);
      ((JFrame) this.newPortfolioWithoutEndDate).dispose();
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
      ((JFrame) this.newPortfolioWithoutEndDate).dispose();
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
