package com.example.demo.service;

import com.example.demo.model.MessageInfo;
import com.example.demo.model.Subscriber;
import com.example.demo.service.impl.MessageServiceImpl;

import java.util.List;

public interface MessageService {

    MessageService INSTANCE = new MessageServiceImpl();

    boolean sendMessage(String messageText, String recPhone, String senPhone);

    boolean saveMessages(String messageText, Subscriber sender, Subscriber recipient);

    List<MessageInfo> showListsByPhone(String phone);
}
