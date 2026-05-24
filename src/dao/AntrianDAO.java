package dao;

import koneksi.Koneksi;
import model.Antrian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AntrianDAO {

    Connection conn;

    public AntrianDAO() {

        conn = Koneksi.getConnection();
    }

    public void insertAntrian(
            Antrian antrian) {

        String sql =
                "INSERT INTO antrian "
                + "(nomor_antrian, id_nasabah, id_teller, status_antrian) "
                + "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    antrian.getNomorAntrian()
            );

            stmt.setInt(
                    2,
                    antrian.getIdNasabah()
            );

            stmt.setInt(
                    3,
                    antrian.getIdTeller()
            );

            stmt.setString(
                    4,
                    antrian.getStatusAntrian()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    public List<Antrian> getAllAntrian() {

        List<Antrian> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM antrian";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Antrian a =
                        new Antrian();

                a.setIdAntrian(
                        rs.getInt("id_antrian")
                );

                a.setNomorAntrian(
                        rs.getString("nomor_antrian")
                );

                a.setIdNasabah(
                        rs.getInt("id_nasabah")
                );
                
                a.setIdTeller(
                        rs.getInt("id_teller")
                );

                a.setStatusAntrian(
                        rs.getString("status_antrian")
                );

                list.add(a);
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return list;
    }

    public void updateStatus(
            int id,
            String status) {

        String sql =
                "UPDATE antrian SET "
                + "status_antrian=? "
                + "WHERE id_antrian=?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, status);

            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }

    public void deleteAntrian(
            int id) {

        String sql =
                "DELETE FROM antrian "
                + "WHERE id_antrian=?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
    
    public void selesaiAntrian(
        int idNasabah) {

            String sql =
                    "UPDATE antrian SET "
                    + "status_antrian='Selesai' "
                    + "WHERE id_nasabah=? "
                    + "AND status_antrian='Diproses'";

            try {

                PreparedStatement stmt =
                        conn.prepareStatement(sql);

                stmt.setInt(1, idNasabah);

                stmt.executeUpdate();

            } catch (SQLException e) {

                System.out.println(
                        e.getMessage()
                );
        }
    }
    
    public Antrian getAntrianBerikutnya(
        int idTeller) {

    String sql =
            "SELECT * FROM antrian "
            + "WHERE id_teller=? "
            + "AND status_antrian='Menunggu' "
            + "ORDER BY id_antrian ASC "
            + "LIMIT 1";

    try {

        PreparedStatement stmt =
                conn.prepareStatement(sql);

        stmt.setInt(1, idTeller);

        ResultSet rs =
                stmt.executeQuery();

        if (rs.next()) {

            Antrian a =
                    new Antrian();

            a.setIdAntrian(
                    rs.getInt("id_antrian")
            );

            a.setNomorAntrian(
                    rs.getString("nomor_antrian")
            );

            a.setIdNasabah(
                    rs.getInt("id_nasabah")
            );

            a.setIdTeller(
                    rs.getInt("id_teller")
            );

            a.setStatusAntrian(
                    rs.getString("status_antrian")
            );

            return a;
        }

    } catch (Exception e) {

        System.out.println(
                e.getMessage()
        );
    }

    return null;
    }
}