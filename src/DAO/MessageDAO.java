/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author duydo
 */
public class MessageDAO {
    private static Connection conn = null;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;
    
    public static boolean insertChat(Message message){
        String sql;
        if(message.getGroupID()!=0)
            sql = "insert into message(userId, groupID, content) values("+message.getUserID()+", "+message.getGroupID()+", '"+message.getContent()+"')";
        else 
            sql = "insert into message(userId, groupID, content) values("+message.getUserID()+", (select max(id) from chatdb.group), 'Xin chao(Auto-send message)')";
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
    public static boolean editMessage(int id, String content){
        String sql = "update message set content = '"+content+"' where id = '"+id+"'";
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean deleteMessage(int id){
        String sql = "delete from message where id = "+id;
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
    public static ArrayList<Message> findAll(int groupID){
        ArrayList<Message> list = new ArrayList<>();
        String sql = "SELECT message.id, userID, groupID, content, filename, filecontent, users.name FROM chatdb.message join users on chatdb.users.id = message.userID where groupID = "+groupID+" order by message.id";
        try {
            conn = new DBConnect().getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getBytes(6), rs.getString(7)));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean sendFile(Message mess){
        String sql = "insert into message(userID, groupID, filename, filecontent) values(?, ?, ?, ?)";
        try {
            conn = new DBConnect().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, mess.getUserID());
            ps.setInt(2, mess.getGroupID());
            ps.setString(3, mess.getFileName());
            ps.setBytes(4, mess.getFileContent());
            System.out.println(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
