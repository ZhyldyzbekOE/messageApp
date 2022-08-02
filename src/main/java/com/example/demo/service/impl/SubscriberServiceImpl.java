package com.example.demo.service.impl;

import com.example.demo.dao.SubscriberDB;
import com.example.demo.model.Subscriber;
import com.example.demo.service.SubscriberService;

public class SubscriberServiceImpl implements SubscriberService {


    @Override
    public Subscriber findSubscriberByPhone(String phone) {
        Subscriber subscriberFromDb = SubscriberDB.INSTANCE.findSubcriberByPhone(phone);
        return subscriberFromDb;
    }

    @Override
    public boolean deactivateSubscriber(String phone) {
        return SubscriberDB.INSTANCE.deactivateSubscriber(phone);
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
