package io.github.justinxie_dev.jxutilities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ConverterDao {

    // Add new row entry to table
    @Insert
    void insertRow(ConverterDataEntity converterDbRow);

    // Retrieve past entries in descending order
    @Query("SELECT * FROM converter ORDER BY id DESC")
    List<ConverterDataEntity> getAllRows();

    // Delete all entries/rows
    @Query("DELETE FROM converter")
    void deleteAllRows();
}
