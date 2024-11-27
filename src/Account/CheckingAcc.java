package Account;
import java.time.LocalDate;
import java.util.LinkedList;

import User.User;

public class CheckingAcc extends Account {

    public CheckingAcc(User user, String accType, int accNumber, double balance, LocalDate openingDate) {
        super(user, "Checking Account", accNumber, balance, openingDate);
    }

}
