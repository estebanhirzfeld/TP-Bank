import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import Bank.Bank;
import User.User;

public class Main {
  public static void main(String[] args) {
    ImageIcon icon = new ImageIcon(Main.class.getResource("/Assets/bank_logo.png"));
    Bank bank = new Bank("Bank");
		User.addUser("steve", 123);
		User.addUser("martu", 456);
		User.addUser("midudios", 999);
		User.addUser("", 0);
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