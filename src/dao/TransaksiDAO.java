package dao;

import koneksi.Koneksi;
import model.Transaksi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {

    Connection conn;

    public TransaksiDAO() {

        conn = Koneksi.getConnection();
    }

    public void insertTransaksi(
            Transaksi transaksi) {

        String sql =
            "INSERT INTO transaksi "
            + "(jenis_transaksi, nominal, id_nasabah, id_teller, tanggal_transaksi) "
            + "VALUES (?, ?, ?, ?, NOW())";

        
        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1,
                    transaksi.getJenisTransaksi());

            stmt.setDouble(2,
                    transaksi.getNominal());

            stmt.setInt(3,
                    transaksi.getIdNasabah());
            
            stmt.setInt(
                    4,
                    transaksi.getIdTeller()
            );

            stmt.executeUpdate();

            System.out.println(
                    "Transaksi berhasil ditambahkan"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Gagal transaksi: "
                            + e.getMessage()
            );
        }
    }

    public List<String[]> getAllTransaksi() {

        List<String[]> list =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transaksi";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                String[] data = {

                        rs.getString("id_transaksi"),

                        rs.getString("jenis_transaksi"),

                        rs.getString("nominal"),

                        rs.getString("id_nasabah"),
                        
                        rs.getString("id_teller"),

                        rs.getString("tanggal_transaksi")
                };

                list.add(data);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Gagal tampil transaksi: "
                            + e.getMessage()
            );
        }

        return list;
    }
}