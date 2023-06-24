/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author duydo
 */
public class User implements Serializable{
    private int id;
    private String username;
    private String password;
    private String name;
    private Date birthDay;
    private String email;
    private String phone;
    private static final long serialVersionUID = 1L;
    public User() {
    }

    public User(String username, String password, String name, Date birthDay, String email, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDay = birthDay;
        this.email = email;
        this.phone = phone;
    }

    public User(int id, String username, String password, String name, Date birthDay, String email, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthDay = birthDay;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirthDay() {
        return new SimpleDateFormat("yyyy-MM-dd").format(birthDay);
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
