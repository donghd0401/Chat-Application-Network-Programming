/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author duydo
 */
public class UserDAO {
    private static Connection conn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    public static boolean checkLogin(User user){
        String sql = "select * from users where username = '" + user.getUsername()+"' and password = '"+user.getPassword()+"'";
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                user.setId(rs.getInt(1));
                user.setName(rs.getString(4));
                user.setBirthDay(rs.getDate(5));
                user.setEmail(rs.getString(6));
                user.setPhone(rs.getString(7));
            }
            else return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean Register(User user){
        String sql = "insert into users(username, password, name) values('"+user.getUsername()+"', '"+user.getPassword()+"', '"+user.getName()+"')";
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean editUser(User user){
        String sql = "update users set password = '"+user.getPassword()+"', name = '"+user.getName()+"', birthday = '"+user.getBirthDay()+"', email = '"+user.getEmail()+"', phone = '"+user.getPhone()+"' where id = "+user.getId();
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
