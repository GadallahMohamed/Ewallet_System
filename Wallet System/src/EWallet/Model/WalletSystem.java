package EWallet.Model;

import java.util.ArrayList;
import java.util.List;

public class WalletSystem {
    private final String walletName = "Eraa Soft Wallet";
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getWalletName() {
        return walletName;
    }
}
