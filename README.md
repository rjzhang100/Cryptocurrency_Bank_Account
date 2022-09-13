# Cryptocurrency Bank Account 

## Author: Ray Zhang
### CPSC 210 Term Project
- ---
### What Will the Program Do?
First and foremost, the purpose of the program is to create a friendly interface where
users can track and maintain their cryptocurrencies. The program will provide details about
cryptocurrencies the user may have based on the user-inputted cryptocurrencies they may own. 
These details include trackers of each cryptocurrency's quantity, market value at a given time
and be able to display historical data of the currencies. There will also be a predicting 
feature in the program, which will, based on historical data of the currency, attempt to predict
the future change in prices of the currency. 

This program is to be used by people not only interested in learning more about cryptocurrencies,
but also for people to manage and organize their holdings. 
- ---
### Who Will Use this Program?
This program is designed for anyone with cryptocurrency holdings, or anyone wanting to 
get involved in cryptocurrencies. Outside of using it for organizing their own holdings,
they can also use the program to research historical data and consider the predictions
proposed by the program regarding trends in the data.
- --- 
### Why is this Project of Interest to You?
From the start, I knew I wanted to try implementing a machine learning algorithm as part of my
assignment. With cryptocurrencies gaining traction more than ever, a wide pool of data is publicly 
available for use in training an algorithm. In addition, I've never really researched 
cryptocurrencies myself, or tried to learn what they really are. In writing this program,
I can also learn about how cryptocurrencies might interact in a market and hopefully
understand, at a fundamental level, what they are. As well, given the volatile and truthfully
unpredictable nature of cryptocurrencies, designing an effective prediction algorithm will 
demand some creativity; I hope to learn some new approaches and fully apply myself.
- ---
### User Stories
*As a user, I want to be able to...* 

- Add a new cryptocurrency and the quantity I have
- Remove a cryptocurrency from my holdings
- Add or subtract the quantity of any one of my holdings
- See information about all my holdings, including quantity and types that I have
- See predictions about possible movement of the cryptocurrency to inform my decision making today
- See each type of cryptocurrency I have and be able to compare them with each other
- See historical data regarding the activity of the cryptocurrency
- Be able to save all my holdings and all the information about them
- Be able to recover my holdings based on their most recent updates
- Be able to access all the above features through a friendly GUI
- ---
### Phase 4: Task 2
*Representative Sample of Output Log*

Thu Nov 25 15:24:04 PST 2021
Loaded 60 units of dogecoin from ./data/account.json!

Thu Nov 25 15:24:04 PST 2021
Loaded 50 units of bitcoin from ./data/account.json!

Thu Nov 25 15:24:10 PST 2021
Deducted 20 units from dogecoin holding!

Thu Nov 25 15:24:15 PST 2021
Added 20 units to bitcoin holding!

Thu Nov 25 15:24:23 PST 2021
Added 100 units of coin!

Thu Nov 25 15:24:30 PST 2021
Saved 40 units of dogecoin to ./data/account.json!

Thu Nov 25 15:24:30 PST 2021
Saved 70 units of bitcoin to ./data/account.json!

Thu Nov 25 15:24:30 PST 2021
Saved 100 units of coin to ./data/account.json!
- ---
### Phase 4: Task 3
*Reflection on Program Design*

Overall I think the design of the program is decent, although 
the coupling between the classes Cryptocurrency, Submenus and Account
could be improved. Rather than having a holding list of its own,
Submenus should find a way to get the list from CryptoAccount, 
since it extends CryptoAccount. As well, the cohesion in CryptoAccount
and Submenus is currently quite low and could see improvement. 
Both classes handle all of setting up the GUI and parsing user input. 
Instead, perhaps different components of the GUI can be handled by
different classes, and user input parsing can be the job of another class,
right now Submenus and CryptoAccount has low cohesion.

I would also restructure Account and Cryptocurrency classes to follow
the Observer pattern, where Cryptocurrency is the observable and Account
is the observer, so that it can track changes in holdings. 
