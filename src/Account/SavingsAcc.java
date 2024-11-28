package Account;

import User.User;

public class SavingsAcc extends Account {

    public SavingsAcc(User user, String accType, int accNumber, double balance) {
        super(user, "Savings Account", accNumber, balance);
    }

}
