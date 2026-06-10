package io.github.justinxie_dev.jxutilities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class MapsMenuActivity extends AppCompatActivity {

    private EditText latDisplayTextEditText;
    private EditText longDisplayTextEditText;

    private EditText latInputEditText;
    private EditText longInputEditText;

    private Button picForMeButton;
    private Button tapToExpButton;

    // Need double primitive not Double wrapper so ints can be converted to double format/type
    static double latitude;
    static double longitude;

    // Database declarations
    private AppDatabase db;
    private MapsDao mapsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_menu);

        latDisplayTextEditText = (EditText) findViewById(R.id.latitudeDisplayTextEditText);
        longDisplayTextEditText = (EditText) findViewById(R.id.longitudeDisplayTextEditText);

        latInputEditText = (EditText) findViewById(R.id.latitudeInputEditText);
        longInputEditText = (EditText) findViewById(R.id.longitudeInputEditText);

        picForMeButton = (Button) findViewById(R.id.pickForMeButton);
        tapToExpButton = (Button) findViewById(R.id.tapToExploreButton);

        picForMeButton.setBackgroundColor(Color.BLUE);
        // Source 1 for setting transparency effect to the button: https://stackoverflow.com/questions/20743124/setting-transparency-to-buttons-in-android
        // Source 2 for setting transparency effect to the button: https://stackoverflow.com/questions/38327188/android-setting-background-color-of-button-loses-ripple-effect
        tapToExpButton.setBackgroundColor(Color.TRANSPARENT);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0492C2"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // EditText disable editing source link: https://stackoverflow.com/questions/4297763/disabling-of-edittext-in-android
        latDisplayTextEditText.setFocusable(false);
        longDisplayTextEditText.setFocusable(false);

        // Globe image source link: https://www.photowall.com/us/globe-poster

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        mapsDao = db.mapsDao();
    }

    public void pickForMeButtonClick(View view) {
        // Randomizer source link: https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range

        // Setting random latitude and longitude coordinates
        double randomLatitude = ThreadLocalRandom.current().nextDouble(-90.0, 91.0);
        double randomLongitude = ThreadLocalRandom.current().nextDouble(-180.0, 181.0);

        // Randomizer adjustment, should value exceed coordinate limits in the decimal places
        if (randomLatitude > 90.0) {
            randomLatitude = 90.0;
        }

        if (randomLongitude > 180.0) {
            randomLongitude = 180.0;
        }

        // Making wrapper versions so that it can be toString()'ed
        Double randomLatitudeWrapperVersion = randomLatitude;
        Double randomLongitudeWrapperVersion = randomLongitude;

        // Outputting the inputs
        // Decimal to six places source: https://stackoverflow.com/questions/7747469/how-can-i-truncate-a-double-to-only-two-decimal-places-in-java
        String latitudeDecimalFormat = new DecimalFormat("#.######").format(randomLatitudeWrapperVersion);
        String longitudeDecimalFormat = new DecimalFormat("#.######").format(randomLongitude);
        latInputEditText.setText(latitudeDecimalFormat);
        longInputEditText.setText(longitudeDecimalFormat);
    }

    public void tapToExploreButtonClick(View view) {
        int COUNTER = 0;

        if (latInputEditText.getText().toString().isEmpty()) {
            latInputEditText.setError("Please enter a value from -90 to 90.");
            COUNTER++;
        } else if (!(Double.parseDouble(latInputEditText.getText().toString()) >= -90.0) || !(Double.parseDouble(latInputEditText.getText().toString()) <= 90.0) ) {
            latInputEditText.setError("Invalid latitude. Please enter a value from -90 to 90.");
            COUNTER++;
        }

        if (longInputEditText.getText().toString().isEmpty()) {
            longInputEditText.setError("Please enter a value from -180 to 180.");
            COUNTER++;
        } else if (!(Double.parseDouble(longInputEditText.getText().toString()) >= -180.0) || !(Double.parseDouble(longInputEditText.getText().toString()) <= 180.0)) {
            longInputEditText.setError("Invalid longitude. Please enter a value from -180 to 180.");
            COUNTER++;
        }

        if (COUNTER == 0) {
            Double latitudeTransfertoPrimitive = Double.valueOf(latInputEditText.getText().toString());
            latitude = latitudeTransfertoPrimitive;

            Double longitudeTransfertoPrimitive = Double.valueOf(longInputEditText.getText().toString());
            longitude = longitudeTransfertoPrimitive;

            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else {
            Context context = getApplicationContext();
            CharSequence text = ("\uD83C\uDF0E") + " " + "Please enter valid values for latitude and longitude" + " " + ("\uD83C\uDF0E");
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        // Store entry/row into database on a separate thread away from UI thread to prevent crashing
        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {
            MapsDataEntity mapsDbRow = new MapsDataEntity();
            mapsDbRow.latitude = latitude;
            mapsDbRow.longitude = longitude;
            mapsDao.insertRow(mapsDbRow);
        });
    }

    // Establish History button and link the Activity to the Activty History screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This line links top_menu.xml file to this activity's top/action bar
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the item clicked matches the ID from top_menu.xml
        if (item.getItemId() == R.id.action_history) {
            Intent intent = new Intent(this, MapsHistory.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}