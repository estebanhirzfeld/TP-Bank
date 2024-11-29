package Bank;

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
import java.util.List;
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

  public static boolean isNumeric(String str) {
    for (char c : str.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  public void addUser() {
    String userName = getValidUserName();
    if (userName == null) {
      return;
    }

    int id = getValidUserId();
    if (id == -1) {
      return;
    }

    // Add user and confirm
    User.addUser(userName, id);
    JOptionPane.showMessageDialog(null, "User added successfully");
  }

  private String getValidUserName() {
    String userName;

    while (true) {
      userName = JOptionPane.showInputDialog("Enter user name:");

      if (userName == null) {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to go back?", "Cancel",
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          return null;
        }
        continue;
      }

      if (userName.trim().isEmpty() || !userName.matches("[a-zA-Z]+") || userName.length() < 3) {
        JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid name with at least 3 letters. Try again.");
      } else {
        break;
      }
    }
    return userName;
  }

  private int getValidUserId() {
    int id;

    while (true) {
      String input = JOptionPane.showInputDialog("Enter user ID:");

      if (input == null) {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to go back?", "Cancel",
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          return -1;
        }
        continue;
      }

      if (input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "ERROR: ID cannot be empty. Please try again.");
        continue;
      }

      if (isNumeric(input)) {
        id = Integer.parseInt(input);

        if (id >= 5 && id <= 250) {
          break;
        } else {
          JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid ID between 5 and 250.");
        }
      } else {
        JOptionPane.showMessageDialog(null, "ERROR: Please enter a numeric ID.");
      }
    }
    return id;
  }

  public void modUserName(User user) {
    String newUserName = getValidUserName();

    if (newUserName != null) {
      user.setUserName(newUserName);
      JOptionPane.showMessageDialog(null, "User modified successfully");
    }
  }

  public void modUserId(User user) {
    int newUserId = getValidUserId();

    if (newUserId == -1) {
      return;
    }

    user.setId(newUserId);
    JOptionPane.showMessageDialog(null, "ID modified successfully");
  }

  public Boolean deleteUser(User user) {

    int confirmation = JOptionPane.showConfirmDialog(null,
        "Are you sure you want to delete your account?",
        "Confirm Deletion",
        JOptionPane.YES_NO_OPTION);

    if (confirmation != JOptionPane.YES_OPTION) {
      JOptionPane.showMessageDialog(null, "Account deletion canceled.");
      return false;
    }

    boolean hasBalance = false;
    double checkingBalance = 0;
    double savingsBalance = 0;

    if (user.getCheckingAcc() != null) {
      checkingBalance = user.getCheckingAcc().getBalance();
      if (checkingBalance > 0) {
        hasBalance = true;
      }
    }

    if (user.getSavingsAcc() != null) {
      savingsBalance = user.getSavingsAcc().getBalance();
      if (savingsBalance > 0) {
        hasBalance = true;
      }
    }

    if (hasBalance) {
      double totalBalance = checkingBalance + savingsBalance;
      int donateChoice = JOptionPane.showConfirmDialog(null,
          "You have a balance of $" + totalBalance + " in your account. \n" +
              "Would you like to donate it to charity?",
          "Balance Detected",
          JOptionPane.OK_CANCEL_OPTION);

      if (donateChoice != JOptionPane.OK_OPTION) {
        JOptionPane.showMessageDialog(null, "Account deletion canceled.");
        return false;
      }
    }

    User.deleteUser(user);
    JOptionPane.showMessageDialog(null, "User deleted successfully.");
    return true;
  }

  public User login() {

    String username = JOptionPane.showInputDialog("Enter your username:");

    if (username == null || username.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "ERROR: Username cannot be empty. Please try again.");
      return null;
    }

    String idInput = JOptionPane.showInputDialog("Enter your user ID:");

    if (idInput == null || idInput.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "ERROR: User ID cannot be empty. Please try again.");
      return null;
    }

    if (!isNumeric(idInput)) {
      JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid numeric ID.");
      return null;
    }

    int id = Integer.parseInt(idInput);

    for (User user : User.getUsers()) {
      if (user.getUserName().equals(username) && user.getId() == id) {
        JOptionPane.showMessageDialog(null, "Login successful");
        return user;
      }
    }

    JOptionPane.showMessageDialog(null, "Error. User not found.");
    return null;
  }

  private int getValidAccountNumber() {
    int accNumber = -1;

    while (true) {
      String input = JOptionPane.showInputDialog("Enter your account number:");

      if (input == null || input.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "ERROR: Account number cannot be blank. Please try again.");
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to go back?", "Cancel",
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          return -1;
        }
        continue;
      }

      if (isNumeric(input)) {
        accNumber = Integer.parseInt(input);

        if (accNumber >= 2 && accNumber <= 20) {
          break;
        } else {
          JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid account number between 2 and 20.");
        }
      } else {
        JOptionPane.showMessageDialog(null, "ERROR: Please enter a numeric account number.");
      }
    }
    return accNumber;
  }

  public void addCheckingAcc(User user) {
    if (user.getCheckingAcc() == null) {
      int accNumber = getValidAccountNumber();

      if (accNumber == -1) {
        return;
      }

      CheckingAcc newUserCheckingAcc = new CheckingAcc(user, name, accNumber, 0);
      user.setCheckingAcc(newUserCheckingAcc);
      newUserCheckingAcc.setUser(user);

      JOptionPane.showMessageDialog(null, "Checking account added successfully.");
    }
  }

  public void addSavingsAcc(User user) {
    if (user.getSavingsAcc() == null) {
      int accNumber = getValidAccountNumber();

      if (accNumber == -1) {

        return;
      }

      SavingsAcc newUserSavingsAcc = new SavingsAcc(user, name, accNumber, 0);
      user.setSavingsAcc(newUserSavingsAcc);
      newUserSavingsAcc.setUser(user);

      JOptionPane.showMessageDialog(null, "Savings account added successfully.");
    }
  }

  public void deposit(User user) {
    if (user.getCheckingAcc() != null) {
      while (true) {
        String input = JOptionPane.showInputDialog("Enter deposit amount:");

        if (input == null || input.trim().isEmpty()) {

          return;
        }

        if (!isNumeric(input)) {
          JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid numeric amount.");
          continue;
        }

        double amount = Double.parseDouble(input);

        if (amount > 0) {
          Transaction.deposit(user.getCheckingAcc(), amount);
          return;
        } else {
          JOptionPane.showMessageDialog(null, "ERROR: Amount must be greater than zero.");
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "ERROR: User does not have a checking account.");
    }
  }

  public void withdraw(User user) {
    if (user.getCheckingAcc() != null) {
      while (true) {
        String input = JOptionPane.showInputDialog("Enter withdrawal amount:");

        if (input == null || input.trim().isEmpty()) {

          return;
        }

        if (!isNumeric(input)) {
          JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid numeric amount.");
          continue;
        }

        double amount = Double.parseDouble(input);

        if (amount > 0 && amount <= user.getCheckingAcc().getBalance()) {
          Transaction.withdraw(user.getCheckingAcc(), amount);

          return;
        } else {
          JOptionPane.showMessageDialog(null,
              "ERROR: Amount must be less than or equal to your balance ($" + user.getCheckingAcc().getBalance() + ")");
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "ERROR: User does not have a checking account.");
    }
  }

  private static void transfer(User user, User selectedUser, double transferAmount) {
    if (selectedUser == null) {
        JOptionPane.showMessageDialog(null, "Error: No recipient selected for the transfer.");
        return;
    }

    if (user.equals(selectedUser)) {
        JOptionPane.showMessageDialog(null, "Error: You cannot transfer funds to your own account.");
        return;
    }

    if (transferAmount <= 0) {
        JOptionPane.showMessageDialog(null, "Invalid Action: Please enter a valid amount.");
        return;
    }

    if (selectedUser.getCheckingAcc() == null) {
        JOptionPane.showMessageDialog(null, "Error: The selected user does not have a checking account.");
        return;
    }

    if (transferAmount > user.getCheckingAcc().getBalance()) {
        JOptionPane.showMessageDialog(null, "Error: Insufficient funds! Available balance: $"
                + user.getCheckingAcc().getBalance());
        return;
    }

    Transaction.transfer(user.getCheckingAcc(), selectedUser.getCheckingAcc(), transferAmount);
    JOptionPane.showMessageDialog(null, "Transfer successful! Amount: $" + transferAmount);
}


  public static String formatTransactionHistory(User user) {
    StringBuilder msg = new StringBuilder();

    if (user != null && user.getCheckingAcc() != null) {
      msg.append("Transaction History for Account: \n\n")
          .append("Account Number: ").append(user.getCheckingAcc().getAccNumber()).append("\n")
          .append("Balance: ").append(user.getCheckingAcc().getBalance()).append("\n\n");

      List<Transaction> transactions = user.getCheckingAcc().getTransactionHistory();

      if (transactions != null && !transactions.isEmpty()) {
        for (Transaction transaction : transactions) {
          msg.append(transaction.toString()).append("\n");
        }
      } else {
        msg.append("No transactions recorded yet.\n");
      }
    } else {
      msg.append("User or account information is missing.\n");
    }

    return msg.toString();
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
          if (bank.deleteUser(user)) {
            option = 4;
          }
          break;
        case 4:
          break;
      }

    } while (option != 4);
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
      option = JOptionPane.showOptionDialog(null, formatTransactionHistory(user),
          "Account: (" + user.getUserName() + ") Choose an option:", 0, 0, null,
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
          String savingsBalanceMessage = (user.getSavingsAcc() != null) 
              ? "Savings account balance: " + user.getSavingsAcc().getBalance() 
              : "No savings account.";
      
          String msg = "Checking account balance: " + user.getCheckingAcc().getBalance()
              + "\n" + savingsBalanceMessage;
          JOptionPane.showMessageDialog(null, msg);
          break;
      
        case 4:
          break;
      }
    } while (option != 4);
  }

  // Menu 6
  public static void transferOptions(Bank bank, User user) {
    int option;
    User selectedUser = null;
    double transferAmount = 0;

    do {
      String msg = "Selected User: " + (selectedUser != null ? selectedUser.getUserName() : "None")
          + "\nAmount: " + (transferAmount > 0 ? transferAmount : "None")
          + "\n\nAvailable Funds: " + user.getCheckingAcc().getBalance();
      option = JOptionPane.showOptionDialog(null, msg,
          "Account: (" + user.getUserName() + ") Choose an option:", 0, 0, null,
          transferOptions.values(), transferOptions.values()[0]);

      switch (option) {
        case 0:
          selectedUser = Transaction.selectTransferUser(user);
          break;

        case 1:
          transferAmount = Transaction.setTransferAmount(user);
          break;

        case 2:
          Bank.transfer(user, selectedUser, transferAmount);
          option = 3;
          break;

        case 3:
          break;
      }
    } while (option != 3);
  }

}
