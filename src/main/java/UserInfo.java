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
    private String username;
    private String password;
    private String nama;
    private String noTelp;
    private String email;
    private static int id=1;
    private String sid;

    public UserInfo(String username, String password, String nama, String noTelp, String email ,String sid) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.noTelp = noTelp;
        this.email = email;
        this.sid = sid;
        this.sid += "-"+id++;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
