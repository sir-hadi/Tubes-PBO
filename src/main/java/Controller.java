
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    
    public Controller() {
        view = new GUI();
        //model = new Application(daftarDonatur, daftarPengalangDana)
        //along with Database
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
            view.getjLayeredMain().removeAll();
            
            
            
            //if admin
            //view.getjLayeredMain().add(view.getPAdmin());
            
            //if donatur
            //view.getjLayeredMain().add(view.getPMain());
            //view.switcPanel(view.getPUserMain(), view.getPMainDonatur());
            
            //if PG
            view.getjLayeredMain().add(view.getPMain());            
            view.getPMainGalangDana().removeAll();
            view.getPMainGalangDana().add(view.getPMainPenggalangDana());
            view.getPMainGalangDana().repaint();
            view.getPMainGalangDana().revalidate();
            
            
            view.getjLayeredMain().repaint();
            view.getjLayeredMain().revalidate();
            
        }
        else if(source == view.getBtnLogout()) {
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
        }
        else if(source == view.getBtnMauDonasi()) {
            view.switcPanel(view.getPPilihanDonatur(), view.getPDonasiPertama());
        }
        else if(source == view.getBtnNewRegister()) {
            view.switcPanel(view.getjLayeredMain(), view.getPLogin());
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
            
            //balik
            view.switcPanel(view.getPMainGalangDana(), view.getPMainPenggalangDana());
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
            
        }
        else if(source == view.getListPdInAdmin()) {
            
        }
        else if(source == view.getListPenerimaAdmin()) {
            
        }
    }
}
