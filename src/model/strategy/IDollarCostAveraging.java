package model.strategy;

import java.util.List;

/**
 * A public interface to support Dollar Cost Averaging strategy.
 */
public interface IDollarCostAveraging {

  /**
   * A method to support one time strategy for existing portfolio.
   *
   * @param portfolioName portfolio name
   * @param stockNames    stock names
   * @param amount        total amount to be invested
   * @param proportions   weightage of the stocks
   * @param date          date of investment
   * @param commissionFee commission fee per transaction
   */
  void strategyForExistingPortfolio(String portfolioName, List<String> stockNames,
      double amount, List<String> proportions, String date, List<String> commissionFee);

  /**
   * A method to implement recurring strategy on new portfolio which have end date.
   *
   * @param portfolioName portfolio name
   * @param stockNames    stocks to be added
   * @param amount        total amount to be invested
   * @param proportions   weightage of the stocks to be added
   * @param startDate     start date of strategy
   * @param endDate       end date of strategy
   * @param interval      frequency of investment
   * @param commissionFee commission fee per transaction
   */
  void strategyForNewPortfolioWithEndDate(String portfolioName, List<String> stockNames,
      double amount, List<String> proportions, String startDate, String endDate,
      int interval, List<String> commissionFee);

  /**
   * A method to implement recurring strategy on new portfolio which does not have an end date.
   *
   * @param portfolioName portfolio name
   * @param stockNames    stocks to be added
   * @param amount        total amount to be invested
   * @param proportions   weightage of the stocks to be added
   * @param startDate     start date of the strategy
   * @param interval      frequency of investment
   * @param commissionFee commission fee per transaction
   */
  void strategyForNewPortfolioWithoutEndDate(String portfolioName, List<String> stockNames,
      double amount, List<String> proportions, String startDate, int interval,
      List<String> commissionFee);


}
