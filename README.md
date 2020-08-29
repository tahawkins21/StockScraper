#README

This Java application demonstrates use of multi-threading to scrape yahoo finance web pages. Each thread makes a connection to the yahoo finance page for the specific stock ticker and uses a regular expression to pull the current stock price, EPS, and PE from the raw HTML.

Download SP500tickers.csv (You can add or remove stock tickers before you run the application)
Download and run FinalProject.jar

When Prompted, open the SP500tickers.csv you just downloaded. It contains a list of tickers we want to search for.

Then, when prompted next, write a new filename and choose a location for the new file. Results will be written to the new file.

Ticker will read "Loading" until all results are in. Then, select a ticker and click "Find Stock Data"
IT MAY TAKE UP TO 5 Minutes to complete all web scraping. Refactoring is necessary to reduce run time.
