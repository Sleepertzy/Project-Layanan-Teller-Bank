package view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    private JButton btnNasabah;

    private JButton btnTeller;

    private JButton btnAntrian;

    private JButton btnTransaksi;

    private JButton btnLogout;

    public DashboardView() {

        setTitle("Dashboard Admin");

        setSize(500, 350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponent();
    }

    private void initComponent() {

        JPanel panel =
                new JPanel(
                        new GridLayout(5, 1, 10, 10)
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        btnNasabah =
                new JButton("Data Nasabah");

        btnTeller =
                new JButton("Data Teller");

        btnAntrian =
                new JButton("Data Antrian");

        btnTransaksi =
                new JButton("Data Transaksi");

        btnLogout =
                new JButton("Logout");

        panel.add(btnNasabah);

        panel.add(btnTeller);

        panel.add(btnAntrian);

        panel.add(btnTransaksi);

        panel.add(btnLogout);

        add(panel);

        btnNasabah.addActionListener(
                e -> new NasabahView()
                        .setVisible(true)
        );

        btnTeller.addActionListener(
                e -> new TellerView()
                        .setVisible(true)
        );

        btnAntrian.addActionListener(
                e -> new AntrianView()
                        .setVisible(true)
        );

        btnTransaksi.addActionListener(
                e -> new TransaksiView()
                        .setVisible(true)
        );

        btnLogout.addActionListener(
                e -> logout());
    }

    private void logout() {

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Logout sekarang?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm ==
                JOptionPane.YES_OPTION) {

            new LoginView()
                    .setVisible(true);

            dispose();
        }
    }
}