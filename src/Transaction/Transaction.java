package Transaction;

import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import Account.Account;

public class Transaction {
  private Account account;
  private int sourceAcc;
  private int targetAcc;
  private String transType;
  private double amount;
  private LocalDate date;
  private LinkedList<Transaction> transactions = new  LinkedList<Transaction>();

  public Transaction(Account account, int sourceAcc, int targetAcc ,String transType, double amount) {
    this.account = account;
    this.sourceAcc = sourceAcc;
    this.targetAcc = targetAcc;
    this.transType = transType;
    this.amount = amount;
    this.date = LocalDate.now();
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public int getSourceAcc() {
    return sourceAcc;
  }

  public void setSourceAcc(int sourceAcc) {
    this.sourceAcc = sourceAcc;
  }

  public int getTargetAcc() {
    return targetAcc;
  }

  public void setTargetAcc(int targetAcc) {
    this.targetAcc = targetAcc;
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

//  Account account, int sourceAcc, int targetAcc ,String transType, double amount, LocalDate date
  public static void deposit(Account account, double amount) {
    if (amount > 0) {
      account.updateBalance(amount);
      account.getTransactionHistory().add(new Transaction(account,account.getAccNumber(),222,amount,"Deposit",15000,date));
      JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + account.getBalance());
  } else {
      JOptionPane.showMessageDialog(null, "Invalid deposit amount.");
  }
}

public static boolean withdraw(Account account, double amount) {
  if (amount > 0 && amount <= account.getBalance()) {
    account.updateBalance(-amount);
    account.getTransactionHistory().add(new Transaction(account,account.getAccNumber(),222,amount,"withdrawal",15000,date));
    JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: $" + account.getBalance());
} else {
    JOptionPane.showMessageDialog(null, "Insufficient funds or invalid withdrawal amount.");
}
}

public static boolean transfer(Account source, Account target, double amount) {
  if (amount > 0 && amount <= source.getBalance()) {
    source.updateBalance(-amount);
    target.updateBalance(amount);
    source.getTransactionHistory().add(new Transaction(source,source.getAccNumber(),222,amount,"transfer",15000,date));
    target.getTransactionHistory().add(new Transaction(target,123,222,amount,"transfer",15000,date));
    JOptionPane.showMessageDialog(null, "Transfer successful from account " + source.getAccNumber() + " to account " + target.getAccNumber());
} else {
    JOptionPane.showMessageDialog(null, "Transfer failed due to insufficient funds or invalid amount.");
}
}



  @Override
  public String toString() {
    return "Transaction [account=" + account + ", transType=" + transType + ", amount=" + amount + ", date=" + date
        + ", transactions=" + transactions + "]";
  }

  
}
