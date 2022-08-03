package com.example.demo.service.impl;

import com.example.demo.dao.MessageDB;
import com.example.demo.exception.SubscriberBlockedException;
import com.example.demo.model.Message;
import com.example.demo.model.Subscriber;
import com.example.demo.service.MessageService;
import com.example.demo.service.SubscriberService;

import java.time.LocalDateTime;
import java.util.List;

public class MessageServiceImpl implements MessageService {


    @Override
    public boolean sendMessage(String messageText, String recPhone, String senPhone) {
        Subscriber recipient = SubscriberService.INSTANCE.findSubscriberByPhone(recPhone);
        Subscriber sender = SubscriberService.INSTANCE.findSubscriberByPhone(senPhone);
        if (recipient.isBlocked()) {
            throw new SubscriberBlockedException("Получатель заблокирован!");
        }

        List<Message> messageList = MessageDB.INSTANCE.getMessagesBySenderId(sender.getId());


        for (Message item: messageList) {
        }

        return false;
    }
}


/*
        MessageServiceImpl -> метод для отправки
        subscriberFromDb.getBlocked()
            select subscriberFromDb.getId() проверить:
                1. В день можно отправить только 6 сообщений -> Вы исчерпали лимит за 24 часа
                2. Нельзя отправлять два раза одному и тому же абононету за 24 часа -> Вы уже отправляли сообщение данному пользователю два раза
                Сделать сохранения сообщения
        Alert об ошшибке
* */
