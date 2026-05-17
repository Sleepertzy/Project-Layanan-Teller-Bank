package view;

import dao.TellerDAO;
import model.Teller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TellerView extends JFrame {

    private JTextField txtNama;

    private JComboBox<String> cbStatus;

    private JButton btnTambah,
            btnEdit,
            btnHapus,
            btnReset;

    private JTable tableTeller;

    private DefaultTableModel tableModel;

    private TellerDAO dao;

    private int selectedId = -1;

    public TellerView() {

        dao = new TellerDAO();

        setTitle("Data Teller");

        setSize(700, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponent();

        tampilData();
    }

    private void initComponent() {

        JPanel panelForm =
                new JPanel(new GridLayout(3, 2, 10, 10));

        panelForm.add(new JLabel("Nama Teller"));

        txtNama = new JTextField();

        panelForm.add(txtNama);

        panelForm.add(new JLabel("Status Teller"));

        cbStatus = new JComboBox<>();

        cbStatus.addItem("AKTIF");
        cbStatus.addItem("NONAKTIF");

        panelForm.add(cbStatus);

        JPanel panelButton = new JPanel();

        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset");

        panelButton.add(btnTambah);
        panelButton.add(btnEdit);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);

        String[] column = {
                "ID",
                "Nama Teller",
                "Status"
        };

        tableModel =
                new DefaultTableModel(column, 0);

        tableTeller =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(tableTeller);

        setLayout(new BorderLayout());

        add(panelForm, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        add(panelButton, BorderLayout.SOUTH);

        btnTambah.addActionListener(
                e -> tambahTeller());

        btnEdit.addActionListener(
                e -> editTeller());

        btnHapus.addActionListener(
                e -> hapusTeller());

        btnReset.addActionListener(
                e -> resetForm());

        tableTeller.getSelectionModel()
                .addListSelectionListener(
                        e -> pilihData());
    }

    private void tampilData() {

        tableModel.setRowCount(0);

        List<Teller> list =
                dao.getAllTeller();

        for (Teller t : list) {

            Object[] row = {

                    t.getIdTeller(),
                    t.getNamaTeller(),
                    t.getStatusTeller()
            };

            tableModel.addRow(row);
        }
    }

    private void tambahTeller() {

        Teller teller = new Teller();

        teller.setNamaTeller(
                txtNama.getText());

        teller.setStatusTeller(
                cbStatus.getSelectedItem().toString());

        dao.insertTeller(teller);

        tampilData();

        resetForm();

        JOptionPane.showMessageDialog(
                this,
                "Data teller berhasil ditambahkan");
    }

    private void pilihData() {

        int row =
                tableTeller.getSelectedRow();

        if (row != -1) {

            selectedId =
                    Integer.parseInt(
                            tableModel.getValueAt(row, 0).toString());

            txtNama.setText(
                    tableModel.getValueAt(row, 1).toString());

            cbStatus.setSelectedItem(
                    tableModel.getValueAt(row, 2).toString());
        }
    }

    private void editTeller() {

        if (selectedId == -1) {
            return;
        }

        Teller teller = new Teller();

        teller.setIdTeller(selectedId);

        teller.setNamaTeller(
                txtNama.getText());

        teller.setStatusTeller(
                cbStatus.getSelectedItem().toString());

        dao.updateTeller(teller);

        tampilData();

        resetForm();

        JOptionPane.showMessageDialog(
                this,
                "Data teller berhasil diupdate");
    }

    private void hapusTeller() {

        if (selectedId == -1) {
            return;
        }

        dao.deleteTeller(selectedId);

        tampilData();

        resetForm();

        JOptionPane.showMessageDialog(
                this,
                "Data teller berhasil dihapus");
    }

    private void resetForm() {

        txtNama.setText("");

        cbStatus.setSelectedIndex(0);

        selectedId = -1;
    }
}