package com.example.demo.model;

import java.time.LocalDateTime;

public class MessageInfo {
    private String text;
    private LocalDateTime msgDate;
    private String senderPhone;
    private int senderId;

    public MessageInfo(String text, LocalDateTime msgDate, String senderPhone, int senderId) {
        this.text = text;
        this.msgDate = msgDate;
        this.senderPhone = senderPhone;
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(LocalDateTime msgDate) {
        this.msgDate = msgDate;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "text='" + text + '\'' +
                ", msgDate=" + msgDate +
                ", senderPhone='" + senderPhone + '\'' +
                ", senderId=" + senderId +
                '}';
    }
}
