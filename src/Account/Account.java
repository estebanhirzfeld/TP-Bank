package Account;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import Transaction.Transaction;
import User.User;

public abstract class Account {
  protected User user;
  protected String accType;
  protected int accNumber;
  protected double balance;
  protected LocalDate openingDate;
  protected LinkedList<Transaction> transactionHistory;

  public Account(User user, String accType, int accNumber, double balance) {
    this.user = user;
    this.accType = accType;
    this.accNumber = accNumber;
    this.balance = 0.0;
    this.openingDate = LocalDate.now();
    this.transactionHistory = new LinkedList<>(); 
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getAccType() {
    return accType;
  }

  public void setAccType(String accType) {
    this.accType = accType;
  }

  public int getAccNumber() {
    return accNumber;
  }

  public void setAccNumber(int accNumber) {
    this.accNumber = accNumber;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public LocalDate getOpeningDate() {
    return openingDate;
  }

  public void setOpeningDate(LocalDate openingDate) {
    this.openingDate = openingDate;
  }

  public List<Transaction> getTransactionHistory() {
    return transactionHistory;
  }

  public void setTransactionHistory(LinkedList<Transaction> transactionHistory) {
    this.transactionHistory = transactionHistory;
  }

  public void updateBalance(double amount) {
    this.balance += amount;
}
  
  @Override
  public String toString() {
    return "" + this.accNumber;
  }

}
