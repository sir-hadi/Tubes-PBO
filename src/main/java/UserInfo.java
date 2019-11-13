/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Hadi
 */
public class UserInfo {
    private String usernama;
    private String password;
    private int id=0;
    private String sid;

    public UserInfo(String usernama, String password, String sid) {
        this.usernama = usernama;
        this.password = password;
        this.sid = sid;
        this.sid += "-"+id;
        id++;
    }

    public String getUsernama() {
        return usernama;
    }

    public void setUsernama(String usernama) {
        this.usernama = usernama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    
    
    
    
}
