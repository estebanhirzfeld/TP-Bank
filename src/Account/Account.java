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

}
