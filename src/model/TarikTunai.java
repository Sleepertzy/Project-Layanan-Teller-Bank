package model;

public class TarikTunai extends Transaksi {

    @Override
    public void prosesTransaksi() {

        System.out.println(
                "Proses Tarik Tunai"
        );
    }
}