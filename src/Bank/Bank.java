package Bank;

import java.time.LocalTime;
import javax.swing.JOptionPane;
import Options.bankOptions;
import Options.loggedOptions;
import Options.userOptions;
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

  // Create user
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

  public void deleteUser() {
    JOptionPane.showMessageDialog(null, "Delete user!!!");
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

  public void addAccount() {
    JOptionPane.showMessageDialog(null, "hola soy funcion agregar account!!");
  }

  public void checkName() {

  }

  public void selectUser() {

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
          bank.addAccount();
          break;
        case 1:
          userOptions(bank, user);
          break;
        case 2:
          bank.deleteUser();
          break;
        case 3:
          break;
      }

    } while (option != 3);
  }

  // menu 3
  public static void userOptions(Bank bank, User user) {
    int option;
    do {
      String msg = "Name: " + user.getUserName() + "\n ID: " + user.getId() ;
      option = JOptionPane.showOptionDialog(null, msg + "\n Choose an option:", "Modify User (" + user.getUserName() + ")", 0, 0, null,
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

  // esto hizo el profe en una tarea lo dejo por si nos sirve jsjs
  // for (Materias materia: Materias.values()) {
  // if (materia.ordinal()==opcion) {

  // JOptionPane.showMessageDialog(null,
  // "Nombre " + materia.getNombre() + " " + materia.getCorrelativa() );

  // opcion = JOptionPane.showOptionDialog(null, "Seleccione día", "", opcion,
  // opcion, null, Dia.values(), Dia.values());

  // for (Dia dia: Dia.values()) {
  // if (dia.ordinal()==opcion) {
  // //Forma normal
  // Inscripcion nueva = new Inscripcion(materia.getNombre(),
  // LocalTime.of(11, 10),dia.name());

  // JOptionPane.showMessageDialog(null, nueva);

  // inscripciones.add(nueva);

  // /*Forma acortada -> Creo directo el objeto en la lista, sin necesidad de
  // crear un objeto de apoyo

  // inscripciones.add(new Inscripcion(materia.getNombre(),
  // LocalTime.of(11, 10),dia.name());

  // * */
  // }
  // }
  // }

}
