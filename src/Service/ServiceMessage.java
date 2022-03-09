/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entités.Message;
import Tools.MyConnexion;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Hassen Chouadah
 */
public class ServiceMessage implements IServiceMessage {

    Connection cnx;

    public ServiceMessage() {
        cnx = MyConnexion.getInstance().getCnx();
    }

    @Override
    public void addMessage(Message m) throws SQLException {
        Statement stm = cnx.createStatement();

        String query = "INSERT INTO `messages` (`sender`, `receiver`, `type`, `message`)"
                + "     VALUES ('" + m.getSenderId() + "', '" + m.getReceiverId() + "', '" + m.getType() + "', '" + m.getMessage() + "')";
        System.out.println(query);
        stm.executeUpdate(query);
        System.out.println("message ajouté");
    }

    @Override
    public void removeMessage(Message m) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "DELETE FROM `messages` where id= '" + m.getId() + "'";
        stm.executeUpdate(query);
    }

    @Override
    public List<Message> getMessages(int sender, int receiver) throws SQLException {
        Statement stm = cnx.createStatement();
        String query ="SELECT * FROM `messages` WHERE (sender='"+sender+"' AND receiver='"+receiver+"') OR (sender='"+receiver+"' AND receiver='"+sender+"') ORDER BY created ASC";
        ResultSet rst = stm.executeQuery(query);
        List<Message> messages = new ArrayList<>();
        utilisateurService US = new utilisateurService();
        while (rst.next()) {
            Message m = new Message();

            m.setId(rst.getInt("id"));
            m.setSenderId(rst.getInt("sender"));
            m.setReceiverId(rst.getInt("receiver"));
            m.setType(rst.getString("type"));
            m.setMessage(rst.getString("message"));
            m.setSeen(rst.getInt("seen"));
            m.setCreated(rst.getTimestamp("created"));

            m.setSender(US.getUserById(rst.getInt("sender")));
            m.setReceiver(US.getUserById(rst.getInt("receiver")));
            messages.add(m);
        }
        return messages;
    }

}
