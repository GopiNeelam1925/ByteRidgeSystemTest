package com.mindorks.framework.mvvm.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mindorks.framework.mvvm.data.model.db.Exam;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ExamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNewExam(Exam exam);

    @Query(("Select * from exam where examId=:examId"))
    Flowable<Exam> getCurrentExam(long examId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateCurrentExam(Exam exam);
}
