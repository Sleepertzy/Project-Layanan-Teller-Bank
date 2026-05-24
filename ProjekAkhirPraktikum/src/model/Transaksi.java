package model;

public abstract class Transaksi {

    protected int idTransaksi;

    protected String jenisTransaksi;

    protected double nominal;

    protected int idNasabah;
    
    private int idTeller;

    public Transaksi() {
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getJenisTransaksi() {
        return jenisTransaksi;
    }

    public void setJenisTransaksi(String jenisTransaksi) {
        this.jenisTransaksi = jenisTransaksi;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public int getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah = idNasabah;
    }
    
    public int getIdTeller() {
        return idTeller;
    }

    public void setIdTeller(int idTeller) {
        this.idTeller = idTeller;
    }

    public abstract void prosesTransaksi();
}