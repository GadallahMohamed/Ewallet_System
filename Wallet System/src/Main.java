import EWallet.Model.Services.impl.ApplicationServicesImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ApplicationServicesImpl applicationServices = new ApplicationServicesImpl();
        applicationServices.start();
    }
}