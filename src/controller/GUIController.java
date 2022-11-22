package controller;

import controller.actions.IActions;
import model.operation.IOperation;
import view.FunctionalView.MainView;
import view.FunctionalView.MainViewFunction;
import view.IView;
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
    actionMap.put("createPortfolio", () -> {
//      createView = new CreatePortfolio("create portfolio");
//      createView.addActionListener(this);
//      ((JFrame) createView).setLocation(((JFrame) this.mainView).getLocation());
//      ((JFrame) this.mainView).dispose();
      System.out.println("Hello World");
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
