package model;

public class Nasabah {
    
    private int idNasabah;
    private String namaNasabah;
    private String alamat;
    private String noHp;
    private String jenisKelamin;

    public Nasabah() {
    }

    public Nasabah(int idNasabah, String namaNasabah, String alamat, String noHp, String jenisKelamin) {
        this.idNasabah = idNasabah;
        this.namaNasabah = namaNasabah;
        this.alamat = alamat;
        this.noHp = noHp;
        this.jenisKelamin = jenisKelamin;
    }

    public int getIdNasabah() {
        return idNasabah;
    }

    public void setIdNasabah(int idNasabah) {
        this.idNasabah = idNasabah;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}