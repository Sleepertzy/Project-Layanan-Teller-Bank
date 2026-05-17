package view;

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

    private JButton btnProses;

    private JTable tableTransaksi;

    private DefaultTableModel tableModel;

    private TransaksiDAO dao;

    public TransaksiView() {

        dao = new TransaksiDAO();

        setTitle("Data Transaksi");

        setSize(700, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponent();

        tampilData();
    }

    private void initComponent() {

        JPanel panelForm =
                new JPanel(
                        new GridLayout(4, 2, 10, 10)
                );

        panelForm.add(
                new JLabel("Jenis Transaksi")
        );

        cbJenis =
                new JComboBox<>();

        cbJenis.addItem("Setor Tunai");
        cbJenis.addItem("Tarik Tunai");
        cbJenis.addItem("Transfer");

        panelForm.add(cbJenis);

        panelForm.add(
                new JLabel("Nominal")
        );

        txtNominal =
                new JTextField();

        panelForm.add(txtNominal);

        panelForm.add(
                new JLabel("ID Nasabah")
        );

        txtIdNasabah =
                new JTextField();

        panelForm.add(txtIdNasabah);

        btnProses =
                new JButton("Proses");

        panelForm.add(new JLabel());

        panelForm.add(btnProses);

        String[] column = {
                "ID",
                "Jenis",
                "Nominal",
                "ID Nasabah",
                "Tanggal"
        };

        tableModel =
                new DefaultTableModel(column, 0);

        tableTransaksi =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(tableTransaksi);

        setLayout(new BorderLayout());

        add(panelForm,
                BorderLayout.NORTH);

        add(scrollPane,
                BorderLayout.CENTER);

        btnProses.addActionListener(
                e -> prosesTransaksi());
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

        transaksi.prosesTransaksi();

        dao.insertTransaksi(transaksi);

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