package com.example.hoangcv2_login;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void addUser(User user);

    @Query("SELECT * FROM user WHERE username=:username")
    List<User> loginUser(String username);

    @Query("UPDATE user SET password =:password WHERE username=:username")
    void updatePassword(String password, String username);
}
