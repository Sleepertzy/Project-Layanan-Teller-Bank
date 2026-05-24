package view;

import dao.NasabahDAO;
import model.Nasabah;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class NasabahView extends JFrame {

    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNoHp;
    private JTextField txtCari;

    private JComboBox<String> cbJenisKelamin;

    private JButton btnTambah;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnReset;
    private JButton btnRefresh;
    private JButton btnCari;

    private JTable tableNasabah;

    private DefaultTableModel tableModel;

    private NasabahDAO dao;

    private int selectedId = -1;

    public NasabahView() {

        dao = new NasabahDAO();

        setTitle("Data Nasabah");

        setSize(900, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponent();

        tampilData();
    }

    private void initComponent() {

        JPanel panelForm =
                new JPanel(
                        new GridLayout(5, 2, 10, 10)
                );

        panelForm.setBorder(
                BorderFactory.createTitledBorder(
                        "Form Data Nasabah"
                )
        );

        panelForm.add(new JLabel("Nama Nasabah"));

        txtNama = new JTextField();

        panelForm.add(txtNama);

        panelForm.add(new JLabel("Alamat"));

        txtAlamat = new JTextField();

        panelForm.add(txtAlamat);

        panelForm.add(new JLabel("No HP"));

        txtNoHp = new JTextField();

        panelForm.add(txtNoHp);

        panelForm.add(new JLabel("Jenis Kelamin"));

        cbJenisKelamin = new JComboBox<>();

        cbJenisKelamin.addItem("Laki-laki");
        cbJenisKelamin.addItem("Perempuan");

        panelForm.add(cbJenisKelamin);

        JPanel panelButton = new JPanel();

        btnTambah = new JButton("Tambah");

        btnEdit = new JButton("Edit");

        btnHapus = new JButton("Hapus");

        btnReset = new JButton("Reset");

        btnRefresh = new JButton("Refresh");

        panelButton.add(btnTambah);
        panelButton.add(btnEdit);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);
        panelButton.add(btnRefresh);

        JPanel panelSearch = new JPanel();

        txtCari = new JTextField(20);

        btnCari = new JButton("Cari");

        panelSearch.add(new JLabel("Cari Nama"));

        panelSearch.add(txtCari);

        panelSearch.add(btnCari);

        String[] column = {
                "ID",
                "Nama",
                "Alamat",
                "No HP",
                "Jenis Kelamin"
        };

        tableModel =
                new DefaultTableModel(column, 0);

        tableNasabah =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(tableNasabah);

        JPanel northPanel =
                new JPanel(
                        new BorderLayout()
                );

        northPanel.add(
                panelForm,
                BorderLayout.CENTER
        );

        northPanel.add(
                panelSearch,
                BorderLayout.SOUTH
        );

        setLayout(new BorderLayout());

        add(northPanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        add(panelButton, BorderLayout.SOUTH);

        btnTambah.addActionListener(
                e -> tambahNasabah()
        );

        btnEdit.addActionListener(
                e -> editNasabah()
        );

        btnHapus.addActionListener(
                e -> hapusNasabah()
        );

        btnReset.addActionListener(
                e -> resetForm()
        );

        btnRefresh.addActionListener(
                e -> tampilData()
        );

        btnCari.addActionListener(
                e -> cariNasabah()
        );

        tableNasabah.getSelectionModel()
                .addListSelectionListener(
                        e -> pilihData()
                );
    }

    private void tampilData() {

        tableModel.setRowCount(0);

        List<Nasabah> list =
                dao.getAllNasabah();

        for (Nasabah n : list) {

            Object[] row = {

                    n.getIdNasabah(),
                    n.getNamaNasabah(),
                    n.getAlamat(),
                    n.getNoHp(),
                    n.getJenisKelamin()
            };

            tableModel.addRow(row);
        }
    }

    private void tambahNasabah() {

        if (txtNama.getText().isEmpty()
                || txtAlamat.getText().isEmpty()
                || txtNoHp.getText().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Semua data harus diisi!"
            );

            return;
        }

        Nasabah nasabah =
                new Nasabah();

        nasabah.setNamaNasabah(
                txtNama.getText()
        );

        nasabah.setAlamat(
                txtAlamat.getText()
        );

        nasabah.setNoHp(
                txtNoHp.getText()
        );

        nasabah.setJenisKelamin(
                cbJenisKelamin
                        .getSelectedItem()
                        .toString()
        );

        dao.insertNasabah(nasabah);

        tampilData();

        resetForm();

        JOptionPane.showMessageDialog(
                this,
                "Data berhasil ditambahkan"
        );
    }

    private void editNasabah() {

        if (selectedId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data terlebih dahulu!"
            );

            return;
        }

        Nasabah nasabah =
                new Nasabah();

        nasabah.setIdNasabah(selectedId);

        nasabah.setNamaNasabah(
                txtNama.getText()
        );

        nasabah.setAlamat(
                txtAlamat.getText()
        );

        nasabah.setNoHp(
                txtNoHp.getText()
        );

        nasabah.setJenisKelamin(
                cbJenisKelamin
                        .getSelectedItem()
                        .toString()
        );

        dao.updateNasabah(nasabah);

        tampilData();

        resetForm();

        JOptionPane.showMessageDialog(
                this,
                "Data berhasil diupdate"
        );
    }

    private void hapusNasabah() {

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
                        "Yakin ingin menghapus data?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm ==
                JOptionPane.YES_OPTION) {

            dao.deleteNasabah(selectedId);

            tampilData();

            resetForm();

            JOptionPane.showMessageDialog(
                    this,
                    "Data berhasil dihapus"
            );
        }
    }

    private void pilihData() {

        int row =
                tableNasabah.getSelectedRow();

        if (row != -1) {

            selectedId =
                    Integer.parseInt(
                            tableModel.getValueAt(row, 0)
                                    .toString()
                    );

            txtNama.setText(
                    tableModel.getValueAt(row, 1)
                            .toString()
            );

            txtAlamat.setText(
                    tableModel.getValueAt(row, 2)
                            .toString()
            );

            txtNoHp.setText(
                    tableModel.getValueAt(row, 3)
                            .toString()
            );

            cbJenisKelamin.setSelectedItem(
                    tableModel.getValueAt(row, 4)
                            .toString()
            );
        }
    }

    private void cariNasabah() {

        tableModel.setRowCount(0);

        List<Nasabah> list =
                dao.cariNasabah(
                        txtCari.getText()
                );

        for (Nasabah n : list) {

            Object[] row = {

                    n.getIdNasabah(),
                    n.getNamaNasabah(),
                    n.getAlamat(),
                    n.getNoHp(),
                    n.getJenisKelamin()
            };

            tableModel.addRow(row);
        }
    }

    private void resetForm() {

        txtNama.setText("");

        txtAlamat.setText("");

        txtNoHp.setText("");

        txtCari.setText("");

        cbJenisKelamin.setSelectedIndex(0);

        selectedId = -1;

        tableNasabah.clearSelection();
    }
}