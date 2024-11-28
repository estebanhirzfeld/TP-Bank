package Transaction;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import Account.Account;

public class Transaction {
  private Account account;
  private String transType;
  private double amount;
  private LocalDate date;
  private LinkedList<Transaction> transactions = new  LinkedList<Transaction>();

  public Transaction(Account account, String transType, double amount, LocalDate date) {
    this.account = account;
    this.transType = transType;
    this.amount = amount;
    this.date = date;
    this.transactions = transactions;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getTransType() {
    return transType;
  }

  public void setTransType(String transType) {
    this.transType = transType;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LinkedList<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(LinkedList<Transaction> transactions) {
    this.transactions = transactions;
  }


  @Override
  public String toString() {
    return "Transaction [account=" + account + ", transType=" + transType + ", amount=" + amount + ", date=" + date
        + ", transactions=" + transactions + "]";
  }

  
}
