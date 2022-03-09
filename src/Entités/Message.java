/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.sql.Timestamp;

/**
 *
 * @author Hassen Chouadah
 */
public class Message {

    private int id;
    private int senderId;
    private int receiverId;
    private String type;
    private String message;
    private int seen;
    private Timestamp created;

    private utilisateurs sender;
    private utilisateurs receiver;

    public Message() {
    }

    public Message(int id, int senderId, int receiverId, String type, String message) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public utilisateurs getSender() {
        return sender;
    }

    public void setSender(utilisateurs sender) {
        this.sender = sender;
    }

    public utilisateurs getReceiver() {
        return receiver;
    }

    public void setReceiver(utilisateurs receiver) {
        this.receiver = receiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", type=" + type + ", message=" + message + ", seen=" + seen + ", created=" + created + '}';
    }

    
}
