package view;

import dao.AntrianDAO;
import dao.TransaksiDAO;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TransaksiView extends JFrame {

    private JComboBox<String> cbJenis;

    private JTextField txtNominal;

    private JTextField txtIdNasabah;
    
    private JTextField txtIdTeller;

    private JTable tableTransaksi;

    private DefaultTableModel tableModel;

    private TransaksiDAO dao;

    public TransaksiView() {

        dao = new TransaksiDAO();

        setTitle("Riwayat Transaksi");

        setSize(700, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponent();

        tampilData();

        new Timer(
            2000,
            e -> tampilData()
        ).start();
    }

    private void initComponent() {

        String[] column = {

            "ID",
            "Jenis",
            "Nominal",
            "ID Nasabah",
            "ID Teller",
            "Tanggal"
        };

        tableModel =
            new DefaultTableModel(column, 0);

        tableTransaksi =
            new JTable(tableModel);

        JScrollPane scrollPane =
            new JScrollPane(tableTransaksi);

        setLayout(new BorderLayout());

        add(scrollPane,
            BorderLayout.CENTER);
    }

    private void prosesTransaksi() {

        String jenis =
                cbJenis.getSelectedItem().toString();

        Transaksi transaksi = null;

        if (jenis.equals("Setor Tunai")) {

            transaksi =
                    new SetorTunai();

        } else if (jenis.equals("Tarik Tunai")) {

            transaksi =
                    new TarikTunai();

        } else if (jenis.equals("Transfer")) {

            transaksi =
                    new Transfer();
        }

        transaksi.setJenisTransaksi(jenis);

        transaksi.setNominal(
                Double.parseDouble(
                        txtNominal.getText()
                )
        );

        transaksi.setIdNasabah(
                Integer.parseInt(
                        txtIdNasabah.getText()
                )
        );
        
        transaksi.setIdTeller(
                Integer.parseInt(
                        txtIdTeller.getText()
                )
        );

        transaksi.prosesTransaksi();

        dao.insertTransaksi(transaksi);
        
        AntrianDAO antrianDAO = new AntrianDAO();

        antrianDAO.selesaiAntrian(
                transaksi.getIdNasabah()
        );

        tampilData();

        JOptionPane.showMessageDialog(
                this,
                "Transaksi berhasil"
        );
    }

    private void tampilData() {

        tableModel.setRowCount(0);

        List<String[]> list =
                dao.getAllTransaksi();

        for (String[] row : list) {

            tableModel.addRow(row);
        }
    }
}