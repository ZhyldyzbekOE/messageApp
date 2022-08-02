package com.example.demo.service;

import com.example.demo.model.Subscriber;
import com.example.demo.service.impl.SubscriberServiceImpl;

public interface SubscriberService {

    SubscriberService INSTANCE = new SubscriberServiceImpl();

    Subscriber findSubscriberByPhone(String phone);

    boolean deactivateSubscriber(String phone);


}
