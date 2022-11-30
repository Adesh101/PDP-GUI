package model.strategy;

import java.util.List;
import java.util.Set;

public interface IDollarCostAveraging {
  void strategyForExistingPortfolio(String portfolioName, List<String> stockNames, double amount, List<String> proportions, String date, List<String> commissionFee);
  //void strategyForExistingPortfolioWithoutEndDate(String portfolioName, double amount, double[] proportions, String startDate, int interval);
  void strategyForNewPortfolioWithEndDate(String portfolioName, String[] stockNames, double amount,
      double[] proportions, String startDate, String endDate, int interval, double[] commissionFee);
  void strategyForNewPortfolioWithoutEndDate(String portfolioName, String[] stockNames, double amount, double[] proportions, String startDate, int interval, double[] commissionFee);
//  List<String> updateCostBasis(String portfolioName,String date, double costBasisValue);
//
//  List<String> updateFuturePortfolioRecords(String portfolioName, String ticker, String date, double quantity,
//      double price);

}
