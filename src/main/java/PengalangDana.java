
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

    public PengalangDana(String usernama, String password, String email, String sid) {
        super(usernama, password, email, sid);
        listEvent = new ArrayList<>();
    }
    
    public void createEvent(Penerima penerima , Date tglMulai, Date tglSelesai, Double targetDana, String description) {
        EventGalangDana e = new EventGalangDana(penerima, tglMulai, tglSelesai, targetDana, description);
        listEvent.add(e);
    }
    
}
