package Bank;

import java.time.LocalTime;
import javax.swing.JOptionPane;

import Account.Account;
import Account.CheckingAcc;
import Account.SavingsAcc;
import Options.accTypeOptions;
import Options.bankOptions;
import Options.loggedOptions;
import Options.userOptions;
import Options.transOptions;
import User.User;

public class Bank {
  private String name;

  public Bank(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addUser() {
    String userName = JOptionPane.showInputDialog("Enter user name:");
    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter user ID:"));
    User.addUser(userName, id);
    JOptionPane.showMessageDialog(null, "User added successfully");
  }

  public void modUserName(User user) {
    String newUserName = JOptionPane.showInputDialog("Enter new user name:");
    user.setUserName(newUserName);
  }

  public void modUserId(User user) {
    int newUserId = Integer.parseInt(JOptionPane.showInputDialog("Enter new user ID:"));
    user.setId(newUserId);
  }

  public void deleteUser(User user) {
    User.deleteUser(user);
    JOptionPane.showMessageDialog(null, "User deleted successfully.");
    user = null;
  }

  public User login() {
    String username = JOptionPane.showInputDialog("Enter your username:");
    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter your user ID:"));

    for (User user : User.getUsers()) {
      if (user.getUserName().equals(username) && user.getId() == id) {
        JOptionPane.showMessageDialog(null, "Login successful");
        return user;
      }
    }

    JOptionPane.showMessageDialog(null, "Error. User not found.");
    return null;
  }

  public void addSavingsAcc(User user) {
    if (user.getSavingsAcc() == null) {
      int accNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter your account number:"));
      SavingsAcc newUserSavingsAcc = new SavingsAcc(user, name, accNumber, 0);
      user.setSavingsAcc(newUserSavingsAcc);
      newUserSavingsAcc.setUser(user);
    }
  }

  public void addCheckingAcc(User user) {
    if (user.getCheckingAcc() == null) {
      int accNumber = Integer.parseInt(JOptionPane.showInputDialog("Enter your account number:"));
      CheckingAcc newUserCheckingAcc = new CheckingAcc(user, name, accNumber, 0);
      user.setCheckingAcc(newUserCheckingAcc);
      newUserCheckingAcc.setUser(user);
    }

  }

  public void deposit(User user){
    JOptionPane.showMessageDialog(null, "Deposit function");
  }

  public void withdraw(User user){
    JOptionPane.showMessageDialog(null, "withdraw function");
  }
  
  public void transfer(User user){
    JOptionPane.showMessageDialog(null, "transfer function");
  }


  public void checkName() {

  }


  // menu
  public static void bankOptions(Bank bank) {
    int option;

    do {
      option = JOptionPane.showOptionDialog(null, "ENUM BANCO \n Choose an option:", null, 0, 0, null,
          bankOptions.values(), bankOptions.values()[0]);
      switch (option) {
        case 0:
          bank.addUser();
          break;
        case 1:
          User user = bank.login();
          if (user != null) {
            loggedOptions(bank, user);
          }
          break;
        case 2:
          break;
      }

    } while (option != 2);

  }

  // menu 2
  public static void loggedOptions(Bank bank, User user) {
    int option;

    do {
      option = JOptionPane.showOptionDialog(null, "ENUM LOGGED (" + user.getUserName() + ") \n Choose an option:", null,
          0, 0, null, loggedOptions.values(), loggedOptions.values()[0]);
      switch (option) {
        case 0:
          accountOptions(bank, user);
          break;
        case 1:
          userOptions(bank, user);
          break;
        case 2:
          bank.deleteUser(user);
          option = 3;
          break;
        case 3:
          break;
      }

    } while (option != 3);
  }

  // menu 3
  public static void userOptions(Bank bank, User user) {
    int option;
    String msg = "Name: " + user.getUserName() + "\n ID: " + user.getId();
    do {
      option = JOptionPane.showOptionDialog(null, msg + "\n Choose an option:",
          "Modify User (" + user.getUserName() + ")", 0, 0, null,
          userOptions.values(), userOptions.values()[0]);
      switch (option) {
        case 0:
          bank.modUserName(user);
          break;
        case 1:
          bank.modUserId(user);
          break;
        case 2:
          break;
      }
    } while (option != 2);
  }

  // menu 4
  public static void accountOptions(Bank bank, User user) {
    int option;
    do {
      String msg = "Name: " + user.getUserName() + "\n Checking Account: " + user.getCheckingAcc()
          + "\n Savings Account: " + user.getSavingsAcc();

      option = JOptionPane.showOptionDialog(null, msg + "\n Choose an option:",
          "Account: (" + user.getUserName() + ")", 0, 0, null,
          accTypeOptions.values(), accTypeOptions.values()[0]);
      switch (option) {
        case 0:
          bank.addCheckingAcc(user);
          break;
        case 1:
          bank.addSavingsAcc(user);
          break;
        case 2:
          transOptions(bank, user);
          break;
        case 3:
        break;
      }

    } while (option != 3);
  }

  //menu 5
  public static void transOptions(Bank bank, User user){
    int option;
    do {
      option = JOptionPane.showOptionDialog(null, "Choose an option:",
          "Account: (" + user.getUserName() + ")", 0, 0, null,
          transOptions.values(), transOptions.values()[0]);
      switch (option) {
        case 0:
          bank.deposit(user);
          break;
        case 1:
          bank.withdraw(user);
          break;
        case 2:
          bank.transfer(user);
          break;
        case 3:
        break;
      }

    } while (option != 3);
  }

}
