package io.github.justinxie_dev.jxutilities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grade_calculator")
public class GradeCalculatorDataEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "assignment_desc_1")
    public String assignmentDesc1;

    @ColumnInfo(name = "assignment_desc_2")
    public String assignmentDesc2;

    @ColumnInfo(name = "assignment_desc_3")
    public String assignmentDesc3;

    @ColumnInfo(name = "assignment_desc_4")
    public String assignmentDesc4;

    @ColumnInfo(name = "assignment_desc_5")
    public String assignmentDesc5;

    @ColumnInfo(name = "assignment_desc_6")
    public String assignmentDesc6;

    @ColumnInfo(name = "assignment_desc_7")
    public String assignmentDesc7;

    @ColumnInfo(name = "assignment_desc_8")
    public String assignmentDesc8;

    @ColumnInfo(name = "assignment_desc_9")
    public String assignmentDesc9;

    @ColumnInfo(name = "assignment_desc_10")
    public String assignmentDesc10;

    @ColumnInfo(name = "grade_1")
    public Double grade1;

    @ColumnInfo(name = "grade_2")
    public Double grade2;

    @ColumnInfo(name = "grade_3")
    public Double grade3;

    @ColumnInfo(name = "grade_4")
    public Double grade4;

    @ColumnInfo(name = "grade_5")
    public Double grade5;

    @ColumnInfo(name = "grade_6")
    public Double grade6;

    @ColumnInfo(name = "grade_7")
    public Double grade7;

    @ColumnInfo(name = "grade_8")
    public Double grade8;

    @ColumnInfo(name = "grade_9")
    public Double grade9;

    @ColumnInfo(name = "grade_10")
    public Double grade10;

    @ColumnInfo(name = "weight_1")
    public Double weight1;

    @ColumnInfo(name = "weight_2")
    public Double weight2;

    @ColumnInfo(name = "weight_3")
    public Double weight3;

    @ColumnInfo(name = "weight_4")
    public Double weight4;

    @ColumnInfo(name = "weight_5")
    public Double weight5;

    @ColumnInfo(name = "weight_6")
    public Double weight6;

    @ColumnInfo(name = "weight_7")
    public Double weight7;

    @ColumnInfo(name = "weight_8")
    public Double weight8;

    @ColumnInfo(name = "weight_9")
    public Double weight9;

    @ColumnInfo(name = "weight_10")
    public Double weight10;

    @ColumnInfo(name = "final_grade")
    public Double finalGrade;

    @ColumnInfo(name = "final_letter_grade")
    public String finalLetterGrade;
}
