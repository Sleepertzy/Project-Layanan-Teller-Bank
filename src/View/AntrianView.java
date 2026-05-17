package view;

import dao.AntrianDAO;
import model.Antrian;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AntrianView extends JFrame {

    private JTextField txtNomor;

    private JTextField txtIdNasabah;

    private JComboBox<String> cbStatus;

    private JButton btnTambah;

    private JButton btnUpdate;

    private JButton btnHapus;

    private JButton btnRefresh;

    private JTable tableAntrian;

    private DefaultTableModel tableModel;

    private AntrianDAO dao;

    private int selectedId = -1;

    public AntrianView() {

        dao = new AntrianDAO();

        setTitle("Data Antrian");

        setSize(850, 450);

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

        panelForm.setBorder(
                BorderFactory.createTitledBorder(
                        "Form Antrian"
                )
        );

        panelForm.add(
                new JLabel("Nomor Antrian")
        );

        txtNomor =
                new JTextField();

        panelForm.add(txtNomor);

        panelForm.add(
                new JLabel("ID Nasabah")
        );

        txtIdNasabah =
                new JTextField();

        panelForm.add(txtIdNasabah);

        panelForm.add(
                new JLabel("Status")
        );

        cbStatus =
                new JComboBox<>();

        cbStatus.addItem("MENUNGGU");
        cbStatus.addItem("DIPROSES");
        cbStatus.addItem("SELESAI");

        panelForm.add(cbStatus);

        JPanel panelButton =
                new JPanel();

        btnTambah =
                new JButton("Tambah");

        btnUpdate =
                new JButton("Update Status");

        btnHapus =
                new JButton("Hapus");

        btnRefresh =
                new JButton("Refresh");

        panelButton.add(btnTambah);
        panelButton.add(btnUpdate);
        panelButton.add(btnHapus);
        panelButton.add(btnRefresh);

        String[] column = {

                "ID",
                "Nomor Antrian",
                "ID Nasabah",
                "Status"
        };

        tableModel =
                new DefaultTableModel(column, 0);

        tableAntrian =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(tableAntrian);

        setLayout(new BorderLayout());

        add(panelForm,
                BorderLayout.NORTH);

        add(scrollPane,
                BorderLayout.CENTER);

        add(panelButton,
                BorderLayout.SOUTH);

        btnTambah.addActionListener(
                e -> tambahAntrian()
        );

        btnUpdate.addActionListener(
                e -> updateStatus()
        );

        btnHapus.addActionListener(
                e -> hapusAntrian()
        );

        btnRefresh.addActionListener(
                e -> tampilData()
        );

        tableAntrian.getSelectionModel()
                .addListSelectionListener(
                        e -> pilihData()
                );
    }

    private void tampilData() {

        tableModel.setRowCount(0);

        List<Antrian> list =
                dao.getAllAntrian();

        for (Antrian a : list) {

            Object[] row = {

                    a.getIdAntrian(),
                    a.getNomorAntrian(),
                    a.getIdNasabah(),
                    a.getStatusAntrian()
            };

            tableModel.addRow(row);
        }
    }

    private void tambahAntrian() {

        if (txtNomor.getText().isEmpty()
                || txtIdNasabah.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Data harus diisi!"
            );

            return;
        }

        Antrian antrian =
                new Antrian();

        antrian.setNomorAntrian(
                txtNomor.getText()
        );

        antrian.setIdNasabah(
                Integer.parseInt(
                        txtIdNasabah.getText()
                )
        );

        antrian.setStatusAntrian(
                cbStatus.getSelectedItem()
                        .toString()
        );

        dao.insertAntrian(antrian);

        tampilData();

        JOptionPane.showMessageDialog(
                this,
                "Antrian berhasil ditambahkan"
        );
    }

    private void updateStatus() {

        if (selectedId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu!"
            );

            return;
        }

        dao.updateStatus(
                selectedId,
                cbStatus.getSelectedItem()
                        .toString()
        );

        tampilData();

        JOptionPane.showMessageDialog(
                this,
                "Status berhasil diupdate"
        );
    }

    private void hapusAntrian() {

        if (selectedId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu!"
            );

            return;
        }

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Yakin hapus antrian?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm ==
                JOptionPane.YES_OPTION) {

            dao.deleteAntrian(selectedId);

            tampilData();

            JOptionPane.showMessageDialog(
                    this,
                    "Antrian berhasil dihapus"
            );
        }
    }

    private void pilihData() {

        int row =
                tableAntrian.getSelectedRow();

        if (row != -1) {

            selectedId =
                    Integer.parseInt(
                            tableModel.getValueAt(row, 0)
                                    .toString()
                    );

            txtNomor.setText(
                    tableModel.getValueAt(row, 1)
                            .toString()
            );

            txtIdNasabah.setText(
                    tableModel.getValueAt(row, 2)
                            .toString()
            );

            cbStatus.setSelectedItem(
                    tableModel.getValueAt(row, 3)
                            .toString()
            );
        }
    }
}