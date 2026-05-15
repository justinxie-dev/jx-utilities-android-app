package io.github.justinxie_dev.jxutilities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MapsDao {

    // Add new row entry to table
    @Insert
    void insertRow(MapsDataEntity mapsDbRow);

    // Retrieve past entries in descending order
    @Query("SELECT * FROM maps ORDER BY id DESC")
    List<CalculatorDataEntity> getAllRows();

    // Delete all entries/rows
    @Query("DELETE FROM maps")
    void deleteAllRows();
}
