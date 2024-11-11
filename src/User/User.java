package User;

import java.util.LinkedList;

import Bank.Bank;

public class User {
  private Bank bank;
  private String name;
  private int id;
  private LinkedList<User> users = new  LinkedList<User>();
}
