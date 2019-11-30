
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
public class PengalangDana extends UserInfo{
    private List<EventGalangDana> listEvent; 

    public PengalangDana(String usernama, String password, String nama, String noTelp, String email, String sid) {
        super(usernama, password, nama, noTelp, email, sid);
        listEvent = new ArrayList<>();
    }
    
    public void createEvent(String nama, Date tglMulai, Date tglSelesai, Double targetDana, String description) {
        EventGalangDana e = new EventGalangDana(nama, tglMulai, tglSelesai, targetDana, description);
        listEvent.add(e);
    }
    
    public void createEvent(String nama, Date tglMulai, Date tglSelesai, Double targetDana, String description, String namaPenerima, String telpPenerima, String alamatPenerima, boolean i) {
        EventGalangDana e = new EventGalangDana(nama, tglMulai, tglSelesai, targetDana, description);
        if(i) {
            e.createPenerimaLembaga(namaPenerima, alamatPenerima, telpPenerima);
        } else {
            e.createPenerimaPersonal(namaPenerima, alamatPenerima, telpPenerima);
        }
        listEvent.add(e);
    }
    
    public List<EventGalangDana> getListEvent() {
        return listEvent;
    }
    
    public EventGalangDana getEvent (String id) {
        int i = 0;
        while(i < listEvent.size() && !listEvent.get(i).getSid().equals(id)){
            i++;
        }
        if (i >= listEvent.size()) {
            return null;
        }
        return listEvent.get(i);
    }
    
    
}
