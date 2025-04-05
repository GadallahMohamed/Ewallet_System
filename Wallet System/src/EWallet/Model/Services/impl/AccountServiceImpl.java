package EWallet.Model.Services.impl;

import EWallet.Model.Account;
import EWallet.Model.Services.AccountService;
import EWallet.Model.WalletSystem;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    WalletSystem walletSystem = new WalletSystem();
    @Override
    public boolean createAccount(Account account) {
        List<Account> accounts = walletSystem.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(account.getUserName())){
                return false;
            }

        }
        walletSystem.getAccounts().add(account);
        return true;
    }

    @Override
    public boolean findAccount(Account account) {
        List<Account> accounts = walletSystem.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(account.getUserName())
            && accounts.get(i).getPassword().equals(account.getPassword())){
                return true;
            }

        }

        return false;
    }

    public boolean deposit(Account account, double money){
        //TODO check if account exit on ewallat if not exit return false
        //TODO add money to account if success return true
        if (account == null) {
            return false;
        }
        if (money > 0){
            account.setBalance(account.getBalance() + money);
            return true;
        }
        return false;

    }

    public boolean withdraw(Account account, double money){
        //TODO check if account exit on ewallat if not exit return false
        //TODO check if money is greater than balance of account return false
        //TODO cut money from account if success return true
        if (account == null ){
            return false;
        }
        if (account.getBalance() < money){
            return false;
        }
        account.setBalance(account.getBalance() - money);
        return true;

    }

    @Override
    public boolean transfer(Account account, String transferUsername, double money) {
        //TODO check if account exists on wallet if not return false
        //TODO check if transferAccount exists on wallet if not return false
        //TODO check if money is greater than balance of account return false
        //TODO cut money from account and add to transferAccount if success return true

        if (account == null) {
            System.out.println("Account not exit!");
            return false;
        }

        Account transferAccount = findAccountByUsername(transferUsername);
        if (transferAccount == null) {
            System.out.println("Receiver account not exit!");
            return false;
        }

        if (account.getBalance() < money) {
            System.out.println("Invalid Transfer! Enter amount of money again");
            return false;
        }

        account.setBalance(account.getBalance() - money);
        transferAccount.setBalance(transferAccount.getBalance() + money);
        return true;
    }

    private Account findAccountByUsername(String username) {
        List<Account> accounts = walletSystem.getAccounts();
        for (Account acc : accounts) {
            if (acc.getUserName().equals(username)) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public Account showProfileDetails(Account account) {
        //TODO check if account exit on ewallat
        //TODO if exit return account if not exit return null
        if (account == null){
            return null;
        }
        return account;

    }
    // todo private function check if account exit or not
}
