package com.example.demo.dao;

import com.example.demo.dao.impl.MessageDBImpl;
import com.example.demo.model.Message;

import java.util.List;

public interface MessageDB {

    MessageDB INSTANCE = new MessageDBImpl();

    List<Message> getMessagesBySenderId(int senderId);

}
