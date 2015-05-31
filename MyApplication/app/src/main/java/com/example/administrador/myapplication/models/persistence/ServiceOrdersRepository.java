package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ServiceOrdersRepository {

    private static class Singleton {
        public static final ServiceOrdersRepository INSTANCE = new ServiceOrdersRepository();
    }

    private ServiceOrdersRepository() {
        super();
    }

    public static ServiceOrdersRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public void save(ServiceOrder serviceOrder) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        if (serviceOrder.getId() == null) {
            db.insert(ServiceOrderContract.TABLE, null, ServiceOrderContract.getContentValues(serviceOrder));
        } else {
            String where = ServiceOrderContract.ID + " = ?";
            String[] args = {serviceOrder.getId().toString()};
            db.update(ServiceOrderContract.TABLE, ServiceOrderContract.getContentValues(serviceOrder), where, args);
        }
        db.close();
        helper.close();
    }

    public void delete(ServiceOrder serviceOrder) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = ServiceOrderContract.ID + " = ?";
        String[] args = {serviceOrder.getId().toString()};
        db.update(ServiceOrderContract.TABLE, ServiceOrderContract.getContentValuesUpdateDelete(), where, args);
        db.close();
        helper.close();
    }

    public List<ServiceOrder> getAll(Integer active) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where = ServiceOrderContract.ACTIVE + " = " + active;
        Cursor cursor = db.query(ServiceOrderContract.TABLE, ServiceOrderContract.COLUNS, where, null, null, null, ServiceOrderContract.DATE);
        List<ServiceOrder> serviceOrders = ServiceOrderContract.bindList(cursor);
        db.close();
        helper.close();
        return serviceOrders;
    }

    public boolean findUser(User mUser) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where = " " + UserContract.USER + " = ? AND " + UserContract.PASSWORD + " = ? ";
        String[] args = {mUser.getUser(), mUser.getPassword()};
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, where, args, null, null, UserContract.USER);
        boolean result = UserContract.findUser(cursor);
        db.close();
        helper.close();
        return result;
    }

}