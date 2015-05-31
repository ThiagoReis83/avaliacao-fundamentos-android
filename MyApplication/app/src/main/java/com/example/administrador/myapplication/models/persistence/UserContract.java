package com.example.administrador.myapplication.models.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.models.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserContract {

    public static final String TABLE = "user";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String ADMIN = "'admin'";

    public static final String[] COLUNS = {USER, PASSWORD};

    public static String createTable() {
        final StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(USER + " TEXT, ");
        sql.append(PASSWORD + " TEXT ");
        sql.append(" ); ");
        return sql.toString();
    }

    public static String insertUserDefault() {
        final StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ");
        sql.append(TABLE);
        sql.append(" VALUES ( ");
        sql.append(ADMIN + ", ");
        sql.append(ADMIN);
        sql.append(" ); ");
        return sql.toString();
    }

    public static boolean findUser(Cursor cursor) {
        User user = null;
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            return true;
        }
        return false;
    }

}
