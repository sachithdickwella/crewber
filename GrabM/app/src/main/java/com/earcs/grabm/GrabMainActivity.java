package com.earcs.grabm;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.earcs.grabm.util.DialogType;
import com.earcs.grabm.util.GrabMConstant;
import com.earcs.grabm.util.anim.ResizeAnimation;
import com.earcs.grabm.util.executor.DialogExecutor;
import com.earcs.grabm.util.executor.GrabAsyncTask;
import com.earcs.grabm.util.executor.GrabTaskHandler;
import com.earcs.grabm.util.message.RegistrationIntentService;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GrabMainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, LocationListener,
        GoogleApiClient.ConnectionCallbacks, GrabMConstant {

    private static final int RESOLUTION_ACTIVITY_CODE = 1254;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "GrabMainActivity";
    private static String displayName;
    private static String email;
    private static String loggedBy;
    private SharedPreferences mSharedPreferences;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClient mGoogleLocationApiClient;
    private LocationRequest mLocationRequest;
    private BottomSheetBehavior<LinearLayout> mBottomSheetBehavior;
    private View mView;
    private LinearLayout mBottomSheetTopBar;
    private static View mContentView;
    private static CardView notificationCard;

    private TextView userName, userEmail,
            pickupLocationValue, vehicleLocationValue;
    /**
     * My location marker.
     */
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grab_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        mContentView = findViewById(R.id.grabm_content_pane);
        notificationCard = (CardView) findViewById(R.id.mNotificationCards);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        View navHeaderInflater = getLayoutInflater().inflate(R.layout.nav_header_grab_main, navigationView);
        userName = (TextView) navHeaderInflater.findViewById(R.id.userName);
        userEmail = (TextView) navHeaderInflater.findViewById(R.id.userEmail);

        pickupLocationValue = (TextView) findViewById(R.id.pickupLocationValue);
        vehicleLocationValue = (TextView) findViewById(R.id.vehicleLocationValue);

        Intent intent = getIntent();
        displayName = intent.getStringExtra(getString(R.string.google_display_name));
        email = intent.getStringExtra(getString(R.string.google_email));
        loggedBy = intent.getStringExtra(getString(R.string.google_twitter_logged_by));

        mSharedPreferences = getSharedPreferences(GrabMConstant.FILE_KEY, MODE_PRIVATE);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mBottomSheetTopBar = (LinearLayout) findViewById(R.id.bottom_sheet_topbar);
        mView = findViewById(R.id.main_overlay);
        final LinearLayout bottomView = (LinearLayout) findViewById(R.id.bottom_sheet);
        if (bottomView != null) {
            mBottomSheetBehavior = BottomSheetBehavior.from(bottomView);
            mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    /**
                     * Nothing goes here.
                     */
                }
            });

            if (mView != null) {
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        collapse();
                        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                });
            }
        }

        mGoogleApiClient = SplashActivity.getGoogleApiClient();
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
        mGoogleLocationApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();
        mLocationRequest = _CreateLocationRequest();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(GrabMConstant.QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Log.e(TAG, getString(R.string.token_sent));
                } else {
                    Log.e(TAG, getString(R.string.token_not_sent));
                }
            }
        };

        if (checkPlayServices()) {
            Intent intentService = new Intent(this, RegistrationIntentService.class);
            startService(intentService);
        }

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(GrabMConstant.KEY_LOGIN_STATUS, LoginStatus.LOGIN.toString());
        if (loggedBy.equals(GrabMConstant.LoginMethod.BY_TWITTER.toString())) {
            editor.putString(GrabMConstant.KEY_LOGIN_BY, GrabMConstant.LoginMethod.BY_TWITTER.toString());
        } else {
            editor.putString(GrabMConstant.KEY_LOGIN_BY, GrabMConstant.LoginMethod.BY_GOOGLE.toString());
        }
        editor.apply();

        final Button callDriverButton = (Button) findViewById(R.id.callDriveButton),
                justLandedButton = (Button) findViewById(R.id.justLandedButton);
        if (callDriverButton != null) {
            callDriverButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * TODO code goes here.
                     */
                    Uri number = Uri.parse("tel:+94777123456");
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                }
            });
        }
        if (justLandedButton != null) {
            justLandedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogExecutor.getDialogExceutor(GrabMainActivity.this, null).showDialog(DialogType.DIALOG_REQUEST_VEHICLE);
                }
            });
        }

        Button cardDismissButton = (Button) findViewById(R.id.buttonDissmiss);
        if (cardDismissButton != null) {
            cardDismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificationCard.setVisibility(View.GONE);
                }
            });
        }
    }

    public static CardView getNotificationCard() {
        return notificationCard;
    }

    public void registerReceiver(Context context) {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(context).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(GrabMConstant.QuickstartPreferences.REGISTRATION_COMPLETE));

            isReceiverRegistered = true;
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.e(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleLocationApiClient.connect();

        userName.setText(displayName);
        userEmail.setText(email);

        mSharedPreferences.edit()
                .putString(GrabMConstant.KEY_NAME, displayName)
                .putString(GrabMConstant.KEY_EMAIL, email)
                .apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleLocationApiClient.disconnect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grab_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.driverDetails:
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN
                        || mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    expand();
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    collapse();
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rideHistory:
                break;
            case R.id.logOut:
                signOut();
                break;
            case R.id.settings:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng cmb_lk = new LatLng(6.927079, 79.861244);
        final CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(cmb_lk)
                .zoom(10.0f)
                .build());
        mMap.moveCamera(cameraUpdate);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });

        try {
            GrabTaskHandler.executeSingleThread(UPDATE_MAP);
        } catch (InterruptedException ex) {
            Log.e(TAG, ex.toString());
        }
        /**
         * Start temp code.
         **/
         LatLng pickup_poin_1 = new LatLng(6.909982, 79.888616);
         LatLng pickup_poin_2 = new LatLng(6.909379, 79.897250);
         LatLng pickup_poin_3 = new LatLng(6.906502, 79.902732);

         Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_location_marker)
         .copy(Bitmap.Config.ARGB_8888, true);

        MAP_LATLANG.put("position 1", pickup_poin_1);
        MAP_LATLANG.put("position 2", pickup_poin_2);
        MAP_LATLANG.put("position 3", pickup_poin_3);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
         paint.setTextSize(100);

         Canvas canvas = new Canvas(bitmap);
         canvas.drawText("1", 65, 90, paint);

         mMap.addMarker(new MarkerOptions()
         .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
         .position(pickup_poin_1));

         bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_my_pickup_location_marker)
         .copy(Bitmap.Config.ARGB_8888, true);
         canvas = new Canvas(bitmap);
         canvas.drawText("2", 65, 90, paint);
         mMap.addMarker(new MarkerOptions()
         .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
         .position(pickup_poin_2));

         bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_location_marker)
         .copy(Bitmap.Config.ARGB_8888, true);
         canvas = new Canvas(bitmap);
         canvas.drawText("3", 65, 90, paint);
         mMap.addMarker(new MarkerOptions()
         .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
         .position(pickup_poin_3));

         /*
         double[][] dummy_route_data = new double[][]{{6.911673, 79.864945},
         {6.911668, 79.865159}, {6.911668, 79.865444}, {6.911657, 79.865661},
         {6.911665, 79.865811}, {6.911657, 79.865991}, {6.911654, 79.866192},
         {6.911641, 79.866393}, {6.911638, 79.866895}, {6.911612, 79.867042},
         {6.911601, 79.867233}, {6.911593, 79.867391}, {6.911582, 79.867536},
         {6.911569, 79.867705}, {6.911569, 79.867903}, {6.911566, 79.868008},
         {6.911737, 79.86801}, {6.912006, 79.868032}, {6.912163, 79.868179},
         {6.912134, 79.868354}, {6.91212, 79.868501}, {6.912022, 79.868501},
         {6.911923, 79.868483}, {6.911811, 79.868464}, {6.911705, 79.86844},
         {6.91155, 79.868421}, {6.911548, 79.868584}, {6.91155, 79.868735},
         {6.911542, 79.868885}, {6.911548, 79.869027}, {6.91154, 79.869223}, {6.911532, 79.8694},
         {6.911532, 79.869577}, {6.911524, 79.869759}, {6.911455, 79.869869}, {6.911337, 79.869864},
         {6.911226, 79.869858}, {6.911106, 79.869856}, {6.911031, 79.86985}, {6.910938, 79.869848},
         {6.911148, 79.869853}, {6.911335, 79.869864}, {6.911519, 79.869915}, {6.911505, 79.870084},
         {6.911503, 79.870247}, {6.911497, 79.870419}, {6.911484, 79.870601}, {6.911484, 79.870752},
         {6.911473, 79.870896}, {6.911649, 79.870846}, {6.911878, 79.870757}, {6.912048, 79.870682},
         {6.912275, 79.870599}, {6.91253, 79.870481}, {6.912719, 79.870398}, {6.912932, 79.870322},
         {6.912996, 79.870457}, {6.91298, 79.870604}, {6.912964, 79.870749}, {6.912948, 79.870918},
         {6.912927, 79.871065}, {6.912908, 79.871224}, {6.912898, 79.871406}, {6.912879, 79.871607},
         {6.912874, 79.871717}, {6.912858, 79.871827}, {6.912834, 79.87205}, {6.912815, 79.872208},
         {6.91281, 79.872356}, {6.912799, 79.872487}, {6.912783, 79.872643}, {6.912765, 79.872785},
         {6.912781, 79.872616}, {6.912765, 79.872782}, {6.912746, 79.872975}, {6.912735, 79.873123},
         {6.912722, 79.873273}, {6.912698, 79.873479}, {6.912671, 79.873643}, {6.912658, 79.873815},
         {6.912645, 79.873997}, {6.912621, 79.874222}, {6.912605, 79.874397}, {6.912581, 79.874576},
         {6.912573, 79.874745}, {6.912549, 79.874955}, {6.91253, 79.875142}, {6.912504, 79.87533}};

         LatLng startingPint = new LatLng(dummy_route_data[0][0], dummy_route_data[0][1]);
         mMap.addMarker(new MarkerOptions()
         .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_car_marker))
         .position(startingPint).rotation(90.0f));

         *
         * End temp code.
         */

    }

    /*
     * Sign out from login.
     */
    private void signOut() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        if (loggedBy.equals(GrabMConstant.LoginMethod.BY_GOOGLE.toString())) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                mGoogleApiClient.disconnect();
                            }
                        }
                    });
        }

        mSharedPreferences.edit()
                .clear()
                .apply();

        Intent authIntent = new Intent(getApplicationContext(), AuthenticationActivity.class);
        startActivity(authIntent);
        finish();
        isReceiverRegistered = false;
    }

    @Override
    public void onLocationChanged(Location location) {
        /*LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15.0f);

        if (marker != null) {
            marker.remove();
            MAP_LATLANG.remove(MY_LOCATION);
        }
        MAP_LATLANG.put(MY_LOCATION, latLng);

        if (MAP_LATLANG.size() > 1) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (LatLng latLngBound : MAP_LATLANG.values()) {
                builder.include(latLngBound);
            }
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
        } else {
            mMap.moveCamera(cameraUpdate);
        }

        marker = mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_my_location_marker)));
        */

        location.reset();
        _StopLocationRequest();
    }

    @Override
    public void onConnected(Bundle bundle) {
        _StartLocationRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {
        /**
         * Nothing goes here.
         */
    }

    public LocationRequest _CreateLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(GrabMConstant.INTERVAL);
        locationRequest.setFastestInterval(GrabMConstant.FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }

    public void _StartLocationRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest)
                .setAlwaysShow(true);
        final PendingResult<LocationSettingsResult> results = LocationServices.SettingsApi
                .checkLocationSettings(mGoogleLocationApiClient, builder.build());
        results.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        if (ContextCompat.checkSelfPermission(GrabMainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleLocationApiClient, mLocationRequest,
                                    GrabMainActivity.this);
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(GrabMainActivity.this,
                                    RESOLUTION_ACTIVITY_CODE);
                        } catch (IntentSender.SendIntentException ex) {
                            /**
                             * Ignore the exception.
                             */
                        }
                        break;
                }
            }
        });
    }

    public void _StopLocationRequest() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleLocationApiClient, this);
    }

    public void expand() {
        mView.setVisibility(View.VISIBLE);
        mView.setAlpha(0.0f);
        mView.animate().alpha(1.0f)
                .setDuration(300);

        final ResizeAnimation resizeAnimation = new ResizeAnimation(mBottomSheetTopBar, 0);
        resizeAnimation.setDuration(200);

        mBottomSheetTopBar.startAnimation(resizeAnimation);
    }

    public void collapse() {
        mView.setVisibility(View.GONE);
        mView.setAlpha(1.0f);
        mView.animate().alpha(0.0f)
                .setDuration(300);

        final ResizeAnimation resizeAnimation = new ResizeAnimation(mBottomSheetTopBar, 240);
        resizeAnimation.setDuration(300);

        mBottomSheetTopBar.startAnimation(resizeAnimation);
    }

    private final Runnable UPDATE_MAP = new Runnable() {

        @Override
        public void run() {
            while (true) {
                GrabAsyncTask.UpdateCarLocationOnMap mUpdateCarLocationOnMap
                        = new GrabAsyncTask.UpdateCarLocationOnMap(GrabMainActivity.this, mMap);
                mUpdateCarLocationOnMap.execute((Void) null);

                final String memNo = mSharedPreferences.getString(GrabMConstant.KEY_MEMBER_NO,
                        getString(R.string.empty_string));

                GrabAsyncTask.RequestGoogleLocationData mUpdateGoogleLocationData
                        = new GrabAsyncTask.RequestGoogleLocationData(GrabMainActivity.this,
                        pickupLocationValue, vehicleLocationValue);
                mUpdateGoogleLocationData.execute(memNo);

                try {
                    Thread.sleep(GrabMConstant.LOCATION_UPDATE_INTERVAL);
                } catch (InterruptedException ex) {
                    Log.e(TAG, ex.toString());
                }
            }
        }

    };

    public static void showFailedSnackbar() {
        Snackbar snackbar = Snackbar.make(mContentView, R.string.snackbar_server_and_execution_error, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mContentView.getContext(), R.color.error_color));
        view.setMinimumHeight(240);

        snackbar.show();
    }

    public static void showSuccessSnackbar() {
        Snackbar snackbar = Snackbar.make(mContentView, R.string.snackbar_vehicle_request_complete, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(mContentView.getContext(), R.color.card_color));
        view.setMinimumHeight(240);

        snackbar.show();
    }
}
