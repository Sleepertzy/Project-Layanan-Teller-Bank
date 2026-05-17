package main;

import view.LoginView;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {

            new LoginView().setVisible(true);

        });
    }
}