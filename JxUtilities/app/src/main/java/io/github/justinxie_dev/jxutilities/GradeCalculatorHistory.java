package io.github.justinxie_dev.jxutilities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class GradeCalculatorHistory extends AppCompatActivity {

    private TextView historyDisplay;
    private StringBuilder sb = new StringBuilder();

    // Database declarations
    private AppDatabase db;
    private GradeCalculatorDao gradeCalculatorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_calculator_history);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#006400"));
        actionBar.setBackgroundDrawable(colorDrawable);

        historyDisplay = (TextView) findViewById(R.id.gradeCalculatorHistoryList);

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        gradeCalculatorDao = db.gradeCalculatorDao();

        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {

            List<GradeCalculatorDataEntity> history = gradeCalculatorDao.getAllRows();

            StringBuilder sb = new StringBuilder();
            for (GradeCalculatorDataEntity gradeCalculatorDbRow : history) {

                sb.append(gradeCalculatorDbRow.assignmentDesc1);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade1);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight1);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc2);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade2);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight2);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc3);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade3);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight3);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc4);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade4);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight4);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc5);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade5);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight5);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc6);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade6);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight6);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc7);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade7);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight7);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc8);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade8);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight8);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc9);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade9);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight9);
                sb.append("\n");

                sb.append(gradeCalculatorDbRow.assignmentDesc10);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.grade10);
                sb.append("     ");
                sb.append(gradeCalculatorDbRow.weight10);
                sb.append("\n");

                sb.append("-------------------------------");
                sb.append("\n");
                sb.append("Grade: ");
                sb.append(gradeCalculatorDbRow.finalGrade);
                sb.append(" ");
                sb.append("(");
                sb.append(gradeCalculatorDbRow.finalLetterGrade);
                sb.append(")");
                sb.append("\n\n");
            }

            runOnUiThread(() -> {
                historyDisplay.setText(sb.toString());
            });
        });
    }
}