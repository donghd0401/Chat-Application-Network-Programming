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
public class Message implements Serializable{
    private int id;
    private int userID;
    private int groupID;
    private String content;
    private String name;
    private String fileName;
    private byte[] fileContent;
    private static final long serialVersionUID = 2L;
    public Message() {
    }

    public Message(int id, int userID, int groupID, String content, String fileName, byte[] fileContent, String name) {
        this.id = id;
        this.userID = userID;
        this.groupID = groupID;
        this.content = content;
        this.name=name;
        this.fileName=fileName;
        this.fileContent=fileContent;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getContent() {
        return content;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }



    
}
