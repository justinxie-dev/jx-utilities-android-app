package io.github.justinxie_dev.jxutilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "calculator")
public class CalculatorDataEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "expression")
    // Stores the formula (i.e. user input)
    public String expression;

    @ColumnInfo(name = "result")
    // Stores answer or Correct/Incorrect (i.e. output)
    public String result;
}
