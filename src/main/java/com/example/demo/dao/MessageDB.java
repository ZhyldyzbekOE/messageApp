package com.example.demo.dao;

import com.example.demo.dao.impl.MessageDBImpl;
import com.example.demo.model.Message;
import com.example.demo.model.Subscriber;

import java.util.List;

public interface MessageDB {

    MessageDB INSTANCE = new MessageDBImpl();

    List<Message> getMessagesBySenderId(int senderId);

    boolean saveMessagesIntoDb(String messageText, Subscriber sender, Subscriber recipient);

    List<Message> getListMessagesByPhone(String phone);

}
