package controller;

import controller.actions.IActions;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.operation.IOperation;
import view.FunctionalView.MainView;
import view.TextFieldView.BuyStock;
import view.TextFieldView.CreateFlexiblePortfolio;
import view.FunctionalView.MainViewFunction;
import view.IView;
import view.TextFieldView.ReadPortfolio;
import view.TextFieldView.SavePortfolio;
import view.TextFieldView.SellStock;
import view.TextFieldView.TextField;
import view.TextUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class GUIController implements IController, ActionListener{
  private IOperation operation;
  private MainViewFunction mainView;
  private TextField createView;
  private TextField buyStock;
  private TextField sellStock;
  private TextField savePortfolio;
  private TextField readPortfolio;
  private IView view;
  private IActions action;
  private String str;
  private Map<String, Runnable> actionMap;

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

  public void setView(TextUI v) {
    view = v;
    //provide view with all the callbacks
    view.addFeatures(this);
  }

  private Map<String, Runnable> initializeMap() {
    Map<String, Runnable> actionMap = new HashMap<>();
    actionMap.put("createFlexiblePortfolio", () -> {
      createView = new CreateFlexiblePortfolio("Create Portfolio");
      createView.addActionListener(this);
      ((JFrame) createView).setLocation(((JFrame) this.mainView).getLocation());
      ((JFrame) this.mainView).dispose();
    });

    actionMap.put("createPortfolioButton", () -> {
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

      String dateFormat = "yyyy-MM-dd";
      if (portfolioDate.length() != 10) {
          createView.setHintMess("Enter a valid date.");
        }
      try {
        DateFormat df = new SimpleDateFormat(dateFormat);
        df.setLenient(false);
        df.parse(portfolioDate);
      } catch (ParseException e) {
        createView.setHintMess("Enter a valid date.");
        return;
      }

      try {
        operation.createFlexiblePortfolio(portfolioName, portfolioDate);
        createView.setHintMess("Portfolio " + portfolioName + " on " + portfolioDate + " created.");
        createView.clearField();
      }
      catch (IllegalArgumentException e) {
        createView.setHintMess(e.getMessage());
      }
    });

    actionMap.put("buyStock", () -> {
      buyStock = new BuyStock("Buy Stock");
      buyStock.addActionListener(this);
      ((JFrame) buyStock).setLocation(((JFrame) this.mainView).getLocation());
      ((JFrame) this.mainView).dispose();
    });

    actionMap.put("addStock", () -> {
      if (buyStock.getInput().length() == 4) {
        buyStock.setHintMess("Enter valid portfolio details.");
        return;
      }

      String[] addStockDetails = buyStock.getInput().split(":");
      String portfolioName = addStockDetails[0];
      String ticker = addStockDetails[1];
      int quantity = 0;
      int commissionFee = 0;

      try{
         quantity = Integer.parseInt(addStockDetails[2]);
      } catch (Exception ex) {
        buyStock.setHintMess("Quantity should be numeric value.");
      }

      try{
        commissionFee = Integer.parseInt(addStockDetails[4]);
      } catch (Exception ex) {
        buyStock.setHintMess("Commission fee should be numeric value.");
      }

      String buyDate = addStockDetails[3];

      //create a function to check if any of the above quantities are empty

      if (ticker.length() == 0) {
        buyStock.setHintMess("Ticker cannot be empty.");
        return;
      } else if (quantity == 0 || quantity < 0) {
        buyStock.setHintMess("Quantity cannot be empty or negative.");
        return;
      } else if (buyDate.length() == 0) {
        buyStock.setHintMess("Buying date cannot be empty.");
        return;
      } else if (commissionFee == 0 || commissionFee < 0) {
        buyStock.setHintMess("Commission fee cannot be empty or negative");
        return;
      }

      String dateFormat = "yyyy-MM-dd";
      if (buyDate.length() != 10) {
        buyStock.setHintMess("Enter a valid date.");
      }
      try {
        DateFormat df = new SimpleDateFormat(dateFormat);
        df.setLenient(false);
        df.parse(buyDate);
      } catch (ParseException e) {
        buyStock.setHintMess("Enter a valid date.");
        return;
      }

      double price = Double.parseDouble(operation.callStockAPI(ticker, buyDate)[3]);

      try {
        operation.addStockToFlexiblePortfolio(portfolioName, ticker, quantity, price, buyDate, commissionFee);
        buyStock.setHintMess(quantity + " units of " + " stock " + ticker + " added to portfolio "
            + portfolioName + " on " + buyDate);
        buyStock.clearField();
      }
      catch (IllegalArgumentException e) {
        buyStock.setHintMess(e.getMessage());
      }
    });

    actionMap.put("sellStock", () -> {
      sellStock = new SellStock("Sell Stock");
      sellStock.addActionListener(this);
      ((JFrame) sellStock).setLocation(((JFrame) this.mainView).getLocation());
      ((JFrame) this.mainView).dispose();
    });

    actionMap.put("sellStockButton", () -> {
      if (sellStock.getInput().length() == 4) {
        sellStock.setHintMess("Enter valid portfolio details.");
        return;
      }

      String[] sellStockDetails = sellStock.getInput().split(":");
      String portfolioName = sellStockDetails[0];
      String ticker = sellStockDetails[1];
      int quantity = 0;
      int commissionFee = 0;

      try{
        quantity = Integer.parseInt(sellStockDetails[2]);
      } catch (Exception ex) {
        buyStock.setHintMess("Quantity should be numeric value.");
      }

      try{
        commissionFee = Integer.parseInt(sellStockDetails[4]);
      } catch (Exception ex) {
        buyStock.setHintMess("Commission fee should be numeric value.");
      }

      String sellDate = sellStockDetails[3];

      //create a function to check if any of the above quantities are empty

      if (ticker.length() == 0) {
        sellStock.setHintMess("Ticker cannot be empty.");
        return;
      } else if (quantity == 0 || quantity < 0) {
        sellStock.setHintMess("Quantity cannot be empty or negative.");
        return;
      } else if (sellDate.length() == 0) {
        sellStock.setHintMess("Selling date cannot be empty.");
        return;
      } else if (commissionFee == 0 || commissionFee < 0) {
        sellStock.setHintMess("Commission fee cannot be empty or negative");
        return;
      }

      String dateFormat = "yyyy-MM-dd";
      if (sellDate.length() != 10) {
        sellStock.setHintMess("Enter a valid date.");
      }
      try {
        DateFormat df = new SimpleDateFormat(dateFormat);
        df.setLenient(false);
        df.parse(sellDate);
      } catch (ParseException e) {
        sellStock.setHintMess("Enter a valid date.");
        return;
      }

      double price = Double.parseDouble(operation.callStockAPI(ticker, sellDate)[3]);

      try {
        operation.sellStock(portfolioName, ticker, quantity, price, sellDate, commissionFee);
        sellStock.setHintMess(quantity + " units of " + " stock " + ticker + " added to portfolio "
            + portfolioName + " on " + sellDate);
        buyStock.clearField();
      }
      catch (IllegalArgumentException e) {
        buyStock.setHintMess(e.getMessage());
      }
    });

    actionMap.put("savePortfolio", () -> {
      savePortfolio = new SavePortfolio("Save Portfolio");
      savePortfolio.addActionListener(this);
      ((JFrame) savePortfolio).setLocation(((JFrame) this.mainView).getLocation());
      ((JFrame) this.mainView).dispose();
    });

    actionMap.put("savePortfolioButton", () -> {
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
      }
      catch (IllegalArgumentException e) {
        savePortfolio.setHintMess(e.getMessage());
      }

      ((JFrame) savePortfolio).setLocation(((JFrame) this.mainView).getLocation());
      ((JFrame) this.mainView).dispose();
    });

    actionMap.put("readPortfolio", () -> {
      readPortfolio = new ReadPortfolio("Read Portfolio");
      readPortfolio.addActionListener(this);
      ((JFrame) readPortfolio).setLocation(((JFrame) this.mainView).getLocation());
      ((JFrame) this.mainView).dispose();
    });

    actionMap.put("readPortfolioButton", () -> {
      String portfolioName = readPortfolio.getInput();
      if (portfolioName.length() == 1) {
        readPortfolio.setHintMess("Portfolio name cannot be empty.");
        return;
      }

      try {
        operation.readFromFile(portfolioName);
        readPortfolio.setHintMess("Portfolio " + portfolioName + " read successfully.");
        readPortfolio.clearField();
      }
      catch (IllegalArgumentException e) {
        readPortfolio.setHintMess(e.getMessage());
      }

      ((JFrame) readPortfolio).setLocation(((JFrame) this.mainView).getLocation());
      ((JFrame) this.mainView).dispose();
    });

    actionMap.put("createHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) mainView).setLocation(((JFrame) this.createView).getLocation());
      ((JFrame) this.createView).dispose();
    });

    actionMap.put("addHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) mainView).setLocation(((JFrame) this.buyStock).getLocation());
      ((JFrame) this.buyStock).dispose();
    });

    actionMap.put("sellHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) mainView).setLocation(((JFrame) this.sellStock).getLocation());
      ((JFrame) this.sellStock).dispose();
    });

    actionMap.put("saveHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) mainView).setLocation(((JFrame) this.savePortfolio).getLocation());
      ((JFrame) this.savePortfolio).dispose();
    });

    actionMap.put("readHomeButton", () -> {
      mainView = new MainView("Home");
      mainView.addActionListener(this);
      ((JFrame) mainView).setLocation(((JFrame) this.readPortfolio).getLocation());
      ((JFrame) this.readPortfolio).dispose();
    });

    actionMap.put("quit", () -> {
      System.exit(0);
    });

    return actionMap;
  }

  @Override
  public void operate(IOperation operation) {
    actionMap = initializeMap();
  }

  @Override
  public void createPortfolioHelper() {

  }

  @Override
  public void createFlexiblePortfolio() {

  }

  @Override
  public void createInflexiblePortfolio() {

  }

  @Override
  public void showExistingPortfolioHelper() {

  }

  @Override
  public void showAmountByDateHelper() {

  }

  @Override
  public void showCompositionHelper() {

  }

  @Override
  public void showCostBasisByDateHelper() {

  }

  @Override
  public void sellStockHelper() {

  }

  @Override
  public void createPortfolioCSV() {

  }

  @Override
  public void addStocksHelper(String portfolioName) {

  }

  @Override
  public void addStocksToFlexiblePortfolioHelper() {

  }

  @Override
  public void showGraph() {

  }

  @Override
  public String menuHelper() {
    return null;
  }

  @Override
  public void setView(IView v) {
    view = v;
    //provide view with all the callbacks
    view.addFeatures(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.str = e.getActionCommand();
    actionMap.get(str).run();
  }
}
