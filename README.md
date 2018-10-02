# FinanceOrganizer

This is a Java application that imports bank statements and displays the bank transactions by categories and date. A comprehensible summary by year can be visualized, and searches by date range can be performed. The categories are customizable, and it supports more than one account, including checking and savings account and credit cards. The application uses JavaFX for the UI and MySQL with stored procedures as database to access data. The banking transactions are imported into the database from csv files supplied by the bank in the online banking system. It is a work in progress.


## Functionalities

### Yearly Summary

The summary of all transactions is displayed in a table format and organized by categories

![ImportData](./imgs/yearsummary.png?raw=true)

By double clicking a cell in the table, it brings the summary of the selected category/month

![ImportData](./imgs/sumarymonth.PNG?raw=true)

### Search by Date Range

Transactions can also be displayed by a selected date range

![ImportData](./imgs/searchbycat.PNG?raw=true)

### Customized Categories

The categories used to classify the data can be customized

![ImportData](./imgs/addcategory.PNG?raw=true)

### Importing Data

Most banks provide bank statements in csv format. 
The application parses the file, based on the select account and imports the data to the database.

![ImportData](./imgs/importfile.PNG?raw=true)

A summary of the parsed data is diplayed, and any transactions with errors can be manually edited.

![ImportData](./imgs/importcheck.PNG?raw=true)

By double-clicking the invalid transaction, it can be manually edited to fix potential errors.

![ImportData](./imgs/editinvalid.PNG?raw=true)

### Classifying the data

After a batch of transactions are imported to the database, they are unclassified.
The application provides two methods to classify:
  1. Automatic: Uses alreadt classified transactions as source to classify new data.
  2. Manual: Individually apply a category to a transaction by double-clicking.
 
![ImportData](./imgs/uncatTransblurry.png?raw=true)
![ImportData](./imgs/editcat.png?raw=true)

## Database

This application use a MySQL database.

### ER-Diagram
![ImportData](./imgs/erdiagram.png?raw=true)


## Author

* **Alexandre Carvalho** - *Initial work* - [AlexMCarv](https://github.com/AlexMCarv)
