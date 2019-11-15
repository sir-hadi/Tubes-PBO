/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Hadi
 */
class Donasi {
    private Donatur donatur;
    private double nominal;
    
    public void addDonatur(Donatur donatur){
        this.donatur = donatur;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }
    
    public Donatur getDonatur() {
        return donatur;
    }

    public double getNominal() {
        return nominal;
    }
   
}
