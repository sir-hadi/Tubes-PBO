
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
            view.switcPanel(view.getPPilihanDonatur(), view.getPBelumDonasi());
        } 
        else if(source == view.getBtnCancelRegis()) {
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
        }
        else if(source == view.getBtnDeleteEvent()) {
            //sistem kerja
        }
        else if(source == view.getBtnEditProfile()) {
            view.switcPanel(view.getPUserMain(), view.getPEditUser());
        }
        else if(source == view.getBtnCancelEditProf()){
            //if donatur           
            //view.switcPanel(view.getPUserMain(), view.getPMainDonatur());
            
            //if PG            
            view.switcPanel(view.getPUserMain(), view.getPMainGalangDana());
            view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
        }
        else if(source == view.getBtnDoneEditProf()){
            //sistem kerja update
            
            //if donatur           
            //view.switcPanel(view.getPUserMain(), view.getPMainDonatur());
            
            //if PG
            view.switcPanel(view.getPUserMain(), view.getPMainGalangDana());
            view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
        }
        else if(source == view.getBtnKirimDonasi()) {
            view.switcPanel(view.getPPilihanDonatur(), view.getPIdlePilihanDonasi());
        }
        else if(source == view.getBtnLogOutAdmin()) {
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
        }
        else if(source == view.getBtnLogin()) {
            UserInfo ui;
            ui = model.getDonatur(view.getTfUsername());
            if (ui==null) { //Donatur Not Found
                ui = model.getPengalangDana(view.getTfUsername());
                System.out.println("b");
            } else {
                System.out.println("a");
            }
            if (ui!=null) { //Any Found
                if(ui.getPassword().equals(String.valueOf(view.getPfPassword().getPassword()))) { //Check Pass
                    currentUser = ui;
                    view.getjLayeredMain().removeAll();
                    
                    //if admin
                    //view.getjLayeredMain().add(view.getPAdmin());
            
                    if(currentUser instanceof Donatur) {
                        view.getjLayeredMain().add(view.getPMain());
                        view.switcPanel(view.getPUserMain(), view.getPMainDonatur());
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
                    System.out.println("Nope");
                    JOptionPane.showMessageDialog(view.getPRegistrasi(), "Username or Password are invalid");
                }
            } else {
                System.out.println("c");
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Username or Password are invalid");
            }
            
        }
        else if(source == view.getBtnLogout()) {
            currentUser = null;
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
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
        }
        else if(source == view.getBtnUpdateDonasi()) {
            //sistem kerja
        }
        else if(source == view.getBtnKembali()){
            view.switcPanel(view.getPPilihanDonatur(), view.getPBelumDonasi());
        }
        else if(source == view.getBtnUpdateEvent()) {
            view.switcPanel(view.getPMainGalangDana(), view.getPEventUpdate());
        }
        else if(source == view.getBtnAddEvent()){
            view.switcPanel(view.getPMainGalangDana(), view.getPEventCreate());
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
                    ((PengalangDana) currentUser).createEvent(alamat, tanggalMulai, tanggalSelesai, targetDana, deskripsi, namaPenerima, telpPenerima, namaPenerima, penerima);
                    //balik
                    view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
                    view.setListEventPD(model.getListEventId((PengalangDana) currentUser));
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(view.getPRegistrasi(), "Wrong Date Format");
            }
        }
        else if(source == view.getBtnCancelUpdateEvent()){
            view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
        }
        else if(source == view.getBtnDoneUpdateEvent()){
            //proses update event
            
            //balik
            view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
        }
        else if(source == view.getBtnBatalDonasi()){
            view.switcPanel(view.getPPilihanDonatur(), view.getPBelumDonasi());
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        Object source = me.getSource();
        if(source == view.getListDntInAdmin()) {
            
        }
        else if(source == view.getListEventAdmin()) {
            
        }
        else if(source == view.getListEventDNT()) {
            
        }
        else if(source == view.getListEventPD()) {
            String id = view.getSelectedEventPD();
            view.setTaDeskripsiPDText(model.searchEvent((PengalangDana) currentUser,id));
        }
        else if(source == view.getListPdInAdmin()) {
            
        }
        else if(source == view.getListPenerimaAdmin()) {
            
        }
    }
}