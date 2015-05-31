package com.example.administrador.myapplication.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.models.persistence.ServiceOrdersRepository;

/**
 * Created by THIAGO on 31/05/2015.
 */
public class User implements Parcelable {

    private String mUser;
    private String mPassword;

    public User(){}

    public User(String password, String user) {
        this.mPassword = password;
        this.mUser = user;
    }

    public String getUser() {
        return this.mUser;
    }

    public void setUser(String user) {
        this.mUser = user;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mUser != null ? !mUser.equals(user.mUser) : user.mUser != null) return false;
        return !(mPassword != null ? !mPassword.equals(user.mPassword) : user.mPassword != null);

    }

    @Override
    public int hashCode() {
        int result = mUser != null ? mUser.hashCode() : 0;
        result = 31 * result + (mPassword != null ? mPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"user\": \"" + mUser + '\"' +
                ", \"password\": \"" + mPassword + '\"' +
                "}";
    }

    public boolean findUser() {
        return ServiceOrdersRepository.getInstance().findUser(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUser);
        dest.writeString(this.mPassword);
    }

    private User(Parcel in) {
        this.mUser = in.readString();
        this.mPassword = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
