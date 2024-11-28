package User;

import java.util.LinkedList;

import Account.CheckingAcc;
import Account.SavingsAcc;
import Bank.Bank;

public class User {
  private Bank bank;
  private CheckingAcc checkingAcc;
  private SavingsAcc savingsAcc;
  private String userName;
  private int id;
  private static LinkedList<User> users = new  LinkedList<User>();
  
    public User(String userName, int id) {
      this.userName = userName;
      this.id = id;
    }

    public void setCheckingAcc(CheckingAcc checkingAcc) {
      this.checkingAcc = checkingAcc;
    }

    public void setSavingsAcc(SavingsAcc savingsAcc) {
      this.savingsAcc = savingsAcc;
    }

    public CheckingAcc getCheckingAcc() {
      return checkingAcc;
    }

    public SavingsAcc getSavingsAcc() {
      return savingsAcc;
    }

    public Bank getBank() {
      return bank;
    }
  
    public void setBank(Bank bank) {
      this.bank = bank;
    }
  
    public String getUserName() {
      return userName;
    }
  
    public void setUserName(String userName) {
      this.userName = userName;
    }
  
    public int getId() {
      return id;
    }
  
    public void setId(int id) {
      this.id = id;
    }
  
    public static LinkedList<User> getUsers() {
      return users;
    }
  
    public void setUsers(LinkedList<User> users) {
      User.users = users;
    }

    public static void addUser(String userName, int id) {
      User newUser = new User(userName, id);  
      users.add(newUser);  
  }

    public static void deleteUser(User user){
      users.remove(user);
    }

  @Override
  public String toString() {
    return "User [bank=" + bank + ", userName=" + userName + ", id=" + id + ", users=" + users + "]";
  }


}
