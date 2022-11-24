package controller;

import java.text.ParseException;
import model.operation.IOperation;
import view.FunctionalView.MainView;
import view.TextFieldView.BuyStock;
import view.TextFieldView.CreateFlexiblePortfolio;
import view.FunctionalView.MainViewFunction;
import view.TextFieldView.ReadPortfolio;
import view.TextFieldView.SavePortfolio;
import view.TextFieldView.SellStock;
import view.TextFieldView.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
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
  private String str;
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
      createView.setHintMess(operation.checkValidDate(portfolioDate));
      try {
        operation.createFlexiblePortfolio(portfolioName, portfolioDate);
        createView.setHintMess("Portfolio " + portfolioName + " on " + portfolioDate + " created.");
        createView.clearField();
      }
      catch (IllegalArgumentException e) {
        createView.setHintMess(e.getMessage());
      }
    });


    operationMap.put("buyStock", () -> {
      buyStock = new BuyStock("Buy Stock");
      buyStock.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });


    operationMap.put("addStock", () -> {
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
      buyStock.setHintMess(operation.checkValidDate(buyDate));
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


    operationMap.put("sellStock", () -> {
      sellStock = new SellStock("Sell Stock");
      sellStock.addActionListener(this);
      ((JFrame) this.mainView).dispose();
    });


    operationMap.put("sellStockButton", () -> {
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
      sellStock.setHintMess(operation.checkValidDate(sellDate));
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
      }
      catch (IllegalArgumentException e) {
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
      }
      catch (IllegalArgumentException e) {
        readPortfolio.setHintMess(e.getMessage());
      }

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
}
