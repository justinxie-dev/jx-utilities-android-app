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

public class MapsHistory extends AppCompatActivity {

    private TextView historyDisplay;
    private StringBuilder sb = new StringBuilder();

    // Database declarations
    private AppDatabase db;
    private MapsDao mapsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_history);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0492C2"));
        actionBar.setBackgroundDrawable(colorDrawable);

        historyDisplay = (TextView) findViewById(R.id.mapsHistoryList);

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        mapsDao = db.mapsDao();

        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {

            List<MapsDataEntity> history = mapsDao.getAllRows();

            StringBuilder sb = new StringBuilder();
            for (MapsDataEntity mapsDbRow : history) {
                sb.append("Latitude: ");
                sb.append(mapsDbRow.latitude);
                sb.append("  |  Longitude: ");
                sb.append(mapsDbRow.longitude);
                sb.append("\n");
            }

            runOnUiThread(() -> {
                historyDisplay.setText(sb.toString());
            });
        });
    }
}