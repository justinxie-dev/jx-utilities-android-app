package io.github.justinxie_dev.jxutilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "maps")
public class MapsDataEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "latitude")
    // Stores latitude value
    public double latitude;

    @ColumnInfo(name = "longitude")
    // Stores longitude value
    public double longitude;
}
