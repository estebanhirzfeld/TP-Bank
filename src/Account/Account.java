package Account;

import java.time.LocalDate;
import java.util.LinkedList;

import User.User;

public class Account {
  private User user;
  private String accType;
  private int accNumber;
  private double balance;
  private LocalDate openingDate;
  private SavingsAcc savingsAcc;
  private CheckingAcc checkingAcc;

  public Account(User user, String accType, int accNumber, double balance, LocalDate openingDate, SavingsAcc savingsAcc,
      CheckingAcc checkingAcc) {
    this.user = user;
    this.accType = accType;
    this.accNumber = accNumber;
    this.balance = balance;
    this.openingDate = openingDate;
    this.savingsAcc = savingsAcc;
    this.checkingAcc = checkingAcc;
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

  public SavingsAcc getSavingsAcc() {
    return savingsAcc;
  }

  public void setSavingsAcc(SavingsAcc savingsAcc) {
    this.savingsAcc = savingsAcc;
  }

  public CheckingAcc getCheckingAcc() {
    return checkingAcc;
  }

  public void setCheckingAcc(CheckingAcc checkingAcc) {
    this.checkingAcc = checkingAcc;
  }

  @Override
  public String toString() {
    return "Account [user=" + user + ", accType=" + accType + ", accNumber=" + accNumber + ", balance=" + balance
        + ", openingDate=" + openingDate + ", savingsAcc=" + savingsAcc + ", checkingAcc=" + checkingAcc + "]";
  }

  

}
