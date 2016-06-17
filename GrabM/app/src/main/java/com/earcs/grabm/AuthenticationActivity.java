package com.earcs.grabm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.earcs.grabm.entity.User;
import com.earcs.grabm.util.DialogType;
import com.earcs.grabm.util.GrabMConstant;
import com.earcs.grabm.util.NetConnectionStatus;
import com.earcs.grabm.util.executor.DialogExecutor;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import io.fabric.sdk.android.Fabric;

public class AuthenticationActivity extends AppCompatActivity {

    private static final String TAG = "AuthenticationActivity";
    private static final String ON_TWITTER_KIT_FAILS = "Logging with Twitter failed";

    private static TwitterAuthClient twitterAuthClient;

    private static final int GOOGLE_SIGN_IN = 9001;
    private static GoogleApiClient mGoogleApiClient;

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    private static View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(
                GrabMConstant.TWITTER_CONSUMER_KEY, GrabMConstant.TWITTER_CONSUMER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        twitterAuthClient = new TwitterAuthClient();
        setContentView(R.layout.activity_authentication);

        mContentView = findViewById(R.id.fullscreen_content);

        final Button googleLogin = (Button) findViewById(R.id.googleLogin),
                twitterLogin = (Button) findViewById(R.id.twitterLogin);

        getWindow().getDecorView()
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            delayedHide(GrabMConstant.AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        /**
         * Google Sign-In process goes here.
         */
        mGoogleApiClient = SplashActivity.getGoogleApiClient();
        if (googleLogin != null) {
            googleLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetConnectionStatus.isConnected(AuthenticationActivity.this)) {
                        DialogExecutor.getDialogExceutor(AuthenticationActivity.this, null).showDialog(DialogType.DIALOG_CONNECTION);
                    } else {
                        DialogExecutor.getDialogExceutor(AuthenticationActivity.this, null)
                                .showDialog(DialogType.DIALOG_MEMBERNUMBER_VALIDATION);
                        GrabMConstant.LoginMethodByte.isGoogle = true;
                    }
                }
            });
        }

        if (twitterLogin != null) {
            twitterLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!NetConnectionStatus.isConnected(AuthenticationActivity.this)) {
                        DialogExecutor.getDialogExceutor(AuthenticationActivity.this, null).showDialog(DialogType.DIALOG_CONNECTION);
                    } else {
                        DialogExecutor.getDialogExceutor(AuthenticationActivity.this, null)
                                .showDialog(DialogType.DIALOG_MEMBERNUMBER_VALIDATION);
                        GrabMConstant.LoginMethodByte.isGoogle = false;
                    }
                }
            });
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(GrabMConstant.AUTO_HIDE_INIT_DELAY_MILLIS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GOOGLE_SIGN_IN:
                GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                mHandleSignInResult(googleSignInResult, null, this);
                break;
            default:
                twitterAuthClient.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    public static void showSnackbar() {
        Snackbar snackbar = Snackbar.make(mContentView, R.string.snackbar_identity_validation, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(mContentView.getContext(), R.color.error_color));
        snackbar.show();
    }

    /**
     * Handle GoogleSignInResult object
     */
    public static void mHandleSignInResult(final GoogleSignInResult result, final User user, Context context) {
        if (mGoogleApiClient != null) {
            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.connect();
            }
        }

        final Intent mainIntent = new Intent(context, GrabMainActivity.class);
        if (result != null) {
            if (result.isSuccess()) {
                GoogleSignInAccount mSignInAccount = result.getSignInAccount();
                if (mSignInAccount != null) {
                    mainIntent.putExtra(context.getString(R.string.google_display_name), mSignInAccount.getDisplayName())
                            .putExtra(context.getString(R.string.google_email), mSignInAccount.getEmail())
                            .putExtra(context.getString(R.string.google_photo_uri), mSignInAccount.getPhotoUrl())
                            .putExtra(context.getString(R.string.google_twitter_logged_by),
                                    GrabMConstant.LoginMethod.BY_GOOGLE.toString());

                    if (context instanceof SplashActivity) {
                        try {
                            Thread.sleep(SplashActivity.SPLASH_TIMEOUT);
                        } catch (InterruptedException ex) {
                            Log.e(TAG, ex.toString());
                        }
                    }

                    ((Activity) context).finish();
                    context.startActivity(mainIntent);
                }
            }
        } else {
            Uri uri = null;
            if (user.getImageUrl() != null) {
                uri = Uri.parse(user.getImageUrl());
            }
            mainIntent.putExtra(context.getString(R.string.google_display_name), user.getName());
            mainIntent.putExtra(context.getString(R.string.google_email), user.getEmail());
            mainIntent.putExtra(context.getString(R.string.google_photo_uri), uri);
            mainIntent.putExtra(context.getString(R.string.google_twitter_logged_by),
                    GrabMConstant.LoginMethod.BY_TWITTER.toString());

            if (context instanceof SplashActivity) {
                try {
                    Thread.sleep(SplashActivity.SPLASH_TIMEOUT);
                } catch (InterruptedException ex) {
                    Log.e(TAG, ex.toString());
                }
            }
            ((Activity) context).finish();
            context.startActivity(mainIntent);
        }
    }

    public static void _LoginType(boolean isGoogle, final Context context) {
        if (isGoogle) {
            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.connect();
            }
            Intent googleSignInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            ((Activity) context).startActivityForResult(googleSignInIntent, GOOGLE_SIGN_IN);
        } else {
            final DialogExecutor mDialogExecutor = DialogExecutor
                    .getDialogExceutor((Activity) context, null);

            twitterAuthClient.authorize((Activity) context, new Callback<TwitterSession>() {
                @Override
                public void success(final Result<TwitterSession> sessionResult) {
                                /*
                                 * The TwitterSession is also available through:
                                 * Twitter.getInstance().core.getSessionManager().getActiveSession()
                                 */
                    final TwitterSession session = sessionResult.data;
                    final DialogFragment mDialogPleaseWait = mDialogExecutor
                            .showDialog(DialogType.DIALOG_PLEASEWAIT);

                    final User userLocal = new User();
                    Twitter.getApiClient(sessionResult.data).getAccountService()
                            .verifyCredentials(true, false,
                                    new Callback<com.twitter.sdk.android.core.models.User>() {
                                        @Override
                                        public void success(Result<com.twitter.sdk.android.core.models.User> result) {
                                            com.twitter.sdk.android.core.models.User user = result.data;
                                            userLocal.setName(user.name);
                                            userLocal.setEmail("@" + session.getUserName());
                                            userLocal.setImageUrl(user.profileImageUrl);

                                            mDialogPleaseWait.dismiss();
                                            mHandleSignInResult(null, userLocal, context);
                                        }

                                        @Override
                                        public void failure(TwitterException ex) {
                                            mDialogPleaseWait.dismiss();
                                            mDialogExecutor.showDialog(DialogType.DIALOG_ERROR_LOGIN);
                                            Log.e(TAG, ON_TWITTER_KIT_FAILS, ex);
                                        }
                                    });

                }

                @Override
                public void failure(TwitterException ex) {
                    Log.e(TAG, ON_TWITTER_KIT_FAILS, ex);
                }
            });
        }
    }
}
