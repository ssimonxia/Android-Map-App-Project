package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.application.FavoritePlacesApplication;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;
import java.util.function.Consumer;

public class AddPlaceActivity extends AppCompatActivity
    implements Consumer<ResultMightThrow<Boolean>> {
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Use Intent to return to MainActivity
    Intent backToMap = new Intent(this, MainActivity.class);
    // Show the layout of AddPlaceActivity
    setContentView(R.layout.activity_addplace);
    // Clear the flag when return to the MainActivity
    backToMap.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    // Define cancelButton
    Button cancelButton = findViewById(R.id.cancel_button);
    // Go back to MainActivity when click cancel button
    cancelButton.setOnClickListener(
        v -> {
          startActivity(backToMap);
        });

    // Define save button
    Intent intent = getIntent();
    // Get latitude and longitude
    double latitude = Double.parseDouble(intent.getStringExtra("latitude"));
    double longitude = Double.parseDouble(intent.getStringExtra("longitude"));
    Button saveButton = findViewById(R.id.save_button);

    FavoritePlacesApplication favoritePlacesApplication =
        (FavoritePlacesApplication) getApplication();
    saveButton.setOnClickListener(
        v -> {
          String getID = FavoritePlacesApplication.CLIENT_ID;
          EditText editText = findViewById(R.id.description);
          EditText editType = findViewById(R.id.type);
          String getDescription = editText.getText().toString();
          String getType = editType.getText().toString();
          Place place = new Place(getID, "Simon", latitude, longitude, getDescription, 1, getType);
          favoritePlacesApplication.getClient().postFavoritePlace(place, this);
          startActivity(backToMap);
        });
  }

  @Override
  public void accept(final ResultMightThrow<Boolean> booleanResultMightThrow) {}
}
