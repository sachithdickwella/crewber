package com.earcs.grabm.util.executor;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.earcs.grabm.AuthenticationActivity;
import com.earcs.grabm.GrabMainActivity;
import com.earcs.grabm.R;
import com.earcs.grabm.entity.GoogleDistanceMatrixInfo;
import com.earcs.grabm.entity.LatestTracking;
import com.earcs.grabm.util.AndroidJerseyConfig;
import com.earcs.grabm.util.DialogType;
import com.earcs.grabm.util.GrabMConstant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.glassfish.jersey.client.ClientConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;

/**
 * @author Sachith Dickwella
 */
public abstract class GrabAsyncTask<E, T, V> extends AsyncTask<E, T, V>
        implements GrabMConstant {

    private static final String TAG = "GrabAsyncTask";
    private static final String PROTOCOL = "http://";
    private static final String HOST = "192.168.8.250:8080";//"123.231.8.197";
    private static final String RESOURCE_PATH = "/grabm/rest/gmdresource";//"/rest/gmdresource";
    private static final MultivaluedHashMap<String, Object> HEADERS
            = new MultivaluedHashMap<>(1);
    protected final ExecutorService executor = Executors.newCachedThreadPool();
    protected Context context;
    protected final int TIMEOUT_INTERVAL = 20000;

    public GrabAsyncTask(Context context) {
        this.context = context;
        HEADERS.put("Authorization", Collections.singletonList((Object) "Z3JhYm06R3JhYm1AMjAxNg=="));
    }

    protected final Client getClient() {
        return ClientBuilder.newClient(new ClientConfig()
                .register(AndroidJerseyConfig.class));
    }

    protected final WebTarget getWebTarget(final Client client, final String resPath) {
        return client.target(PROTOCOL + HOST + RESOURCE_PATH + resPath);
    }

    public static class ValidateUser extends GrabAsyncTask<String, Void, Integer> {

        private final Client client;
        private DialogFragment dialogFragment;

        public ValidateUser(Context context) {
            super(context);
            this.client = super.getClient();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogFragment = DialogExecutor.getDialogExceutor((Activity) context, null)
                    .showDialog(DialogType.DIALOG_PLEASEWAIT);
        }

        @Override
        protected Integer doInBackground(final String... params) {
            final String memNo = params[0];
            final Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    try {
                        return getWebTarget(client, "/enduser/validate/member/" + memNo)
                                .request(MediaType.TEXT_PLAIN_TYPE)
                                .headers(HEADERS)
                                .post(null, Integer.class);
                    } catch (Exception ex) {
                        Log.e(ex.getMessage(), ex.toString());
                        return EXECUTION_ERROR;
                    }
                }
            };

            try {
                Future<Integer> userFuture = executor.submit(callable);
                int respond = userFuture.get();
                if (respond == 0) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences(
                            GrabMConstant.FILE_KEY, Context.MODE_PRIVATE);
                    sharedPreferences.edit()
                            .putString(GrabMConstant.KEY_MEMBER_NO, memNo)
                            .apply();
                }
                return respond;
            } catch (InterruptedException | ExecutionException ex) {
                Log.e(TAG, ex.toString());
            } finally {
                if (this.client != null) {
                    this.client.close();
                }
            }
            return -2;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            dialogFragment.dismiss();
            if (result == 0) {
                DialogExecutor.getDialogExceutor((Activity) context, null).showDialog(DialogType.DIALOG_PIN_VALIDATION);
            } else {
                AuthenticationActivity.showSnackbar();
            }
        }
    }

    public static class ValidatePin extends GrabAsyncTask<String, Void, Integer> {

        private final Client client;
        private DialogFragment dialogFragment;

        public ValidatePin(Context context) {
            super(context);
            this.client = super.getClient();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogFragment = DialogExecutor.getDialogExceutor((Activity) context, null)
                    .showDialog(DialogType.DIALOG_PLEASEWAIT);
        }

        @Override
        protected Integer doInBackground(String... params) {
            final String pin = params[0];
            final String memNo = params[1];
            final Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    try {
                        return getWebTarget(client, "/enduser/validate/pin/" + pin)
                                .queryParam("memno", memNo)
                                .request(MediaType.TEXT_PLAIN_TYPE)
                                .headers(HEADERS)
                                .post(null, Integer.class);
                    } catch (Exception ex) {
                        Log.e(ex.getMessage(), ex.toString());
                        return EXECUTION_ERROR;
                    }
                }
            };

            try {
                Future<Integer> userFuture = executor.submit(callable);
                return userFuture.get();
            } catch (InterruptedException | ExecutionException ex) {
                Log.e(TAG, ex.toString());
            } finally {
                if (this.client != null) {
                    this.client.close();
                }
            }
            return -2;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            dialogFragment.dismiss();
            if (result == 0) {
                AuthenticationActivity._LoginType(LoginMethodByte.isGoogle, context);
            } else {
                AuthenticationActivity.showSnackbar();
            }
        }
    }

    public static class SendTokenToServer extends GrabAsyncTask<String, Void, Integer> {

        private final Client client;

        public SendTokenToServer(Context context) {
            super(context);
            this.client = super.getClient();
        }

        @Override
        protected Integer doInBackground(final String... params) {
            final Callable<Integer> callable = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    final String memNo = params[0];
                    final String token = params[1];
                    return getWebTarget(client, "/enduser/token/" + memNo + "?token=" + token)
                            .request(MediaType.TEXT_PLAIN_TYPE)
                            .headers(HEADERS)
                            .post(null).readEntity(Integer.class);
                }
            };

            try {
                Future<Integer> future = executor.submit(callable);
                return future.get();
            } catch (ExecutionException | InterruptedException ex) {
                Log.e(ex.getMessage(), ex.toString());
            }
            return -1;
        }
    }

    public static class RequestCarLocationData extends GrabAsyncTask<String, Void, String> {

        private final Client client;

        public RequestCarLocationData(Context context) {
            super(context);
            this.client = getClient();
        }

        @Override
        public String doInBackground(final String... params) {
            final Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    final String memberId = params[0];
                    try {
                        return getWebTarget(client, "/location/latest/" + memberId)
                                .request()
                                .headers(HEADERS)
                                .accept(MediaType.APPLICATION_JSON_TYPE)
                                .post(null, String.class);
                    } catch (ServiceUnavailableException ex) {
                        Log.e(TAG, ex.toString());
                    }
                    return null;
                }
            };

            try {
                Future<String> future = executor.submit(callable);
                String response = future.get(TIMEOUT_INTERVAL, TimeUnit.MILLISECONDS);
                if (response != null) {
                    return response;
                } else {
                    return null;
                }
            } catch (ExecutionException | InterruptedException | TimeoutException ex) {
                Log.e(TAG, ex.toString());
            } finally {
                client.close();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String latestTracking) {
            super.onPostExecute(latestTracking);
            if (latestTracking != null) {
                try (OutputStream os = context.openFileOutput(FILE_KEY, Context.MODE_PRIVATE)) {

                    os.write(latestTracking.getBytes());
                    os.flush();
                } catch (Exception ex) {
                    Log.e(TAG, ex.toString());
                }
            }
        }
    }

    private static Marker marker = null;

    public static class UpdateCarLocationOnMap extends GrabAsyncTask<Void, Void, Void> {

        private GoogleMap mMap;
        private LatestTracking lt;

        public UpdateCarLocationOnMap(Context context, GoogleMap mMap) {
            super(context);
            this.mMap = mMap;
            File file = new File(context.getFilesDir(), FILE_KEY);
            if (file.exists()) {
                try (InputStream in = context.openFileInput(FILE_KEY)) {
                    byte[] buffer = new byte[in.available()];
                    in.read(buffer);
                    lt = LatestTracking.fromJson(new String(buffer));
                } catch (IOException ex) {
                    Log.e(TAG, ex.toString());
                }
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            GrabTaskHandler.sendTaskHandler(mapUpdateHandler, null);
            return null;
        }

        private final Handler mapUpdateHandler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message message) {
                if (marker != null) {
                    marker.remove();
                    MAP_LATLANG.remove(VEHICLE_LOCATION);
                }
                if (lt != null) {
                    double latitude = lt.getLatitude(),
                            longitude = lt.getLongitude();
                    if (latitude != 0.0D && longitude != 0.0D) {
                        LatLng newPosition = new LatLng(latitude, longitude);

                        if (mMap != null) {
                            if (marker != null) {
                                marker.remove();
                                MAP_LATLANG.remove(VEHICLE_LOCATION);
                            }
                            marker = mMap.addMarker(new MarkerOptions()
                                    .position(newPosition)
                                    .anchor(0.5f, 0.5f)
                                    .icon(BitmapDescriptorFactory
                                            .fromResource(R.mipmap.ic_car_marker_front)));

                            MAP_LATLANG.put(VEHICLE_LOCATION, marker.getPosition());

                            if (MAP_LATLANG.size() > 0) {
                                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                for (LatLng latLngBound : MAP_LATLANG.values()) {
                                    builder.include(latLngBound);
                                }
                                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 200));
                            }

                            //CameraUpdate update = CameraUpdateFactory.newLatLngZoom(newPosition, 16.0f);
                            //mMap.animateCamera(update);
                        }
                    }
                }
            }
        };
    }

    public static class RequestGoogleLocationData extends GrabAsyncTask<String, Void, String> {

        private Client client;
        private final TextView[] textViews;

        public RequestGoogleLocationData(Context context, TextView... textViews) {
            super(context);
            this.client = getClient();
            this.textViews = textViews;
        }

        @Override
        protected String doInBackground(final String... params) {
            final Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    final String memberId = params[0];
                    try {
                        return getWebTarget(client, "/location/google/" + memberId)
                                .request()
                                .headers(HEADERS)
                                .accept(MediaType.APPLICATION_JSON_TYPE)
                                .post(null, String.class);
                    } catch (ServiceUnavailableException ex) {
                        Log.e(TAG, ex.toString());
                    }
                    return null;
                }
            };

            try {
                Future<String> future = executor.submit(callable);
                String response = future.get(TIMEOUT_INTERVAL, TimeUnit.MILLISECONDS);
                if (response != null) {
                    return response;
                } else {
                    return null;
                }
            } catch (ExecutionException | InterruptedException | TimeoutException ex) {
                Log.e(TAG, ex.toString());
            } finally {
                client.close();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String response) {
            super.onPostExecute(response);
            final GoogleDistanceMatrixInfo info = GoogleDistanceMatrixInfo.fromJson(response);

            final Handler handler = new Handler(Looper.getMainLooper()) {

                @Override
                public void handleMessage(Message message) {
                    if (info != null) {
                        textViews[0].setText(info.getDestination());
                        textViews[1].setText(info.getOrigin()
                                + "\nETA: " + info.getDuration() + ", Distance: " + info.getDistance());
                    }
                }
            };

            GrabTaskHandler.sendTaskHandler(handler, null);
        }
    }

    public static class RequestVehicle extends GrabAsyncTask<Void, Void, Void> {

        private DialogFragment dialogFragment;

        public RequestVehicle(Context context) {
            super(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogFragment = DialogExecutor.getDialogExceutor((Activity) context, null)
                    .showDialog(DialogType.DIALOG_PLEASEWAIT);
        }

        @Override
        protected Void doInBackground(Void... params) {
            /**
             * TODO code goes here.
             */
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Log.e(TAG, ex.toString());
                GrabMainActivity.showFailedSnackbar();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (dialogFragment != null) {
                dialogFragment.dismiss();
            }
            GrabMainActivity.showSuccessSnackbar();
        }
    }
}
