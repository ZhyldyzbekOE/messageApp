package com.example.demo.dao.impl;

import com.example.demo.dao.ConnectionDB;
import com.example.demo.dao.SubscriberDB;
import com.example.demo.model.Subscriber;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriberDBImpl implements SubscriberDB {
    @Override
    public Subscriber findSubcriberByPhone(String phone) {
        Connection connection = null;
        Subscriber subscriber = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "SELECT * FROM subscribers WHERE phone = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subscriber = new Subscriber();
                subscriber.setId(rs.getInt("id"));
                subscriber.setPhone(rs.getString("phone"));
                subscriber.setBlocked(rs.getInt("is_blocked") == 1 ? true : false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return subscriber;
    }

    @Override
    public Subscriber findSubcriberById(int id) {
        Connection connection = null;
        Subscriber subscriber = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "SELECT * FROM subscribers WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subscriber = new Subscriber();
                subscriber.setId(rs.getInt("id"));
                subscriber.setPhone(rs.getString("phone"));
                subscriber.setBlocked(rs.getInt("is_blocked") == 1 ? true : false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return subscriber;
    }

    @Override
    public boolean deactivateSubscriber(String phone) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String query = "UPDATE subscribers SET is_blocked = ? WHERE phone = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setString(2, phone);
            return ps.executeUpdate() > 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return false;
    }
}
