import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import Bank.Bank;

public class Main {
  public static void main(String[] args) {
    ImageIcon icon = new ImageIcon(Main.class.getResource("/Assets/banco.jpg"));
    Bank bank = new Bank("Bank");
    String[] menu = {"Sign in","Close"};
    int option = 0;
		do {
			option = JOptionPane.showOptionDialog(null, "", "Bank (banco)",  JOptionPane.DEFAULT_OPTION, 
      JOptionPane.INFORMATION_MESSAGE, icon, menu, menu[0]);
			switch (option) {
			case 0:
				Bank.bankOptions(bank); 
				break;
			case 1:
				break;
			
			}
		} while (option!=1);
  }

}