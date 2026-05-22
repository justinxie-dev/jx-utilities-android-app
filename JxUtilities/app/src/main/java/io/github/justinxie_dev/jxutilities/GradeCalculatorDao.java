package io.github.justinxie_dev.jxutilities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface GradeCalculatorDao {

    // Add new row entry to table
    @Insert
    void insertRow(GradeCalculatorDataEntity gradeCalculatorDbRow);

    // Retrieve past entries in descending order
    @Query("SELECT * FROM grade_calculator ORDER BY id DESC")
    List<GradeCalculatorDataEntity> getAllRows();

    // Delete all entries/rows
    @Query("DELETE FROM grade_calculator")
    void deleteAllRows();
}
