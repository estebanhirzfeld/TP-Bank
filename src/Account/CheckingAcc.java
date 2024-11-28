package Account;
import User.User;

public class CheckingAcc extends Account {

    public CheckingAcc(User user, String accType, int accNumber, double balance) {
        super(user, "Checking Account", accNumber, balance);
    }

}
