package com.earcs.grabm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.earcs.grabm.entity.User;
import com.earcs.grabm.util.DialogType;
import com.earcs.grabm.util.GrabMConstant;
import com.earcs.grabm.util.NetConnectionStatus;
import com.earcs.grabm.util.executor.DialogExecutor;
import com.earcs.grabm.util.executor.GrabAsyncTask;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getClass().getName();
    static final int SPLASH_TIMEOUT = 5000;
    private static Context sharedContext;

    public static Context getSharedContext() {
        return sharedContext;
    }

    private static SharedPreferences mSharedPreferences;
    private static GoogleApiClient mGoogleApiClient;
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        GrabMConstant.mHideHandler.postDelayed(mHidePart2Runnable, GrabMConstant.UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        GrabMConstant.mHideHandler.removeCallbacks(mHideRunnable);
        GrabMConstant.mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    public static GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        sharedContext = SplashActivity.this;

        mContentView = findViewById(R.id.fullscreen_content_spalsh);
        getWindow().getDecorView()
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            delayedHide(GrabMConstant.AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        final GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile().requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(SplashActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mSharedPreferences = getSharedPreferences(GrabMConstant.FILE_KEY, MODE_PRIVATE);
        startService(GrabMConstant.LocationService.getIntent(this));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(GrabMConstant.AUTO_HIDE_INIT_DELAY_MILLIS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetConnectionStatus.isConnected(this)) {
            DialogExecutor.getDialogExceutor(this, null).showDialog(DialogType.DIALOG_CONNECTION_INIT);
        } else {
            final SplashRunner runner = new SplashRunner(SplashActivity.this);
            runner.execute((Void) null);
        }
    }

    public static class SplashRunner extends GrabAsyncTask<Void, Void, Void> {

        public SplashRunner(Context context) {
            super(context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            final Map<String, ?> allSharedPreferences = mSharedPreferences.getAll();
            if (allSharedPreferences.get(GrabMConstant.KEY_LOGIN_STATUS) != null
                    && allSharedPreferences.get(GrabMConstant.KEY_LOGIN_STATUS)
                    .equals(GrabMConstant.LoginStatus.LOGIN.toString())) {

                final User user = new User();
                user.setName(allSharedPreferences.get(GrabMConstant.KEY_NAME).toString());
                user.setEmail(allSharedPreferences.get(GrabMConstant.KEY_EMAIL).toString());

                if (allSharedPreferences.get(GrabMConstant.KEY_LOGIN_BY)
                        .equals(GrabMConstant.LoginMethod.BY_TWITTER.toString())) {
                    AuthenticationActivity.mHandleSignInResult(null, user, context);
                } else {
                    final OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
                    if (opr.isDone()) {
                        GoogleSignInResult googleSignInResult = opr.get();
                        AuthenticationActivity.mHandleSignInResult(googleSignInResult, null, context);
                    } else {
                        opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                            @Override
                            public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                                AuthenticationActivity.mHandleSignInResult(googleSignInResult, null, context);
                            }
                        });
                    }
                }
            } else {
                try {
                    Intent authIntent = new Intent(context, AuthenticationActivity.class);
                    Thread.sleep(SPLASH_TIMEOUT);
                    context.startActivity(authIntent);
                    ((Activity) context).finish();
                } catch (InterruptedException ex) {
                    Log.e(TAG, ex.toString());
                }
            }
            return null;
        }
    }
}
