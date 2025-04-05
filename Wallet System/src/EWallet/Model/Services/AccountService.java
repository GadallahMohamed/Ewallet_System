package EWallet.Model.Services;

import EWallet.Model.Account;

import java.util.List;

public interface AccountService {
    boolean createAccount(Account account);
    boolean findAccount(Account account);
    boolean deposit (Account account, double money);
    boolean withdraw (Account account, double money);
    boolean transfer (Account account, String transferUsername, double money);
    Account showProfileDetails (Account account);
}
