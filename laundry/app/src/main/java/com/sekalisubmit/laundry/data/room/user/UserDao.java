package com.sekalisubmit.laundry.data.room.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created By Agus Ardi on 25/05/2024.
 */
@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM client")
    List<User> getAllUsers();

    @Query("SELECT * FROM client WHERE id = :id")
    User getUserById(int id);
}