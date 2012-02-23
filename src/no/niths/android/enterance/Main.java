package no.niths.android.enterance;

import java.io.IOException;

import no.niths.android.R;
import no.niths.android.common.AppController;
import no.niths.android.config.TokenConfig;
import no.niths.android.exceptions.NoNITHMailFoundException;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 
 * @author NITHs
 *
 */
public class Main extends Activity {
    private AccountController accountController;
    private ProgressDialog progressDialog;
    private int tokenCount;

    /**
     * Only the very basic information will be retrieved
     */
    private final String AUTH_TYPE=
            "oauth2:https://www.googleapis.com/auth/userinfo.email",

            /**
             * The URL which will handle the token sent 
             */
            SERVER_URL = "http://10.0.2.2:8080/niths/google";

    /**
     * The form data token field 
     */
    private final String TOKEN_FIELD = "token";

    /**
     * @param Bundle the bundle which was provided through this method
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configure();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tokenCount = 0;
    }

    /**
     * Sets up the handlers
     */
    private void configure() {
        setContentView(R.layout.main);

        accountController = new AccountController(this);

        Button btnAccountManager = (Button) findViewById(
                R.id.btnAccountManager);
        btnAccountManager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_SYNC_SETTINGS));
            }
        });

        Button btnAuthenticate = (Button) findViewById(R.id.btnAuthenticate);
        btnAuthenticate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (accountController.checkAccounts()) {
                    processToken();
                    }
                } catch (NoNITHMailFoundException e) {
                    Log.e(getString(R.string.no_nith_email), e.getMessage());
                    Toast.makeText(Main.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    

    



    private void processToken() {
        progressDialog = ProgressDialog.show(this, getString(R.string.waiting),
                getString(R.string.waiting_for_auth));
        getToken();
    }

    /**
     * Gets the token from Google
     */
    private void getToken() {
        accountController.refreshToken();

        accountController.getAccountManager().getAuthToken(
                accountController.getAccount(), AUTH_TYPE, true,
                new AccountManagerCallback<Bundle>() {

            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    accountController.setAccessToken(
                            future.getResult().getString(
                                    AccountManager.KEY_AUTHTOKEN));
                } catch (OperationCanceledException e) {
                    Log.e(getString(R.string.transport_error),
                            e.getMessage());
                } catch (AuthenticatorException e) {
                    Log.e(getString(R.string.transport_error),
                            e.getMessage());
                } catch (IOException e) {
                    Log.e(getString(R.string.transport_error),
                            e.getMessage());
                }

                // Call this again after callback to ensure the newest token
                // is fetched
                getToken();

                if (++tokenCount == 2) {
                    processPostAction();
                }
            }
            
        }, null);
    }

    /**
     * To do after everything is done
     */
    private void processPostAction() {
        progressDialog.dismiss();

        // TODO Remove ASAP
           Log.i(TokenConfig.HEADER_NAME.toString(),
                   AppController.token);

           // Sends the user to the main menu
           startActivity(new Intent(Main.this, MainMenu.class));
    }
}