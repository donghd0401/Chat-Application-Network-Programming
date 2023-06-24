/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author duydo
 */
public class Group implements Serializable{
    private int id;
    private int leaderID;
    private String name;
    private static final long serialVersionUID = 3L;
    public Group() {
    }

    public Group(int id, int leaderID, String name) {
        this.id = id;
        this.leaderID = leaderID;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public int getLeaderID() {
        return leaderID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLeaderID(int leaderID) {
        this.leaderID = leaderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
