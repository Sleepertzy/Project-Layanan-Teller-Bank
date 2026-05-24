package main;

import thread.TellerThread;
import view.LoginView;

public class Main {

    public static void main(String[] args) {

        TellerThread teller1 =
                new TellerThread(1);

        TellerThread teller2 =
                new TellerThread(2);

        TellerThread teller3 =
                new TellerThread(3);

        teller1.start();
        teller2.start();
        teller3.start();

        new LoginView().setVisible(true);
    }
}