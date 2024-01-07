package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.function.Consumer;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.application.FavoritePlacesApplication;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;

public class AddPlanActivity extends AppCompatActivity
    implements Consumer<ResultMightThrow<Boolean>> {
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Use Intent to return to MainActivity
    Intent backToMap = new Intent(this, MainActivity.class);
    // Show the layout of AddPlaceActivity
    setContentView(R.layout.activity_plan);
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

    Button saveButton = findViewById(R.id.save_button);

    FavoritePlacesApplication favoritePlacesApplication =
        (FavoritePlacesApplication) getApplication();
    saveButton.setOnClickListener(
        v -> {
          //String getID = FavoritePlacesApplication.CLIENT_ID;
          EditText editId = findViewById(R.id.name_plan);
          EditText editText = findViewById(R.id.note);
          String getID = editId.getText().toString();
          String getNote = editText.getText().toString();
          Place place = new Place(getID, getNote, 1);
          favoritePlacesApplication.getClient().postPlan(place, this);
          startActivity(backToMap);
        });
  }

  @Override
  public void accept(final ResultMightThrow<Boolean> booleanResultMightThrow) {}
}
