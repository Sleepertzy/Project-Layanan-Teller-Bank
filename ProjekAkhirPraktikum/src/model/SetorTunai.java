package model;

public class SetorTunai extends Transaksi {

    @Override
    public void prosesTransaksi() {

        System.out.println(
                "Proses Setor Tunai"
        );
    }
}