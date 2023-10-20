package com.example.task.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.task.models.UserDetails;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    void insertUser(UserDetails userDetails);

    @Query("select * from user")
    List<UserDetails> getUserDtails();

    @Query("Delete from user")
    void Delete();
}
