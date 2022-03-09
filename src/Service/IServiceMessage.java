/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entités.Message;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Hassen Chouadah
 */
public interface IServiceMessage {

    public void addMessage(Message m) throws SQLException;

    public void removeMessage(Message m) throws SQLException;

    public List<Message> getMessages(int sender, int receiver) throws SQLException;
}
