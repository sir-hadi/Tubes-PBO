
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Hadi
 */
public class EventGalangDana {
    private Penerima penerima;
    private String nama;
    private List<Donasi> listDonasi;
    private Date tglMulai, tglSelesai;
    private Double targetDana;
    private String description;
    private boolean verified = true;//was true cuz for testing
    private static int id=1;
    private String sid;

    public EventGalangDana(String nama, Date tglMulai, Date tglSelesai, Double targetDana, String description) {
        this.nama = nama;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.targetDana = targetDana;
        this.description = description;
        listDonasi = new ArrayList<>();
        this.sid = "EG"+id++;
    }
    
    public void updateGalangDana(String nama, Date tglMulai, Date tglSelesai, Double targetDana, String description, String namaPenerima, String telpPenerima, String alamatPenerima) {
        this.nama = nama;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.targetDana = targetDana;
        this.description = description;
        this.penerima.setAlamat(alamatPenerima);
        this.penerima.setNama(namaPenerima);
        this.penerima.setNoTelp(telpPenerima);
    }
    
    public void createDonasi(Donatur donatur, double nominal) {
        Donasi d = new Donasi(donatur, nominal);
        listDonasi.add(d);
    }
    
    public void updateDonasi(Donatur donatur, double nominal){
        for(Donasi d : listDonasi){
            if(d.getDonatur().equals(donatur)){
                d.setNominal(nominal);
                break;
            }
        }
    }
    
    public void deleteDonasi(Donatur donatur){
        for(Donasi d : listDonasi){
            if(d.getDonatur().equals(donatur)){
                listDonasi.remove(d);
                break;
            }
        }
    }
    
    public double getNominalDonasiByDonatur(Donatur donatur){
        for(Donasi d : listDonasi){
            if(d.getDonatur().equals(donatur)){
                return d.getNominal();
            }
        }
        return 0;
    }
    
    public void createPenerimaPersonal(String nama, String alamat, String noTelp){
        penerima = new Personal(nama, alamat, noTelp);
    }
    
    public void createPenerimaLembaga(String nama, String alamat, String noTelp){
        penerima = new Lembaga(nama, alamat, noTelp);
    }
    
    public double countDonasi(){
        double d = 0;
        for (Donasi donasi : listDonasi) {
            d = d + donasi.getNominal();
        }
        return d;
    }

    public Penerima getPenerima() {
        return penerima;
    }

    public void setPenerima(Penerima penerima) {
        this.penerima = penerima;
    }

    public Date getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(Date tglMulai) {
        this.tglMulai = tglMulai;
    }

    public Date getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(Date tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public Double getTargetDana() {
        return targetDana;
    }

    public void setTargetDana(Double targetDana) {
        this.targetDana = targetDana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Donasi getDonasi(int x) {
        return listDonasi.get(x);
    }

    public String getSid() {
        return sid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
}
