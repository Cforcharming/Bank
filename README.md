# Bank
**Software Engineering Laboratory**  

Over the EBU5304 module in the next few weeks, 
you will be required to develop a simple banking system using Agile methods, 
from requirements, through to analysis/design, implementation and testing, 
following the guidelines and activities outlined in the lectures. 
Each week you will be required to carry out one phase in the development lifecycle. 
Bear in mind that there is no absolute right answer – your solution may be perfectly appropriate.  

It should be noted that determining the requirements of a system is one
 of the most important and complex phases in any development project – 
if this is wrong, it doesn’t matter how good the system will be designed, -- 
it will be the wrong system! However, in order to simplify the case study, 
you will start from the point at which the requirements have been stated in a detailed level. 
As in real systems though, there may be more details you want to know that are missing from these notes 
– in this situation you can make your own assumptions or ask the Lecturers/Demonstrators for clarification.  
## Guideline
A simple banking system is to be developed with the intention of providing a generic, 
reusable system from which to develop more realistic systems. 
he requirements of the system are to offer a number of different accounts, 
each of which provides specific services to the customer. 
A simple banking system is to be developed with the intention of providing a generic, 
reusable system from which to develop more realistic systems. 
The requirements of the system are to offer a number of different accounts, 
each of which provides specific services to the customer. 
The following are all types of accounts that must be supported by the system:
## Functions
### Accounts
>The following are all types of accounts that must be supported by the system:
>* **saver account**
>* **junior account**
>* **current account**  
>
>When a customer joins the bank, they are required choose an account type to open, 
and must credit it with a minimum figure. A customer may open more than one type of account.  
In order to open an account, the customer must provide the following information:  
>* **name**
>* **address**
>* **date of birth**
>* **type of account to be opened**  
>
>Only customers under the age of 16 may open a Junior account. 
To determine the credit status of a customer, 
the bank sends the customer's details to a Credit Agency, 
who then carries out a credit search. 
Provided that the customer has a satisfactory credit history, a new account is opened. 
Each account has a unique account number. 
A customer is also issued a separate personal identification number (PIN) for that account.  
### Deposit Funds
>Funds may be deposited in an account provided that the depositor provides the appropriate account number. 
When funds are deposited, they are either cleared (the funds have been fully credited, 
e.g Cash), or un-cleared (transfer of funds is pending, e.g. using Cheque). 
Cleared funds are immediately credited to the account.  
### Clear Funds
>An external bank clearing system periodically clears un-cleared funds. 
Once cleared, they are immediately credited to the account.
### Withdraw Funds
>Customers may withdraw funds from an account by supplying their account number, 
an appropriate identification (in this case, their PIN), and the amount to be withdrawn. 
A customer cannot withdraw more funds than their limit permits. 
The type of account the funds are being drawn from determines a customer’s limit. 
In the case of Junior and Saver accounts, no withdrawal should result in a negative balance. 
In the case of a Current account, a customer may withdraw additional funds, up to, 
but not exceeding, their overdraft limit. For a withdrawal from a Saver account, 
a minimum period of notice (in days) must be given before any withdrawal can be made.  
### Suspend Account
>In some situations, 
accounts may be suspended and no further transactions may occur until the account is re-instated.  
### Close Account
>A customer can choose to close their account provided that the balance has been cleared.  

<br /><br />
Copyright: Queen Mary University of London
