package io.github.justinxie_dev.jxutilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "converter")
public class ConverterDataEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "unit_type")
    // temp, length or mass
    public String unitType;

    @ColumnInfo(name = "from_unit")
    // If Temp: Fahrenheit, Celsius, or Kelvin
    // If Length: inch, foot, yard
    // If Mass: pound, ounce, ton
    public String fromUnit;

    @ColumnInfo(name = "to_unit")
    // If Temp: Fahrenheit, Celsius, or Kelvin
    // If Length: inch, foot, yard
    // If Mass: pound, ounce, ton
    public String toUnit;

    @ColumnInfo(name = "from_value")
    public Double fromValue;

    @ColumnInfo(name = "to_value")
    public Double toValue;

}
