package com.example.demo.dao.impl;

import com.example.demo.dao.ConnectionDB;
import com.example.demo.dao.MessageDB;
import com.example.demo.model.Message;
import com.example.demo.model.Subscriber;
import com.example.demo.service.SubscriberService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageDBImpl implements MessageDB {
    @Override
    public List<Message> getMessagesBySenderId(int senderId) {
        Connection connection = null;
        List<Message> messageList = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "SELECT m.id, m.msg_date, s.id, s.phone, m.recipient_id FROM messages m INNER JOIN subscribers s" +
                    " ON m.sender_id = s.id WHERE m.sender_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, senderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt(1));
                String msdDate = rs.getString(2);
                message.setMsgDate(LocalDateTime.parse(msdDate));
                Subscriber sender = new Subscriber();
                sender.setId(rs.getInt(3));
                sender.setPhone(rs.getString(4));
                message.setSender(sender);
                messageList.add(message);
                Subscriber recipient = SubscriberService.INSTANCE.findSubscriberById(rs.getInt(5));
                message.setRecipient(recipient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return messageList;
    }

    @Override
    public boolean saveMessagesIntoDb(String messageText, Subscriber sender, Subscriber recipient) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "INSERT INTO messages (msg_text, recipient_id, sender_id, msg_date)" +
                    " VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, messageText);
            ps.setInt(2, recipient.getId());
            ps.setInt(3, sender.getId());
            ps.setString(4, LocalDateTime.now().toString());
            return ps.executeUpdate() >= 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return false;
    }

    @Override
    public List<Message> getListMessagesByPhone(String phone) {
        Connection connection = null;
        List<Message> messageList = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "SELECT m.msg_date, m.msg_text, m.sender_id FROM messages m " +
                    "INNER JOIN subscribers s " +
                    "ON m.recipient_id = s.id WHERE s.phone = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, phone);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                Message item = new Message();
                item.setMsgDate(LocalDateTime.parse(rs.getString(1)));
                item.setMsgText(rs.getString(2));
                item.setSender(SubscriberService.INSTANCE.findSubscriberById(rs.getInt(3)));
                messageList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return messageList;
    }
}
