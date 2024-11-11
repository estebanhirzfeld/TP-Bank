package Transaction;

import java.time.LocalDate;
import java.util.LinkedList;

import Account.Account;

public class Transaction {
  private Account account;
  private String transType;
  private double amount;
  private LocalDate date;
    private LinkedList<Transaction> transactions = new  LinkedList<Transaction>();
}
