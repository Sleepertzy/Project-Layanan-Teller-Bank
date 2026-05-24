package controller;

import dao.LoginDAO;

public class LoginController {

    LoginDAO dao;

    public LoginController() {

        dao = new LoginDAO();
    }

    public boolean login(String username,
                         String password) {

        if (username.isEmpty()
                || password.isEmpty()) {

            return false;
        }

        return dao.login(username, password);
    }
}