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

public class ConverterHistory extends AppCompatActivity {

    private TextView historyDisplay;

    // Database declarations
    private AppDatabase db;
    private ConverterDao converterDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_history);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#E6CC00"));
        actionBar.setBackgroundDrawable(colorDrawable);

        historyDisplay = (TextView) findViewById(R.id.converterHistoryList);

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        converterDao = db.converterDao();

        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {

            List<ConverterDataEntity> history = converterDao.getAllRows();

            StringBuilder sb = new StringBuilder();
            for (ConverterDataEntity converterDbRow : history) {
                sb.append(converterDbRow.unitType);
                sb.append("   ");
                sb.append(converterDbRow.fromValue);
                sb.append(" ");
                sb.append(converterDbRow.fromUnit);
                sb.append(" = ");
                sb.append(converterDbRow.toValue);
                sb.append(" ");
                sb.append(converterDbRow.toUnit);
                sb.append("\n");
            }

            runOnUiThread(() -> {
                historyDisplay.setText(sb.toString());
            });
        });
    }
}