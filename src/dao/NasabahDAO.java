package dao;

import koneksi.Koneksi;
import model.Nasabah;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NasabahDAO {

    Connection conn;

    public NasabahDAO() {

        conn = Koneksi.getConnection();
    }

    public void insertNasabah(
            Nasabah nasabah) {

        String sql =
                "INSERT INTO nasabah "
                + "(nama_nasabah, alamat, no_hp, jenis_kelamin) "
                + "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    nasabah.getNamaNasabah()
            );

            stmt.setString(
                    2,
                    nasabah.getAlamat()
            );

            stmt.setString(
                    3,
                    nasabah.getNoHp()
            );

            stmt.setString(
                    4,
                    nasabah.getJenisKelamin()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public List<Nasabah> getAllNasabah() {

        List<Nasabah> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM nasabah";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Nasabah n =
                        new Nasabah();

                n.setIdNasabah(
                        rs.getInt("id_nasabah")
                );

                n.setNamaNasabah(
                        rs.getString("nama_nasabah")
                );

                n.setAlamat(
                        rs.getString("alamat")
                );

                n.setNoHp(
                        rs.getString("no_hp")
                );

                n.setJenisKelamin(
                        rs.getString("jenis_kelamin")
                );

                list.add(n);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return list;
    }

    public void updateNasabah(
            Nasabah nasabah) {

        String sql =
                "UPDATE nasabah SET "
                + "nama_nasabah=?, "
                + "alamat=?, "
                + "no_hp=?, "
                + "jenis_kelamin=? "
                + "WHERE id_nasabah=?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    nasabah.getNamaNasabah()
            );

            stmt.setString(
                    2,
                    nasabah.getAlamat()
            );

            stmt.setString(
                    3,
                    nasabah.getNoHp()
            );

            stmt.setString(
                    4,
                    nasabah.getJenisKelamin()
            );

            stmt.setInt(
                    5,
                    nasabah.getIdNasabah()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public void deleteNasabah(
            int id) {

        String sql =
                "DELETE FROM nasabah "
                + "WHERE id_nasabah=?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
    }

    public List<Nasabah> cariNasabah(
            String keyword) {

        List<Nasabah> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM nasabah "
                + "WHERE nama_nasabah LIKE ?";

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

                Nasabah n =
                        new Nasabah();

                n.setIdNasabah(
                        rs.getInt("id_nasabah")
                );

                n.setNamaNasabah(
                        rs.getString("nama_nasabah")
                );

                n.setAlamat(
                        rs.getString("alamat")
                );

                n.setNoHp(
                        rs.getString("no_hp")
                );

                n.setJenisKelamin(
                        rs.getString("jenis_kelamin")
                );

                list.add(n);
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return list;
    }
}