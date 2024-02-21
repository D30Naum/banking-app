# Banking App
**Authors**: Nick Kent, Devi Naum  
**Course**: Comp 128 - Fall 2023

## Built With
<img src="https://1000logos.net/wp-content/uploads/2020/09/Java-Logo.png" width="80" height="50">

## Project Description & Expected Functionality
This banking application is designed as a virtual platform for managing financial information, including checking and savings account balances. It features money transfer functionality, enabling users to send funds to friends. The primary objective is to educate young kids and teenagers about financial responsibility by navigating and managing a virtual bank account.

## Challenges Faced
A major challenge was persisting and reloading the members' map after closing and reopening the user interface. We resolved this by creating toString and fromString methods for User objects, enabling the conversion of the map into a text file and vice versa.

## Data Structure Analysis
We used a HashMap <String, User> data structure for efficient user information storage and retrieval. This choice ensured quick access to user data, essential for a seamless user experience.

## Method Decomposition & Reuse
The project consists of five classes:

#### **NewUserUI**: For creating new accounts
#### **InfoUI**: Displaying user bank account information
#### **User**: Creating User objects for the hash map
#### **BankUsers**: Managing bank user information
#### **BankingApp**: The main class integrating all other classes

## Unfixed Bugs
Current unresolved issues include:

- Handling non-numeric inputs in numeric fields
- Managing withdrawal amounts exceeding account balance
- Validating decimal values in account balances

## Future Improvements
Future updates will focus on:

- Distinct user interfaces for kids and parents
- Specialized functionalities for kids, like transfer restrictions

## Code Structure
The application's codebase includes various classes and methods to handle different aspects of the banking process, such as user account management, transactions, and UI interactions.

## Acknowledgements 
<a href="https://mac-comp127.github.io/kilt-graphics/edu/macalester/graphics/package-summary.html">edu.macalester.graphics</a>