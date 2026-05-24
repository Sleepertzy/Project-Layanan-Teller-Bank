package dao;

import koneksi.Koneksi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    Connection conn;

    public LoginDAO() {

        conn = Koneksi.getConnection();
    }

    public boolean login(String username,
                         String password) {

        String sql =
                "SELECT * FROM admin "
                + "WHERE username=? "
                + "AND password=?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, username);

            stmt.setString(2, password);

            ResultSet rs =
                    stmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Login gagal: "
                            + e.getMessage());
        }

        return false;
    }
}