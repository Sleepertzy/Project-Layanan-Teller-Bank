package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private JTextField txtUsername;

    private JPasswordField txtPassword;

    private JButton btnLogin;

    private LoginController controller;

    public LoginView() {

        controller =
                new LoginController();

        setTitle("Login Admin");

        setSize(350, 220);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponent();
    }

    private void initComponent() {

        JPanel panel =
                new JPanel(
                        new GridLayout(3, 2, 10, 10)
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        panel.add(new JLabel("Username"));

        txtUsername =
                new JTextField();

        panel.add(txtUsername);

        panel.add(new JLabel("Password"));

        txtPassword =
                new JPasswordField();

        panel.add(txtPassword);

        btnLogin =
                new JButton("Login");

        panel.add(new JLabel());

        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(
                e -> prosesLogin());
    }

    private void prosesLogin() {

        String username =
                txtUsername.getText();

        String password =
                String.valueOf(
                        txtPassword.getPassword()
                );

        boolean berhasil =
                controller.login(
                        username,
                        password
                );

        if (berhasil) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login berhasil"
            );

            new DashboardView()
                    .setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Username / Password salah"
            );
        }
    }
}