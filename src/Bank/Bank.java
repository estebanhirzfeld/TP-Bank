package Bank;

import Account.Account;
import Account.CheckingAcc;
import Account.SavingsAcc;
import Options.accTypeOptions;
import Options.bankOptions;
import Options.loggedOptions;
import Options.transOptions;
import Options.transferOptions;
import Options.userOptions;
import Transaction.Transaction;
import User.User;
import java.util.LinkedList;
import javax.swing.JOptionPane;

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

  public void deposit(User user) {
    double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter deposit amount:"));
    if (user.getCheckingAcc() != null) {
        Transaction.deposit(user.getCheckingAcc(), amount);
    }
}

public void withdraw(User user) {
    double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter withdrawal amount:"));
    if (user.getCheckingAcc() != null) {
        Transaction.withdraw(user.getCheckingAcc(), amount);
    }
}

public void transfer(User user) {
  double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter transfer amount:"));

    if (user.getCheckingAcc() == null) {
      JOptionPane.showMessageDialog(null, "You must have a checking account to make transfers.");
    }

    User targetUser = Transaction.selectTransferUser(user);

    if (targetUser == null || targetUser.getCheckingAcc() == null) {
      JOptionPane.showMessageDialog(null, "Target user does not have a checking account.");
    }

    Transaction.transfer(user.getCheckingAcc(), targetUser.getCheckingAcc(), amount);
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
          if (user.getCheckingAcc() == null) {
            JOptionPane.showMessageDialog(null, "You must have a Checking Account first to make transactions!");
            break;
          }
          transOptions(bank, user);
          break;
        case 2:
          userOptions(bank, user);
          break;
        case 3:
          bank.deleteUser(user);
          option = 4;
          break;
        case 4:
        //historial de la cuenta
          break;
        case 5:
          break;
      }

    } while (option != 5);
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
          break;
      }

    } while (option != 2);
  }

  // menu 5
  public static void transOptions(Bank bank, User user) {
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
          transferOptions(bank, user);
          break;
        case 3:
        String msg = "Checking account balance: " + user.getCheckingAcc().getBalance() + "\n Savings account balance: " + user.getSavingsAcc().getBalance();
        JOptionPane.showMessageDialog(null, msg);
          break;
        case 4:
        break;
      }
    } while (option != 4);
  }

  // menu 6
  public static void transferOptions(Bank bank, User user) {
    int option;
    User selectedUser = null;
    double transferAmount = 0;
    String msg = "Selected User: " + selectedUser + "\nAmount: " + transferAmount + "\n\nAvalaible Funds: " + user.getCheckingAcc().getBalance() + "\n Choose an option:";
    do {
      msg = "Selected User: " + selectedUser + "\n Amount: " + transferAmount;
      option = JOptionPane.showOptionDialog(null, msg,
          "Account: (" + user.getUserName() + ")", 0, 0, null,
          transferOptions.values(), transferOptions.values()[0]);
      switch (option) {
        case 0:
          selectedUser = Transaction.selectTransferUser(user);
          break;
        case 1:
          transferAmount = Transaction.setTransferAmount(user);
          break;
        case 2:
          if(transferAmount <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid Action, please enter valid amount");
          } else{
            Transaction.transfer(user.getCheckingAcc(), selectedUser.getCheckingAcc(), transferAmount);
          }
          break;
        case 3:
          break;
      }

    } while (option != 3);
  }

}
