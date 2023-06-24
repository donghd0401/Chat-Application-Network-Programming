/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Group;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author duydo
 */
public class GroupDAO {
    private static Connection conn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    public static boolean insertGroup(Group group){
        String sql = "insert into chatdb.group(leaderID, name) values("+group.getLeaderID()+", '"+group.getName()+"');";
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean editGroupName(int id, String name){
        String sql = "update chatdb.group set name = '"+name+"' where id = "+id;
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            return false;
        }
        return true;        
    }
    public static boolean editGroupLeader(int id, int leaderID){
        String sql = "update chatdb.group set leaderID = "+leaderID+" where id = "+id;
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            return false;
        }
        return true;        
    }
    public static boolean deleteGroup(int id){
        String sql = "delete from chatdb.group where id = "+id;
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            return false;
        }
        return true;        
    }
    public static ArrayList<Group> findAll(int userID){
        ArrayList<Group> list = new ArrayList<>();
        String sql = "SELECT * FROM chatdb.group where id in (select distinct groupID from message where userID = "+userID+")";
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(new Group(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public static ArrayList<User> findMember(int groupID){
        String sql = "select * from users where id in (SELECT distinct userID FROM chatdb.message where groupID = "+groupID+")";
        ArrayList<User> list = new ArrayList<>();
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7)));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean deleteMember(int userID, int groupID){
        String sql = "delete from message where userID = "+userID+" and groupID = "+groupID;
        System.out.println(sql);
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
