package Report;

import Account.Account;
import Transaction.Transaction;

public class Report {
  private Account account;
  private Transaction transaction;
  private String report;

  public Report(Account account, Transaction transaction, String report) {
    this.account = account;
    this.transaction = transaction;
    this.report = report;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public String getReport() {
    return report;
  }

  public void setReport(String report) {
    this.report = report;
  }

  @Override
  public String toString() {
    return "Report [account=" + account + ", transaction=" + transaction + ", report=" + report + "]";
  }

  
  
}
