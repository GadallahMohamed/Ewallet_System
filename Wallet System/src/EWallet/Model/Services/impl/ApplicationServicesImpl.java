package EWallet.Model.Services.impl;

import EWallet.Model.Account;
import EWallet.Model.Services.AccountService;
import EWallet.Model.Services.ApplicationServices;
import EWallet.Model.WalletSystem;

import java.util.List;
import java.util.Scanner;

public class ApplicationServicesImpl implements ApplicationServices {
    private Scanner scanner = new Scanner(System.in);

    private AccountService accountService = new AccountServiceImpl();
    private DataValidationImpl dataValidation = new DataValidationImpl();

    @Override
    public void start() {
        System.out.println("Hi Sir I Hope You Are Good");
        int counter = 0;
        int choose = 0;
        while (true) {

            System.out.println("1. Login      2. Create Account     3. Exit");
            try {
                choose = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("pls enter number not chars");
                scanner = new Scanner(System.in);
                continue;
            }
            boolean exit = false;
            switch (choose) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    System.out.println("Have a nice day");
                    exit = true;
                    break;
                default:
                    counter++;
                    if (counter != 4){
                        System.out.println("pls try again");
                        break;
                    }
            }
            if (exit){
                break;
            }
            if (counter == 4){
                System.out.println("try another time");
                break;
            }
        }

    }

    private void login(){
        scanner.nextLine();
        Account account = extractAccount();
        if (account == null){
            return;
        }
        boolean accountExits = accountService.findAccount(account);
        if (accountExits){
            //TODO main page
            mainPage(account);
        }else {
            System.out.println("Invalid username or password");
        }


    }

    private void mainPage(Account account) {
        //TODO create switch case
        //TODO every case must match functions and apply feature for(multi loop, 4 times invalid must out)
        int choose = 0;
        int counter = 0;
        while (true) {
            System.out.println("1. Deposit      2. Withdraw      3. transfer     4. show profile details      5. exit");
            try {
                choose = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("pls enter number not chars");
                scanner = new Scanner(System.in);
                continue;
            }

            boolean exit = false;
            switch (choose) {
                case 1:
                    deposit(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    transfer(account);
                    break;
                case 4:
                    showProfileDetails(account);
                    break;
                case 5:
                    System.out.println("Have a nice day");
                    exit = true;
                    break;
                default:
                    counter++;
                    if (counter != 4){
                        System.out.println("pls try again");
                        break;
                    }
            }
            if (exit){
                break;
            }
            if (counter == 4){
                System.out.println("try another time");
                break;
            }
        }
    }

    private void showProfileDetails(Account account) {
        // todo call account service to showProfileDetails
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }

        Account foundAccount = accountService.showProfileDetails(account);

        if (foundAccount == null) {
            System.out.println("Account not found!");
        } else {

            System.out.println("Account Details for " + foundAccount.getUserName() + ":");
            System.out.println("Username: " + foundAccount.getUserName());
            System.out.println("Balance: " + foundAccount.getBalance());
            System.out.println("Account Status: " + (foundAccount.isActive() ? "Active" : "Inactive"));
        }
    }

    private void transfer(Account account) {

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        scanner.nextLine();
        System.out.println("Enter the username of the account you want to transfer money to: ");
        String transferUsername = scanner.nextLine();

        System.out.println("Enter the amount to transfer: ");
        double money = scanner.nextDouble();

        if (money <= 0) {
            System.out.println("Invalid transfer amount!");
            return;
        }

        boolean transferSuccess = accountService.transfer(account, transferUsername, money);

        if (transferSuccess) {
            System.out.println("Transfer successful! New balance: " + account.getBalance());
        } else {
            System.out.println("Transfer failed.");
        }
    }

    private void withdraw(Account account) {
        //TODO take money from user
        // todo call account service to withdraw
        if (account == null){
            System.out.println("Account not exit!");
            return;
        }
        System.out.println("Enter Amount of withdraw money");
        double money = scanner.nextDouble();

        if (money <= 0){
            System.out.println("Invalid Withdraw!");
            return;
        }

        boolean withdrawSuccess = accountService.withdraw(account, money);
        if (withdrawSuccess){
            System.out.println("Withdrawal successful! New Balance: " + account.getBalance());
        }else {
            System.out.println("Withdrawal failed!");
        }
    }

    private void deposit(Account account) {

        //TODO take money from user
        // todo call account service to deposit
        if (account == null){
            System.out.println("Account not exit!");
            return;
        }
        System.out.println("Enter Amount Of Deposit Money : ");
        double money = scanner.nextDouble();

        if (money <= 0){
            System.out.println("Invalid deposit amount!");
            return;
        }
        boolean depositSuccess = accountService.deposit(account, money);
        if (depositSuccess){
            System.out.println("Deposit successful! New Balance: " + account.getBalance());
        }else {
            System.out.println("Deposit Failed!");
        }


    }

    private void createAccount(){
        scanner.nextLine();
        Account account = extractAccount();
        if (account == null){
            return;
        }
        boolean accountCreated = accountService.createAccount(account);
        if (accountCreated ){
            System.out.println("Account created successfully");
        }else {
            System.out.println("username is already exit");
        }

    }
    private Account extractAccount() {
        System.out.println("pls enter your username");
        String userName = scanner.nextLine();
        if (!dataValidation.validateUserName(userName)){
            System.out.println("Invalid username pls username size >= 3 and first letter is upper");
            return null;
        }
        System.out.println("pls enter your password");
        String password = scanner.nextLine();
        if (!dataValidation.validatePassword(password)){
            System.out.println("Invalid password enter upper char and lower , number and special char");
            return null;
        }
        return new Account(userName, password);
    }


}

