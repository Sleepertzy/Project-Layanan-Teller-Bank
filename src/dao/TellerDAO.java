package dao;

import koneksi.Koneksi;
import model.Teller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TellerDAO {

    Connection conn;

    public TellerDAO() {

        conn = Koneksi.getConnection();
    }

    public void insertTeller(
            Teller teller) {

        String sql =
                "INSERT INTO teller "
                + "(nama_teller, status_teller) "
                + "VALUES (?, ?)";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    teller.getNamaTeller()
            );

            stmt.setString(
                    2,
                    teller.getStatusTeller()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public List<Teller> getAllTeller() {

        List<Teller> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM teller";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Teller teller =
                        new Teller();

                teller.setIdTeller(
                        rs.getInt("id_teller")
                );

                teller.setNamaTeller(
                        rs.getString("nama_teller")
                );

                teller.setStatusTeller(
                        rs.getString("status_teller")
                );

                list.add(teller);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return list;
    }

    public void updateTeller(
            Teller teller) {

        String sql =
                "UPDATE teller SET "
                + "nama_teller=?, "
                + "status_teller=? "
                + "WHERE id_teller=?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    teller.getNamaTeller()
            );

            stmt.setString(
                    2,
                    teller.getStatusTeller()
            );

            stmt.setInt(
                    3,
                    teller.getIdTeller()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void deleteTeller(
            int id) {

        String sql =
                "DELETE FROM teller "
                + "WHERE id_teller=?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public List<Teller> cariTeller(
            String keyword) {

        List<Teller> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM teller "
                + "WHERE nama_teller LIKE ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    "%" + keyword + "%"
            );

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Teller teller =
                        new Teller();

                teller.setIdTeller(
                        rs.getInt("id_teller")
                );

                teller.setNamaTeller(
                        rs.getString("nama_teller")
                );

                teller.setStatusTeller(
                        rs.getString("status_teller")
                );

                list.add(teller);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return list;
    }
}