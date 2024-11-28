import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import Bank.Bank;
import User.User;

public class Main {
  public static void main(String[] args) {
    ImageIcon icon = new ImageIcon(Main.class.getResource("/Assets/banco.jpg"));
    Bank bank = new Bank("Bank");
	User user1 = new User("steve", 123);
	User user2 = new User("martu", 456);
	User user3 = new User("midudios", 999);
    String[] menu = {"Sign in","Close"};
    int option = 0;
		do {
			option = JOptionPane.showOptionDialog(null, "", "Bank (banco)",  JOptionPane.DEFAULT_OPTION, 
      JOptionPane.INFORMATION_MESSAGE, icon, menu, menu[0]);
			switch (menu[option]) {
			case "Sign in":
				Bank.bankOptions(bank); 
				break;
			case "Close":
				break;
			
			}
		} while (option!=1);
  }

}