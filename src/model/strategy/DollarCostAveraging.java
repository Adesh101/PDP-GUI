package model.strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.portfolio.IFlexiblePortfolio;
import model.stocks.IStocks;
import model.stocks.Stocks;

/**
 * A public class which implements IDollarCostAveraging interface.
 */
public class DollarCostAveraging implements IDollarCostAveraging {

  IFlexiblePortfolio flexiblePortfolio;
  IStocks stocks;

  /**
   * A public constructor for DollarCostAveraging class.
   *
   * @param flexiblePortfolio object of IFlexiblePortfolio interface.
   */
  public DollarCostAveraging(IFlexiblePortfolio flexiblePortfolio) {
    this.flexiblePortfolio = flexiblePortfolio;
    stocks = new Stocks();
  }

  @Override
  public void strategyForExistingPortfolio(String portfolioName, List<String> stockNames,
      double amount, List<String> proportions, String date, List<String> commissionFee) {
    for (String s : commissionFee) {
      amount = amount - Double.parseDouble(s);
    }
    for (int i = 0; i < stockNames.size(); i++) {
      double individualAmount = (Double.parseDouble(proportions.get(i)) / 100) * amount;
      double individualPrice = stocks.getPriceByDate(stockNames.get(i), date);
      double quantity = individualAmount / individualPrice;
      flexiblePortfolio.buyStock(portfolioName, stockNames.get(i), String.valueOf(quantity),
          stocks.getPriceByDate(stockNames.get(i), date), date,
          Double.parseDouble(commissionFee.get(i)));
    }
  }

  @Override
  public void strategyForNewPortfolioWithEndDate(String portfolioName, List<String> stockNames,
      double amount, List<String> proportions, String startDate, String endDate, int interval,
      List<String> commissionFee) {
    for (String s : commissionFee) {
      amount = amount - Double.parseDouble(s);
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date iterativeDate = null;
    Date end = null;
    Calendar c = Calendar.getInstance();
    try {
      iterativeDate = formatter.parse(startDate);
      end = formatter.parse(endDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    c.setTime(iterativeDate);
    Date currentDate = new Date();
    while (!iterativeDate.after(currentDate)) {
      if (iterativeDate.after(end)) {
        break;
      }
      for (int i = 0; i < stockNames.size(); i++) {
        String date = formatter.format(iterativeDate);
        double individualAmount = (Double.parseDouble(proportions.get(i)) / 100) * amount;
        double individualPrice = stocks.getPriceByDate(stockNames.get(i), date);
        double quantity = individualAmount / individualPrice;
        flexiblePortfolio.buyStock(portfolioName, stockNames.get(i), String.valueOf(quantity),
            stocks.getPriceByDate(stockNames.get(i), date), date,
            Double.parseDouble(commissionFee.get(i)));
      }
      c.add(Calendar.DATE, interval);
      iterativeDate = c.getTime();
    }
  }

  @Override
  public void strategyForNewPortfolioWithoutEndDate(String portfolioName, List<String> stockNames,
      double amount, List<String> proportions, String startDate, int interval,
      List<String> commissionFee) {
    for (String s : commissionFee) {
      amount = amount - Double.parseDouble(s);
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date iterativeDate = null, currentDate = null;
    Calendar c = Calendar.getInstance();
    try {
      iterativeDate = formatter.parse(startDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    c.setTime(iterativeDate);
    currentDate = new Date();
    while (!iterativeDate.after(currentDate)) {
      for (int i = 0; i < stockNames.size(); i++) {
        String date = formatter.format(iterativeDate);
        double individualAmount = (Double.parseDouble(proportions.get(i)) / 100) * amount;
        double individualPrice = stocks.getPriceByDate(stockNames.get(i), date);
        double quantity = individualAmount / individualPrice;
        flexiblePortfolio.buyStock(portfolioName, stockNames.get(i), String.valueOf(quantity),
            stocks.getPriceByDate(stockNames.get(i), date), date,
            Double.parseDouble(commissionFee.get(i)));
      }
      c.add(Calendar.DATE, interval);
      iterativeDate = c.getTime();
    }
  }

}
