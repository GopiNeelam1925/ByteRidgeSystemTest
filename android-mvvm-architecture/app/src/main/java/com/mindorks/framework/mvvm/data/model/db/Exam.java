package com.mindorks.framework.mvvm.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "exam")
public class Exam {

    public Exam() {

    }

    @Ignore
    public Exam(long id, int totalQuestions, int correct, int wrong, int looingAt) {
        this.examId = id;
        this.total_questions = totalQuestions;
        this.correct = correct;
        this.wrong = wrong;
        this.lookingAt = looingAt;
    }

    @PrimaryKey(autoGenerate = true)
    public Long examId;

    @ColumnInfo(name = "total_questions")
    public int total_questions;

    @ColumnInfo(name = "correct")
    public int correct;

    @ColumnInfo(name = "wrong")
    public int wrong;

    @ColumnInfo(name = "lookingAt")
    public int lookingAt;

}
