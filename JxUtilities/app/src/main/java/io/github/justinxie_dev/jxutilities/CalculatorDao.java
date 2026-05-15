package io.github.justinxie_dev.jxutilities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CalculatorDao {

    // Add new row entry to calculations table
    @Insert
    void insertRow(CalculatorDataEntity calculatorDbRow);

    // Retrieve past entries in descending order
    @Query("SELECT * FROM calculator ORDER BY id DESC")
    List<CalculatorDataEntity> getAllRows();

    // Delete all entries/rows
    @Query("DELETE FROM calculator")
    void deleteAllRows();
}
