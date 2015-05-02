package austinlentzmobileapp.pickupi399;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class createPage extends ActionBarActivity implements GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {


    private static LatLng fromPosition = null;
    private static LatLng toPosition = null;
    private GoogleMap googleMap;

    EditText mTitleEditText;
    EditText mTimeEditText;
    EditText mSportEditText;
    EditText mDescriptionEditText;
    EditText mCoordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_page);

        //sets the global variables
        mTitleEditText = (EditText) findViewById(R.id.titleEditText);
        mTimeEditText = (EditText) findViewById(R.id.timeEditText);
        mSportEditText = (EditText) findViewById(R.id.sportEditText);
        mDescriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        mCoordText = (EditText) findViewById(R.id.coordText);
        addGoogleMap();
        addMarkers();


        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        //fetches current location
        Location myLocation = locationManager.getLastKnownLocation(provider);
        double latitude = myLocation.getLatitude();
        //fetches long
        double longitude = myLocation.getLongitude();

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude) , 15.0f) );

        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Click and hold the marker to drag and drop the marker where your game is.", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }



    private void addGoogleMap() {
        // check if we have got the googleMap already
        if (googleMap == null) {
            googleMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();
            googleMap.setOnMarkerClickListener(this);
            googleMap.setOnMarkerDragListener(this);
        }

    }

    private void addMarkers() {
        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        //fetches current location
        Location myLocation = locationManager.getLastKnownLocation(provider);
        double latitude = myLocation.getLatitude();
        //fetches long
        double longitude = myLocation.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
                .title("Location")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //zooms in to a specific zoom
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(5));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Log.i("GoogleMapActivity", "onMarkerClick");
        Toast.makeText(getApplicationContext(),
                "Marker Clicked: " + marker.getTitle(), Toast.LENGTH_LONG)
                .show();
        return false;
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        // do nothing during drag
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        toPosition = marker.getPosition();

        LatLng work = toPosition;
        Double l1 = work.latitude;
        Double l2 = work.longitude;
        String workl1 = l1.toString();
        String workl2 = l2.toString();

        Toast.makeText(
                getApplicationContext(),
                "Marker " + marker.getTitle() + " dragged from " + fromPosition
                        + " to " + workl1, Toast.LENGTH_LONG).show();
    LatLng latlng = toPosition;
    double latty = latlng.latitude;
    double longy = latlng.longitude;
    String finallatty = String.valueOf(latty);
    String finallongy = String.valueOf(longy);
    mCoordText.setText(finallatty + " " + finallongy);
    //double lat = toPosition.latitude(new double);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        fromPosition = marker.getPosition();
        Log.d(getClass().getSimpleName(), "Drag start at: " + fromPosition);
    }

    public void createIt(View view) {

        GamesDBHandler dbHandler = new GamesDBHandler(this,null,null,1);
        //get the title
        String title = mTitleEditText.getText().toString();
        //get the time
        String time = mTimeEditText.getText().toString();
        //get the sport
        String sport = mSportEditText.getText().toString();
        //get the description
        String description = mDescriptionEditText.getText().toString();

        String herewego = mCoordText.getText().toString();
        String[] latlong = herewego.split(" ");
        String latitude = latlong[0];
        String longitude = latlong[1];

        //makes new game with these values
        Game myGame = new Game(title, time, sport, description, latitude, longitude);

        //add the student
        dbHandler.addGame(myGame);

        //clears the fields after a game was entered
        mTitleEditText.setText("");
        mTimeEditText.setText("");
        mSportEditText.setText("");
        mDescriptionEditText.setText("");
        mCoordText.setText("");

        Intent myIntent = new Intent(this, explorePage.class);
        startActivity(myIntent);
    }
    //creates game goes to explore page
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_page, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}