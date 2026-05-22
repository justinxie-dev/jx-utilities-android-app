package io.github.justinxie_dev.jxutilities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

// Reference: https://developer.android.com/training/data-storage/room (Database section)
@Database(entities = {CalculatorDataEntity.class, ConverterDataEntity.class, MapsDataEntity.class, GradeCalculatorDataEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CalculatorDao calculatorDao();

    public abstract ConverterDao converterDao();

    public abstract MapsDao mapsDao();

    public abstract GradeCalculatorDao gradeCalculatorDao();
}
