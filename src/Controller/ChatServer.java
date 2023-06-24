/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.*;
import Model.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author duydo
 */
public class ChatServer {
    private static final int PORT = 9001;
    
    
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private Socket socket;
        private DataInputStream dis;
        private PrintWriter out;
        private BufferedReader in;
        private ObjectOutputStream oos;
        private ObjectInputStream ois;
        /**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                dis = new DataInputStream(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                
               
                while (true) {
                    String req = in.readLine();
                    if(req==null){
                        return;
                    }
                    switch (req) {
                        case "LOGIN":
                            {
                                out.println("RECEIVE");
                                User user = (User) ois.readObject();
                                if(UserDAO.checkLogin(user)){
                                    oos.writeObject(user);
                                }
                                else oos.writeObject(user);
                                break;
                            }
                        case "REGISTER":
                            {
                                out.println("RECEIVE");
                                User user = (User) ois.readObject();
                                if(UserDAO.Register(user)){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "EDITUSER":
                            {
                                out.println("RECEIVE");
                                User user = (User) ois.readObject();
                                if(UserDAO.editUser(user)){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "SEND":
                            {
                                out.println("RECEIVE");
                                Message chat = (Message) ois.readObject();
                                if(MessageDAO.insertChat(chat)){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "EDITMESSAGE":
                            {
                                out.println("RECEIVE");
                                int messID = Integer.parseInt(in.readLine());
                                out.println("RECEIVE");
                                String content = in.readLine();
                                if(MessageDAO.editMessage(messID, content)){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "DELETEMESSAGE":
                            {
                                out.println("RECEIVE");
                                int messID = Integer.parseInt(in.readLine());
                                if(MessageDAO.deleteMessage(messID)){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "LISTMESSAGE":
                            {
                                out.println("RECEIVE");
                                int groupID = Integer.parseInt(in.readLine());
                                ArrayList<Message> list = MessageDAO.findAll(groupID);
                                oos.writeObject(list);
                                break;
                            }
                        case "CREATE":
                            {
                                out.println("RECEIVE");
                                Group group = (Group) ois.readObject();
                                if(GroupDAO.insertGroup(group)){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "EDITGROUPNAME":
                            {
                                out.println("RECEIVE");
                                Group group = (Group) ois.readObject();
                                if(GroupDAO.editGroupName(group.getId(), group.getName())){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "EDITGROUPLEADER":
                            {
                                Group group = (Group) ois.readObject();
                                if(GroupDAO.editGroupLeader(group.getId(), group.getLeaderID())){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "DELETEGROUP":
                            {
                                out.println("RECEIVE");
                                int groupID = Integer.parseInt(in.readLine());
                                if(GroupDAO.deleteGroup(groupID)){
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }
                        case "LISTGROUP":
                            {
                                out.println("RECEIVE");
                                int userID = Integer.parseInt(in.readLine());
                                ArrayList<Group> list = GroupDAO.findAll(userID);
                                oos.writeObject(list);
                                break;
                            }
                        case "LISTMEMBER":
                            {
                                out.println("RECEIVE");
                                int groupID = Integer.parseInt(in.readLine());
                                ArrayList<User> list = GroupDAO.findMember(groupID);
                                oos.writeObject(list);
                                break;
                            }
                        case "DELETEMEMBER":
                            {
                                out.println("RECEIVE");
                                int userID = Integer.parseInt(in.readLine());
                                out.println("RECEIVE");
                                int groupID = Integer.parseInt(in.readLine());
                                System.out.println(groupID);
                                if(GroupDAO.deleteMember(userID, groupID)){
                                    System.out.println("true");
                                    out.println("TRUE");
                                }
                                else out.println("FALSE");
                                break;
                            }  
                        case "SENDFILE":
                            {
                                out.println("RECEIVE");
                                Message mess = (Message) ois.readObject();
                                    if(MessageDAO.sendFile(mess)){
                                        out.println("TRUE");
                                    }
                                    else out.println("FALSE");
                                break;
                            }
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
            } catch (ClassNotFoundException ex) {
            } 
        }
    }
}
