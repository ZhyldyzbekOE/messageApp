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
    public Subscriber findSubscriberById(int id) {
        return SubscriberDB.INSTANCE.findSubcriberById(id);
    }

    @Override
    public boolean deactivateSubscriber(String phone) {
        return SubscriberDB.INSTANCE.deactivateSubscriber(phone);
    }
}

