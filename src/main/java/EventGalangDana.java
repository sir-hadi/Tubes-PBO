
import java.util.*;

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

    public EventGalangDana(Penerima penerima , Date tglMulai, Date tglSelesai, Double targetDana, String description) {
        this.penerima = penerima;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
        this.targetDana = targetDana;
        this.description = description;
        listDonasi = new ArrayList<>();
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
    
}
