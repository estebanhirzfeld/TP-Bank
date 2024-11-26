package Bank;

import java.time.LocalTime;
import javax.swing.JOptionPane;
import Options.BankOptions;
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

  //Create user
  public void addUser(){
      String userName = JOptionPane.showInputDialog("Enter user name:");
      int id = Integer.parseInt(JOptionPane.showInputDialog("Enter user ID:"));
      User.addUser(userName, id);
      JOptionPane.showMessageDialog(null, "User added successfully");
    }
  
  
    public void modUser(){
      JOptionPane.showMessageDialog(null, "Modify user!!!");
  
    }
  
    public void deleteUser(){
      JOptionPane.showMessageDialog(null, "Delete user!!!");
    }
  
    public void checkName(){
  
    }
  
  
    public void selectUser(){
      
    }
  
  
  public static void bankOptions(Bank bank){
    int option;
  
    do{
      option = JOptionPane.showOptionDialog(null,"ENUM BANCO \n Choose an option:", null, 0, 0, null,BankOptions.values(), BankOptions.values()[0]);
      switch(option) {
        case 0:
            bank.addUser();
          break;
      case 1:
          bank.modUser();
          break;
      case 2:
          bank.deleteUser();
          break;
      case 3:
          JOptionPane.showMessageDialog(null, "Returning...");
          break;
  }

  }while(option!=3);

}

//esto hizo el profe en una tarea lo dejo por si nos sirve jsjs
	// 	for (Materias materia: Materias.values()) {
	// 			if (materia.ordinal()==opcion) {
					
	// 				JOptionPane.showMessageDialog(null, 
	// 						"Nombre " + materia.getNombre() + " " + materia.getCorrelativa() );
					
	// 				opcion = JOptionPane.showOptionDialog(null, "Seleccione día", "", opcion, opcion, null, Dia.values(), Dia.values());
				
	// 				for (Dia dia: Dia.values()) {
	// 					if (dia.ordinal()==opcion) {
	// 						//Forma normal
	// 						Inscripcion nueva = new Inscripcion(materia.getNombre(),
	// 								LocalTime.of(11, 10),dia.name());
							
	// 						JOptionPane.showMessageDialog(null, nueva);
							
	// 						inscripciones.add(nueva);
							
	// 						/*Forma acortada -> Creo directo el objeto en la lista, sin necesidad de crear un objeto de apoyo
							
	// 						inscripciones.add(new Inscripcion(materia.getNombre(),
	// 								LocalTime.of(11, 10),dia.name());

	// 						 * */
	// 					} 
	// 				}
	// 			}


}
