package Account;
import java.time.LocalDate;
import java.util.LinkedList;

import User.User;

public class SavingsAcc extends Account {
    
    public SavingsAcc(User user, String accType, int accNumber, double balance, LocalDate openingDate){
        super(user, "Savings Account", accNumber, balance, openingDate);
    }

}
