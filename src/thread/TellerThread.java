package thread;

import dao.AntrianDAO;
import dao.TransaksiDAO;
import model.Antrian;
import model.SetorTunai;
import model.Transaksi;
import dao.TellerDAO;

public class TellerThread extends Thread {

    private int idTeller;

    private AntrianDAO antrianDAO;
    
    private TellerDAO tellerDAO;

    private TransaksiDAO transaksiDAO;

    public TellerThread(int idTeller) {

        this.idTeller = idTeller;

        antrianDAO = new AntrianDAO();

        transaksiDAO = new TransaksiDAO();
        
        tellerDAO = new TellerDAO();
    }

@Override
public void run() {

    while (true) {

        try {

            if (!tellerDAO.isTellerAktif(
                    idTeller)) {

                System.out.println(
                        "Teller "
                        + idTeller
                        + " sedang nonaktif"
                );

                sleep(3000);

                continue;
            }

            Antrian antrian =
                    antrianDAO
                            .getAntrianBerikutnya(
                                    idTeller
                            );

            if (antrian != null) {

                System.out.println(
                        "Teller "
                        + idTeller
                        + " sedang melayani "
                        + antrian.getNomorAntrian()
                );

                antrianDAO.updateStatus(
                        antrian.getIdAntrian(),
                        "Diproses"
                );

                sleep(5000);

                Transaksi transaksi =
                        new SetorTunai();

                transaksi.setJenisTransaksi(
                        "Setor Tunai"
                );

                transaksi.setNominal(
                        50000
                );

                transaksi.setIdNasabah(
                        antrian.getIdNasabah()
                );

                transaksi.setIdTeller(
                        idTeller
                );

                transaksi.prosesTransaksi();

                transaksiDAO.insertTransaksi(
                        transaksi
                );

                antrianDAO.updateStatus(
                        antrian.getIdAntrian(),
                        "Selesai"
                );

                System.out.println(
                        "Teller "
                        + idTeller
                        + " selesai melayani "
                        + antrian.getNomorAntrian()
                );
            }

            sleep(2000);

        }   catch (Exception e) {

                System.out.println(
                    e.getMessage()
                );
            }
        }
    }
}