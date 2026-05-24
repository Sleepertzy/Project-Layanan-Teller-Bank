package model;

public class Transfer extends Transaksi {

    @Override
    public void prosesTransaksi() {

        System.out.println(
                "Proses Transfer"
        );
    }
}