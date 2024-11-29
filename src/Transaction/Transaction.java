package Transaction;

import java.time.LocalDate;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import Account.Account;
import User.User;

public class Transaction {
  private Account account;
  private int sourceAcc;
  private int targetAcc;
  private String transType;
  private double amount;
  private LocalDate date;
  private LinkedList<Transaction> transactions = new  LinkedList<Transaction>();

  public Transaction(Account account, int sourceAcc,String transType, double amount) {
    this.account = account;
    this.sourceAcc = sourceAcc;
    this.transType = transType;
    this.amount = amount;
    this.date = LocalDate.now();
  }

  public Transaction(Account account, int sourceAcc, int targetAcc ,String transType, double amount){
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

public static void deposit(Account account, double amount) {
  if (amount > 0) {
      account.updateBalance(amount);
      account.getTransactionHistory().add(new Transaction(account, account.getAccNumber(), "Deposit", amount));
      JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + account.getBalance());

  } else {
      JOptionPane.showMessageDialog(null, "Error. Please enter a valid amount.");
  }
}

public static void withdraw(Account account, double amount) {
  if (amount > 0 && amount <= account.getBalance()) {
    account.updateBalance(-amount);
    account.getTransactionHistory().add(new Transaction(account, account.getAccNumber(), "Withdrawal", amount));
    JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: $" + account.getBalance());
} else {
    JOptionPane.showMessageDialog(null, "Insufficient funds or invalid withdrawal amount.");
}
}

public static boolean transfer(Account source, Account target, double amount) {

  if (source == null || target == null) {
    JOptionPane.showMessageDialog(null, "Source or target account cannot be null.");
    return false;
  }

  if (amount > 0 && amount <= source.getBalance()) {
    source.updateBalance(-amount);
    target.updateBalance(amount);
    source.getTransactionHistory().add(new Transaction(source,source.getAccNumber(),target.getAccNumber(),"transfer",amount));
    target.getTransactionHistory().add(new Transaction(source,source.getAccNumber(),target.getAccNumber(),"transfer",amount));
    
    JOptionPane.showMessageDialog(null, "Successfully transfered "+ amount + "from account " + source.getAccNumber() + " to account " + target.getAccNumber());
    return true;
} else {
    JOptionPane.showMessageDialog(null, "Transfer failed due to insufficient funds or invalid amount.");
    return false;
    
}
}

  public static User selectTransferUser(User user) {
    LinkedList<User> userList = User.getUsers();

    if (userList.isEmpty()) {
      JOptionPane.showMessageDialog(null, "No users available to transfer.");
      return null;
    }

    User[] userArray = userList.toArray(new User[0]);

    Object selectedUser = JOptionPane.showInputDialog(null,"Select a user:","Hiru Bank",JOptionPane.INFORMATION_MESSAGE,null,userArray,userArray[0]);

    if (selectedUser != null) {
      User selected = (User) selectedUser;
      return selected;
    } else {
      JOptionPane.showMessageDialog(null, "No user selected.");
      return null;
    }
  }

  public static double setTransferAmount(User user) {
    double amount = Math.abs(Double.parseDouble(JOptionPane.showInputDialog("Input amount")));

    if (amount > user.getCheckingAcc().getBalance()) {
      JOptionPane.showMessageDialog(null, "Error. Insufficent funds!");
      return 0;
    } else {
      return amount;
    }
  }



  @Override
  public String toString() {
    return "Transaction [account=" + account + ", transType=" + transType + ", amount=" + amount + ", date=" + date
        + ", transactions=" + transactions + "]";
  }

  
}
