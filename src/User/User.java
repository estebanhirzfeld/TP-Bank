package User;

import java.util.LinkedList;
import Bank.Bank;

public class User {
  private Bank bank;
  private String userName;
  private int id;
  private static LinkedList<User> users = new  LinkedList<User>();
  
    public User(String userName, int id) {
      this.userName = userName;
      this.id = id;
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
  
    public LinkedList<User> getUsers() {
      return users;
    }
  
    public void setUsers(LinkedList<User> users) {
      User.users = users;
    }
  

  //Adds created user to the list
    public static void addUser(String userName, int id) {
      User newUser = new User(userName, id);  
      users.add(newUser);  
  }






  @Override
  public String toString() {
    return "User [bank=" + bank + ", userName=" + userName + ", id=" + id + ", users=" + users + "]";
  }


}
