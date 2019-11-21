
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
    private List<Donasi> listDonasi;
    private Date tglMulai, tglSelesai;
    private Double targetDana;
    private String description;
    private boolean verified = false;
    private static int id=1;
    private String sid;

    public EventGalangDana(Penerima penerima , Date tglMulai, Date tglSelesai, Double targetDana, String description) {
        this.penerima = penerima;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.targetDana = targetDana;
        this.description = description;
        listDonasi = new ArrayList<>();
        this.sid = "EG"+id++;
    }
    
    public void createDonasi(Donatur donatur, double nominal) {
        Donasi d = new Donasi(donatur, 0);
        listDonasi.add(d);
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
    
}
