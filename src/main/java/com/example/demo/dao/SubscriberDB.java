package com.example.demo.dao;

import com.example.demo.dao.impl.SubscriberDBImpl;
import com.example.demo.model.Subscriber;

public interface SubscriberDB {

    SubscriberDB INSTANCE = new SubscriberDBImpl();

    Subscriber findSubcriberByPhone(String phone);

    boolean deactivateSubscriber(String phone);
}
