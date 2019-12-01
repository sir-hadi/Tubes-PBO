
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
public class Controller extends MouseAdapter implements ActionListener{
    
    GUI view;
    Application model;
    UserInfo currentUser;
    EventGalangDana currentSeletedEgdByAdmin;
    
    public Controller() {
        view = new GUI();
        //model = new Application(daftarDonatur, daftarPengalangDana)
        //along with Database
        model = new Application();
        view.addActionListener(this);
        view.addMouseAdaper(this);
        view.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == view.getBtnBatalDonasi()) {            
            int index = view.getSelectedIndexEventDNT();
            String sid = model.getEventValidByIndex(index).getSid();
            model.deleteDonatioOfEvent((Donatur) currentUser,sid);
            view.switcPanel(view.getPPilihanDonatur(), view.getPBelumDonasi());
            refreshTaDeskDNT();
        } 
        else if(source == view.getBtnCancelRegis()) {
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
            view.setTfUsername("");
            view.setPfPassword("");
        }
        else if(source == view.getBtnDeleteEvent()) {
            if(view.getSelectedEventPD()!=null) {
                int yes = JOptionPane.showOptionDialog(view.getPMainGalangDana(), "Are you sure you want to delete Event?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(yes==0) {
                    ((PengalangDana) currentUser).deleteEvent(view.getSelectedEventPD());
                    view.setListEventPD(model.getListEventId((PengalangDana) currentUser));
                    view.setTaDeskripsiPDText("");
                }
            } else {
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "No Event Selected");
            }
        }
        else if(source == view.getBtnEditProfile()) {
            view.switcPanel(view.getPUserMain(), view.getPEditUser());
            view.setTfNewName(currentUser.getNama());
            view.setTfNewEmail(currentUser.getEmail());
            view.setTfNewNoTelp(currentUser.getNoTelp());
        }
        else if(source == view.getBtnCancelEditProf()){
            if (currentUser instanceof PengalangDana) {
                view.switcPanel(view.getPUserMain(), view.getPMainGalangDana());
                view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
            }
            if (currentUser instanceof Donatur) {
                view.switcPanel(view.getPUserMain(), view.getPMainDonatur());
            }
        }
        else if(source == view.getBtnDoneEditProf()){
            String nama = view.getTfNewName();
            String email = view.getTfNewEmail();
            String noTelp = view.getTfNewNoTelp();
            
            boolean valid = true;
            
            if (!model.isEmailValid(email)) {
                valid = false;
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Email Is Not Valid");
            }
            if (!model.isNumeric(noTelp)) {
                valid = false;
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Number Not Valid");
            }
            
            if (valid) {
                currentUser.setNama(nama);
                currentUser.setEmail(email);
                currentUser.setNoTelp(noTelp);
                view.setLblCurrentUsername(currentUser.getNama());
                if (currentUser instanceof PengalangDana) {
                    view.switcPanel(view.getPUserMain(), view.getPMainGalangDana());
                    view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
                }
                if (currentUser instanceof Donatur) {
                    view.switcPanel(view.getPUserMain(), view.getPMainDonatur());
                }
            }
        }
        else if(source == view.getBtnKirimDonasi()) {
            String nominalString =  view.getTfNominalPertama();
            double nominal = Double.parseDouble(nominalString);
            
            int index = view.getSelectedIndexEventDNT();
            String sid = model.getEventValidByIndex(index).getSid();
            model.insertDonationOfEvent((Donatur) currentUser, nominal, sid);
            refreshTaDeskDNT();
            view.switcPanel(view.getPPilihanDonatur(), view.getPIdlePilihanDonasi());
        }
        else if(source == view.getBtnLogOutAdmin()) {
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
            view.setTfUsername("");
            view.setPfPassword("");
            currentUser = null;
        }
        else if(source == view.getBtnLogin()) {
            UserInfo ui;
            
            if(view.getTfUsername().equals("admin") && String.valueOf(view.getPfPassword().getPassword()).equals("admin") ){
                
                    view.setListDntInAdmin(model.ListDNTForAdmin());
                    view.setListEventAdmin(model.ListEventForAdmin());
                    view.setListPdInAdmin(model.ListPdForAdmin());
                    view.setListPenerimaInAdmin(model.ListPenerimaForAdmin());
                    view.switcPanel(view.getjLayeredMain(), view.getPAdmin());

            }
            
            
            ui = model.getDonatur(view.getTfUsername());
            if (ui==null) { //Donatur Not Found
                ui = model.getPengalangDana(view.getTfUsername());
            }
            if (ui!=null) { //Any Found
                if(ui.getPassword().equals(String.valueOf(view.getPfPassword().getPassword()))) { //Check Pass
                    currentUser = ui;
                    view.setLblCurrentUsername(currentUser.getNama());
                    view.getjLayeredMain().removeAll();
                    
                    
            
                    if(currentUser instanceof Donatur) {
                        view.getjLayeredMain().add(view.getPMain());
                        view.switcPanel(view.getPUserMain(), view.getPMainDonatur());
                        view.setListEventDNT((model.getListValidEventName()));
                    }
                    if(currentUser instanceof PengalangDana) {
                        view.getjLayeredMain().add(view.getPMain());     
                        view.switcPanel(view.getPUserMain(), view.getPMainGalangDana());
                        view.getPMainGalangDana().removeAll();
                        view.getPMainGalangDana().add(view.getPMainPenggalangDana());
                        view.getPMainGalangDana().repaint();
                        view.getPMainGalangDana().revalidate();
                        view.setListEventPD(model.getListEventId((PengalangDana) currentUser));
                    }
            
            
                    view.getjLayeredMain().repaint();
                    view.getjLayeredMain().revalidate();
                } else {
                    JOptionPane.showMessageDialog(view.getPRegistrasi(), "Username or Password are invalid");
                    view.setPfPassword("");
                }
            } else {
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Username or Password are invalid");
                view.setPfPassword("");
            }
            
        }
        else if(source == view.getBtnLogout()) {
            currentUser = null;
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
            view.setTfUsername("");
            view.setPfPassword("");
        }
        else if(source == view.getBtnMauDonasi()) {
            view.switcPanel(view.getPPilihanDonatur(), view.getPDonasiPertama());
        }
        else if(source == view.getBtnNewRegister()) {
            String username = view.getTfRegisUsername();            
            String password = String.valueOf(view.getPfRegisPassword().getPassword());
            String passConfirm = String.valueOf(view.getPfRegisPasswordConfirmation().getPassword());
            String nama = view.getTfRegisName();
            String noTelp = view.getTfRegisTelp();
            String email = view.getTfRegisEmail();
            
            boolean valid = true;                        
            if (password.equals("")) {
                valid = false;
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Enter Password");
            } 
            else if (passConfirm.equals("")) {
                valid = false;
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Confirm Password");
            }
            else if (!passConfirm.equals(password)) {
                valid = false;
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Password and password confirmation does not match");
            }
            if (!model.isEmailValid(email)) {
                valid = false;
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Email Is Not Valid");
            }
            if (!model.isNumeric(noTelp)) {
                valid = false;
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Number Not Valid");
            }

            if(view.getRbRegisDonatur().isSelected() && valid){
                //JOptionPane.showMessageDialog(view.getPRegistrasi(), "Weh Valid");
                model.inputDonatur(username, password, nama, noTelp, email, "D");
                view.switcPanel(view.getjLayeredMain(), view.getPLogin());
            }
            
            if(view.getRbRegisPengalangDana().isSelected() && valid){
                //JOptionPane.showMessageDialog(view.getPRegistrasi(), "Weh Valid");
                model.inputPengalangDana(username, password, nama, noTelp, email, "P");
                view.switcPanel(view.getjLayeredMain(), view.getPLogin());
            }
            
        }
        else if(source == view.getBtnRegister()) {
            view.switcPanel(view.getjLayeredMain(), view.getPRegistrasi());
            view.setTfRegisUsername("");
            view.setTfRegisName("");
            view.setTfRegisTelp("");
            view.setTfRegisEmail("");
            view.setPfRegisPassword("");
            view.setPfRegisPasswordPConfirmation("");
            view.getRbRegisDonatur().setSelected(true);
        }
        else if(source == view.getBtnUpdateDonasi()) {
            //sistem kerja
            String nominalString =  view.getTfIdleNominal();
            double nominal = Double.parseDouble(nominalString);
            
            int index = view.getSelectedIndexEventDNT();
            String sid = model.getEventValidByIndex(index).getSid();
            model.updateDonationOfEvent((Donatur) currentUser, nominal, sid);
            model.insertingValidOnes();
            refreshTaDeskDNT();
        }
        else if(source == view.getBtnKembali()){
            view.switcPanel(view.getPPilihanDonatur(), view.getPBelumDonasi());
        }
        else if(source == view.getBtnUpdateEvent()) {
            if(view.getSelectedEventPD()==null) {
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "No Event Selected");
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                view.switcPanel(view.getPMainGalangDana(), view.getPEventUpdate());
                EventGalangDana egd = ((PengalangDana) currentUser).getEvent(view.getSelectedEventPD());
                view.setTfNamaEventUpdate(egd.getNama());
                view.setTfTanggalMulaiUpdate(format.format(egd.getTglMulai()));
                view.setTfTanggalSelesaiUpdate(format.format(egd.getTglSelesai()));
                view.setTfTargetDanaUpdate(String.valueOf(egd.getTargetDana()));
                view.setTfDescriptionUpdate(egd.getDescription());
                view.setTfNamaPenerimaUpdate(egd.getPenerima().getNama());
                view.setTfAlamatPenerimaUpdate(egd.getPenerima().getAlamat());
                view.setTfTelpPenerimaUpdate(egd.getPenerima().getNoTelp());
                if(egd.getPenerima() instanceof Lembaga) {
                    view.getRbLembagaUpdate().setSelected(true);
                } else {
                    view.getRbPersonalUpdate().setSelected(true);
                }
            }
        }
        else if(source == view.getBtnAddEvent()){
            view.switcPanel(view.getPMainGalangDana(), view.getPEventCreate());
            view.setTfAlamatPenerimaCreate("");
            view.setTfNamaEventCreate("");
            view.setTfNamaPenerimaCreate("");
            view.setTfTanggalMulaiCreate("");
            view.setTfTanggalSelesaiCreate("");
            view.setTfTargetDanaCreate("");
            view.setTfDescriptionCreate("");
            view.getRbPersonal().setSelected(true);
        }
        else if(source == view.getBtnCancelCreateEvent()){
            view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
        }
        else if(source == view.getBtnDoneCreateEvent()){
            //proses create event
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                String namaEvent = view.getTfNamaEventCreate();
                Date tanggalMulai = format.parse(view.getTfTanggalMulaiCreate());
                Date tanggalSelesai = format.parse(view.getTfTanggalSelesaiCreate());
                Double targetDana = Double.valueOf(view.getTfTargetDanaCreate());
                String deskripsi = view.getTfDescriptionCreate();
                String namaPenerima = view.getTfNamaPenerimaCreate();
                String alamat = view.getTfAlamatPenerimaCreate();
                String telpPenerima = view.getTfTelpPenerimaCreate();
                boolean penerima = false;
                if(view.getRbLembaga().isSelected()) {
                    penerima = true;
                }
                
                boolean valid = true;
                if (!model.isNumeric(telpPenerima)) {
                    valid = false;
                    JOptionPane.showMessageDialog(view.getPRegistrasi(), "Number Not Valid");
                }
                if(valid) {
                    ((PengalangDana) currentUser).createEvent(namaEvent, tanggalMulai, tanggalSelesai, targetDana, deskripsi, namaPenerima, telpPenerima, namaPenerima, penerima);
                    //balik
                    view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
                    view.setListEventPD(model.getListEventId((PengalangDana) currentUser));
                    view.setTfUsername("");
                    view.setPfPassword("");
                    model.insertingValidOnes();// masukin ulang event event yang valid biar donatur bisa liat
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Wrong Date Format");
            }
        }
        else if(source == view.getBtnCancelUpdateEvent()){
            view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
        }
        else if(source == view.getBtnDoneUpdateEvent()){
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            try {
                String namaEvent = view.getTfNamaEventUpdate();
                Date tanggalMulai = format.parse(view.getTfTanggalMulaiUpdate());
                Date tanggalSelesai = format.parse(view.getTfTanggalSelesaiUpdate());
                Double targetDana = Double.valueOf(view.getTfTargetDanaUpdate());
                String deskripsi = view.getTfDescriptionUpdate();
                String namaPenerima = view.getTfNamaPenerimaUpdate();
                String alamat = view.getTfAlamatPenerimaUpdate();
                String telpPenerima = view.getTfTelpPenerimaUpdate();
                boolean penerima = false;
                if(view.getRbLembagaUpdate().isSelected()) {
                    penerima = true;
                }
                
                boolean valid = true;
                if (!model.isNumeric(telpPenerima)) {
                    valid = false;
                    JOptionPane.showMessageDialog(view.getPRegistrasi(), "Number Not Valid");
                }
                if(valid) {
                    
                    EventGalangDana egd = ((PengalangDana) currentUser).getEvent(view.getSelectedEventPD());
                    egd.updateGalangDana(namaEvent, tanggalMulai, tanggalSelesai, targetDana, deskripsi, namaPenerima, alamat, telpPenerima);
                    view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
                    view.setListEventPD(model.getListEventId((PengalangDana) currentUser));
                    String id = view.getSelectedEventPD();
                    view.setTaDeskripsiPDText(model.searchEvent((PengalangDana) currentUser,id));
                    model.insertingValidOnes();
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Wrong Date Format");
            }
            
            
        }
        else if(source == view.getBtnBatalDonasi()){
            view.switcPanel(view.getPPilihanDonatur(), view.getPBelumDonasi());
        }else if(source == view.getRbVarifTrue()){
            if (view.getRbVarifTrue().isSelected()) {
                if (currentSeletedEgdByAdmin != null) {
                    model.VarifiedEvent(model.searchEventById(view.getSelectedEventAdmin()), true);
                    refreshTaDeskEventOnAdmin();
                }
            }

        }else if(source == view.getRbVarifFalse()){
            if (view.getRbVarifFalse().isSelected()) {
                if (currentSeletedEgdByAdmin != null) {
                    model.VarifiedEvent(model.searchEventById(view.getSelectedEventAdmin()), false);
                    refreshTaDeskEventOnAdmin();
                }
            }
            
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if(source == view.getListDntInAdmin()) {
            String id = view.getSelectedDntInAdmin();
            Donatur currentDNT = model.searchDNTById(id);
            view.setTaDeskripsiDtnInAdminText(
            "Nama : "+currentDNT.getNama()+
            "\nUsername : "+currentDNT.getUsername()+
            "\nEmail : "+currentDNT.getEmail()+
            "\nNomer Telp"+currentDNT.getNoTelp()
            );
        }
        else if(source == view.getListEventAdmin()) {
            refreshTaDeskEventOnAdmin();
        }
        else if(source == view.getListEventDNT()) {
            refreshTaDeskDNT();
        }
        else if(source == view.getListEventPD()) {
            String id = view.getSelectedEventPD();
            view.setTaDeskripsiPDText(model.searchEvent((PengalangDana) currentUser,id));
        }
        else if(source == view.getListPdInAdmin()) {
            String id = view.getSelectedPdInAdmin();
            PengalangDana currentPD = model.searchPegalangDanaById(id);
            view.setTaDeskripsiPdInAdminText(
            "Nama : "+currentPD.getNama()+
            "\nUsername : "+currentPD.getUsername()+
            "\nEmail : "+currentPD.getEmail()+
            "\nNomor Telp"+currentPD.getNoTelp()
            );
        }
        else if(source == view.getListPenerimaAdmin()) {
            String id = view.getSelectedPenerimaInAdmin();
            String type;
            Penerima currenPenerima = model.searchPeberimaById(id);
            if (currenPenerima instanceof Lembaga) {
                type = "Lembaga";
            }else{
                type = "Personal";
            }
            view.setTaDeskripsiPenerimaInAdminText(
            "Nama Penerima : "+currenPenerima.getNama()+
            "Jenis Penerima : "+type+
            "Alamat : "+currenPenerima.getAlamat()+
            "Nomor Telp : "+currenPenerima.getNoTelp()
            );
        }
    }
    
    public void refreshTaDeskDNT(){
        int index = view.getSelectedIndexEventDNT();
        String msg = model.getEventValidByIndex(index).getDescription();
        if (model.getEventValidByIndex(index).getNominalDonasiByDonatur((Donatur) currentUser) == 0) {
            view.setTaDeskripsiDNTText(msg);
            view.switcPanel(view.getPPilihanDonatur(), view.getPBelumDonasi());// biar panel donasi ke refresh kalo dia belum donasi
        } else {
            msg += "\n====================================================="
                    + "\n Your dantion to this Event is " + model.getEventValidByIndex(index).getNominalDonasiByDonatur((Donatur) currentUser);
            view.setTaDeskripsiDNTText(msg);
            view.switcPanel(view.getPPilihanDonatur(), view.getPIdlePilihanDonasi());
        }
    }
    
    public void refreshTaDeskEventOnAdmin(){
        String id = view.getSelectedEventAdmin();
        EventGalangDana currentEGD = model.searchEventById(id);            
        if (currentEGD.isVerified()) {
            view.getRbVarifTrue().isSelected();
        } else {
            view.getRbVarifFalse().isSelected();
        }
        view.setTaDeskriEventAdminText(
        "Nama Event : "+currentEGD.getNama()+
        "\nDeskripsi : "+currentEGD.getDescription()+
        "\nTanggal : "+currentEGD.getTglMulai()+" - "+currentEGD.getTglSelesai()+
        "\nTarget : "+currentEGD.getTargetDana()+
        "\nPenerima ID : "+currentEGD.getPenerima().getSid()+
        "\nNama Penerima : "+currentEGD.getPenerima().getNama()+
        "\nIs Event Valid : "+currentEGD.isVerified()
        );
    }
}