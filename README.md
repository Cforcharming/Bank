# Bank
**Software Engineering Laboratory**  
**This is the release note of the Bank Project**  
## Technique Design
### Environment
This code is built under **JetBrains Intellij Idea Ultimate 2019.1.2**  
With Java Development Kit **OpenJDK 11.0.1**  
Use **JRE 11** or above
### Version Control
Git is imported as version control.  
GitHub: https://github.com/cforcharming/bank  
**This repository is currently private, but will be made public later.**  
### Test Module
The JUnit5 is used as test tool.  
### MVC Module
Model - entity classes:  
* package Models  
* Package Util  
Visual - boundary classes:
* package Pages  
Controller - controller classes:  
* package Main  
* package Controllers  
### Module Structure
 __________Bank  
 |  
 |_________src  
 | |_______Main  
 | |   |___Bank.java  
 | |  
 | |_______Models  
 | |   |___Account.java  
 | |   |___AccountDao.java  
 | |  
 | |_______Util  
 | |   |___FontDao.java  
 | |   |___IconDao.java  
 | |  
 | |_______Controllers  
 | |   |___MainController.java  
 | |   |___PanelController.java  
 | |  
 | |_______Pages  
 |     |___AutoRefreshableJPanel.java  
 |     |___DepositPanel.java  
 |     |___LoginPanel.java  
 |     |___MainFrame.java  
 |     |___MainPanel.java  
 |     |___RegisterPanel.java  
 |     |___TransferPanel.java  
 |     |___UserPanel.java  
 |     |___WithdrawPanel.java  
 |  
 |_________out  
 |  
 |_________test  
 |  
 |_________resources  
 |    
 |_________Bank.iml  
 |_________.idea  
 |_________.git  
#### Descriptions
* **src** source code  
* **out** compiled release  
* **test** JUnit test ase  
* **resources** database  
## Usage
### log in
You need to input the account No. and the PIN number to log in.  
Or to register one, and the account No. and the PIN will be provided to you.
>When a customer joins the bank, they are required choose an account type to open, 
and must credit it with a minimum figure. A customer may open more than one type of account.  
In order to open an account, the customer must provide the following information:  
>* **name**
>* **address**
>* **date of birth**
>* **type of account to be opened**  
>
>Only customers under the age of 16 may open a Junior account.  
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
### Account Management
#### Suspend Account
>In some situations, 
accounts may be suspended and no further transactions may occur until the account is re-instated.  
#### Close Account
>A customer can choose to close their account provided that the balance has been cleared.  

<br /><br />
Copyright: QMUL & BUPT  
Author: Zhang, Hanwen