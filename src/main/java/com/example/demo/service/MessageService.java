package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.model.Subscriber;
import com.example.demo.service.impl.MessageServiceImpl;

public interface MessageService {

    MessageService INSTANCE = new MessageServiceImpl();

    boolean sendMessage(Message message);
}
