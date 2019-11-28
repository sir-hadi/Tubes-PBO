
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
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == view.getBtnBatalDonasi()) {
            
        } 
        else if(source == view.getBtnCancelRegis()) {
            
        }
        else if(source == view.getBtnDeleteEvent()) {
            
        }
        else if(source == view.getBtnEditProfile()) {
            
        }
        else if(source == view.getBtnKirimDonasi()) {
            
        }
        else if(source == view.getBtnLogOutAdmin()) {
            
        }
        else if(source == view.getBtnLogin()) {
            
        }
        else if(source == view.getBtnLogout()) {
            
        }
        else if(source == view.getBtnMauDonasi()) {
            
        }
        else if(source == view.getBtnNewRegister()) {
            
        }
        else if(source == view.getBtnRegister()) {
            
        }
        else if(source == view.getBtnUpdateDonasi()) {
            
        }
        else if(source == view.getBtnUpdateEvent()) {
            
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
