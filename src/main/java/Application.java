
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
    private List<EventGalangDana> daftarEventValid;

    public Application() {
        daftarDonatur = new ArrayList<>();
        daftarPengalangDana = new ArrayList<>();
        daftarEventValid = new ArrayList<>();
        
    }
    
    //source www.geeksforgeeks.org/check-email-address-valid-not-java/
    public static boolean isEmailValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    //source www.baeldung.com/java-check-string-number
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public void validasi(){
        
        
    }
   
    
    public void inputDonatur (String usernama, String password, String nama, String  noTelp, String email, String sid) {
        Donatur d = new Donatur(usernama, password, nama, noTelp, email, sid);
        daftarDonatur.add(d);
    }
    
    public Donatur getDonatur(String username){
        int i = 0;
        while(i < daftarDonatur.size() && !daftarDonatur.get(i).getUsername().equals(username)){
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
    
    public List<Donatur> getDaftarDonatur() {
        return daftarDonatur;
    }

    public List<PengalangDana> getDaftarPengalangDana() {
        return daftarPengalangDana;
    }
    
    public void inputPengalangDana(String usernama, String password, String nama, String  noTelp, String email, String sid) {
        PengalangDana pd = new PengalangDana(usernama, password, nama, noTelp, email, sid);
        daftarPengalangDana.add(pd);
    }
    
    public PengalangDana getPengalangDana(String username){
        int i = 0;
        while(i < daftarPengalangDana.size() && !daftarPengalangDana.get(i).getUsername().equals(username)){
            i++;
        }
        if (i >= daftarPengalangDana.size()) {
            return null;
        }
        return daftarPengalangDana.get(i);
    }
    
    public String searchEvent(PengalangDana pd, String id) {
        //Change into DB later
        int i = 0;
        List<EventGalangDana> l = pd.getListEvent();
        while(i < l.size() && !l.get(i).getSid().equals(id)) {
            i++;
        } 
        if (i >= l.size()) {
            return "";
        }
        return "ID = "+l.get(i).getSid()+"\n"
               +"Nama Event = "+l.get(i).getNama()+"\n"
               +"Target Dana = "+l.get(i).getTargetDana()+"\n";
    }
    
    public void insertDonationOfEvent(Donatur donatur ,double nominal, String sid){
        for(PengalangDana pg : daftarPengalangDana){
            for(EventGalangDana egd : pg.getListEvent()){
                if(egd.getSid().equals(sid)){
                    egd.createDonasi(donatur, nominal);
                    break;
                }
            }
        }
        insertingValidOnes();//biar data divalid ke di update
    }
    
    public void updateDonationOfEvent(Donatur donatur ,double nominal, String sid){
        for(PengalangDana pg : daftarPengalangDana){
            for(EventGalangDana egd : pg.getListEvent()){
                if(egd.getSid().equals(sid)){
                    egd.updateDonasi(donatur, nominal);
                    break;
                }
            }
        }
        insertingValidOnes();
    }
    
    public void deleteDonatioOfEvent(Donatur donatur,String sid){
        for(PengalangDana pg : daftarPengalangDana){
            for(EventGalangDana egd : pg.getListEvent()){
                if(egd.getSid().equals(sid)){
                    egd.deleteDonasi(donatur);
                    break;
                }
            }
        }
        insertingValidOnes();
    }
    
    
    public String[] getListEventId(PengalangDana pd) {
        List<EventGalangDana> l = pd.getListEvent();
        String[] s = new String[l.size()];
        for (int i = 0; i < l.size(); i++) {
            s[i] = l.get(i).getSid();
        }
        return s;
    }
    
    public String[] getListValidEventName() {
        String[] eventName = new String[daftarEventValid.size()];
        for (int i = 0; i < eventName.length; i++) {
            eventName[i] = daftarEventValid.get(i).getNama();
        }

        return eventName;
    }

    public List<EventGalangDana> getDaftarEventValid() {
        return daftarEventValid;
    }
    
    public EventGalangDana getEventValidByIndex(int i){
        return daftarEventValid.get(i);
    }
    
    public void insertingValidOnes(){
        daftarEventValid.clear();
        for(PengalangDana pg : daftarPengalangDana){
            for(EventGalangDana egd : pg.getListEvent()){
                int j = 0;
                if (egd.isVerified()){
                    daftarEventValid.add(j, egd);
                    j++;
                    System.out.println("done 1");
                }
                
            }
        }
    }
    
    public String[] ListEventForAdmin(){
        ArrayList<String> eventIdList = new ArrayList<String>();
        
        for(PengalangDana pg : daftarPengalangDana){
            for(EventGalangDana egd : pg.getListEvent()){
                eventIdList.add(egd.getSid());
            }
        }
        String[] eventId = new String[eventIdList.size()];
        for (int i = 0; i < eventIdList.size(); i++) {
            eventId[i] = eventIdList.get(i);
        }
        return eventId;
    }
    
    public String[] ListDNTForAdmin(){
        ArrayList<String> dntIdList = new ArrayList<String>();
        
        for(Donatur d : daftarDonatur){
            dntIdList.add(d.getSid());
        }
        String[] dntId = new String[dntIdList.size()];
        for (int i = 0; i < dntIdList.size(); i++) {
           dntId[i] = dntIdList.get(i);
        }
        return dntId;
    }
    
    public String[] ListPdForAdmin(){
        ArrayList<String> pdIdList = new ArrayList<String>();
        
        for(PengalangDana pd : daftarPengalangDana){
            pdIdList.add(pd.getSid());
        }
        String[] pdId = new String[pdIdList.size()];
        for (int i = 0; i < pdIdList.size(); i++) {
           pdId[i] = pdIdList.get(i);
        }
        return pdId;
    }
    
    public String[] ListPenerimaForAdmin(){
        ArrayList<String> penerimaIdList = new ArrayList<String>();
        
        for(PengalangDana pd : daftarPengalangDana){
            for(EventGalangDana egd : pd.getListEvent()){
                penerimaIdList.add(egd.getPenerima().getSid());
            }
        }
        String[] penerimaId = (String[]) penerimaIdList.toArray();
//        String[] penerimaId = new String[penerimaIdList.size()];
//        for (int i = 0; i < penerimaIdList.size(); i++) {
//           penerimaId[i] = penerimaIdList.get(i);
//        }
        return penerimaId;
    }
    
    public EventGalangDana searchEventById(String id){
        for(PengalangDana pg : daftarPengalangDana){
            for(EventGalangDana egd : pg.getListEvent()){
                if(egd.getSid().equals(id)){
                    return egd;
                }
            }
        }
        return null;
    }
    
    public Donatur searchDNTById(String id){
        for(Donatur d : daftarDonatur){
            if(d.getSid().equals(id)){
                return d;
            }
        }
        return null;
    }
    
    public PengalangDana searchPegalangDanaById(String id){
        for(PengalangDana pd : daftarPengalangDana){
            if (pd.getSid().equals(id)) {
                return pd;
            }
        }
        return null;
    }
    
    public Penerima searchPeberimaById(String id){
        for(PengalangDana pd : daftarPengalangDana){
            for(EventGalangDana egd : pd.getListEvent()){
                if(egd.getPenerima().getSid().equals(id)){
                    return egd.getPenerima();
                }
            }
        }
        return null;
    }
    

}
