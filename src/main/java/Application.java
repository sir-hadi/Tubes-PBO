
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
public class Application {
    private List<Donatur> daftarDonatur;
    private List<PengalangDana> daftarPengalangDana;

    public Application(List<Donatur> daftarDonatur, List<PengalangDana> daftarPengalangDana) {
        this.daftarDonatur = daftarDonatur;
        this.daftarPengalangDana = daftarPengalangDana;
    }
    
    public void validasi(){
        
        
    }
   
    
    public void inputDonatur (String usernama, String password, String nama, String  noTelp, String email, String sid) {
        Donatur d = new Donatur(usernama, password, nama, noTelp, email, sid);
        daftarDonatur.add(d);
    }
    
    public Donatur getDonatur(String id){
        int i = 0;
        while(i < daftarDonatur.size() && !daftarDonatur.get(i).getSid().equals(id)){
            i++;
        }
        if (i >= daftarDonatur.size()) {
            return null;
        }
        return daftarDonatur.get(i);
    }
    
    public void editDonatur(String id) {
        Donatur ed = getDonatur(id);
//        String something = GUI.getString;
//        eD.setName(something);
//        ...
    }
    
    public void deleteDonatur(String id) {
        int i = 0;
        while(i < daftarDonatur.size() && !daftarDonatur.get(i).getSid().equals(id)){
            i++;
        }
        if (i < daftarDonatur.size()) {
            daftarDonatur.remove(i);
        }
    }
}
