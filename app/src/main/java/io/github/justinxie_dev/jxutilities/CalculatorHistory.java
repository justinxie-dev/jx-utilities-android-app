package io.github.justinxie_dev.jxutilities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class CalculatorHistory extends AppCompatActivity {

    private TextView historyDisplay;
    private StringBuilder sb = new StringBuilder();

    // Database declarations
    private AppDatabase db;
    private CalculatorDao calculatorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_history);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#800000"));
        actionBar.setBackgroundDrawable(colorDrawable);

        historyDisplay = (TextView) findViewById(R.id.calculatorHistoryList);

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        calculatorDao = db.calculatorDao();

        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {

            List<CalculatorDataEntity> history = calculatorDao.getAllRows();

            StringBuilder sb = new StringBuilder();
            for (CalculatorDataEntity calculatorDbRow : history) {
                sb.append("Input: ");
                sb.append(calculatorDbRow.expression);
                sb.append("  |  Output: ");
                sb.append(calculatorDbRow.result);
                sb.append("\n");
            }

            runOnUiThread(() -> {
                historyDisplay.setText(sb.toString());
            });
        });
    }
}