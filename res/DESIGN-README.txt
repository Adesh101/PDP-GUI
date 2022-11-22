DESIGN CHANGES
Added FlexiblePortfolio and InflexiblePortfolio classes : We have added these classes to support Flexible portfolios. Earlier the operation model contained the methods to perform the operations but now since we have two different types of portfolios and different logic for them, we have refactored the code.
Added fileHandling interface: We have created new interface so that if there is a new input source in the future, we can simply implement this interface to get input from new source with minimal design changes.
Created helper methods in controller: We have created helper methods in the controller and significantly reduced the size of the switch case.
Hash map structure changed: In the previous part, the hash map contained portfolio name along with the stocks present and their composition. But now, We have added date as a key in the hash map to keep better track of the transactions.

DESIGN README
The application is built using MVC Design. All the functionalities are implemented in the model of the application.
The model includes five components:
Operation: It contains all the functionalities of the application like creating the portfolio, adding stocks to the portfolio, showing all the portfolios present, returning the composition, and returning the value of the portfolio for a given date, calculating the cost basis and the portfolio performance graph of a given range.
Stocks: It is the part that deals with the stock data. It calls the Alphavantage API and handles the resulting data.
File Handling:It currently deals with taking input from csv and will ensure easy implementation for any new input source in the future.
Portfolio: It contains two interfaces for Flexible portfolio and Inflexible portfolio respectively. Both have their own implementations and contains the respective functionalities.
Plot: It deals with plotting of the performance graph. Currently, we are showing the performance graph in terms of a line chart.

The controller includes:
Controller: It is responsible for controlling the whole program. This controller handles input and redirects the flow to the appropriate child controllers
Add stock to flexible portfolioL controls the input and flow for the add stock for flexible portfolio
Add stock to the portfolio: controls the input and flow for the add stock operation for inflexible portfolio
Create a new portfolio: controls the input and flow for the new portfolio creation through the console for inflexible portfolio
Create new portfolio CSV: controls the input and flow for the creation of a new portfolio from a CSV file
Create a new portfolio for flexible portfolio: controls the input and flow for the new portfolio creation through console for a flexible portfolio
Show amount of portfolio by date: controls the input and flow when the amount of portfolio needs to be estimated for a given date
Show composition: controls the input and flow for the show composition operation 
Show existing portfolios: controls the flow when all existing portfolios need to be displayed
Cost basis: controls the input and flow when cost basis needs to be calculated for a given date
Sell stock:controls the input and flow when stocks need to be sold from a given portfolio


The view includes the text-based interface where the input is read and output messages are displayed. A readable object is used to read the user input and an appendable object is used to return the output

