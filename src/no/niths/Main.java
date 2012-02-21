package no.niths;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

/**
 * 
 * @author NITHs
 *
 */
public class Main extends Activity {

    private GoogleAccessProtectedResource resource;
    private GoogleAccountManager googleAccountManager;
    private Account account;

    /**
     * Only the very basic information will be retrieved
     */
    private final String AUTH_TYPE=
            "oauth2:https://www.googleapis.com/auth/userinfo.email",
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

    /**
     * Sets up the handlers
     */
    private void configure() {
        setContentView(R.layout.main);
        resource = new GoogleAccessProtectedResource(null);
        googleAccountManager = new GoogleAccountManager(this);

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
                    if (checkAccounts(googleAccountManager.getAccounts())) {
                        getToken();
                    }
                } catch (NoNITHMailFoundException e) {
                    Log.e(getString(R.string.no_nith_email), e.getMessage());
                    Toast.makeText(Main.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Checks the accounts on the device
     * @param Account[] The account on the device
     * @return boolean Success
     * @throws NoNITHMailFoundException
     */
    private boolean checkAccounts(Account[] accounts) throws NoNITHMailFoundException {
        ArrayList<Account> nithAccounts = new ArrayList<Account>();
        
        for (Account account : accounts) {
            if (account.name.matches("^\\w+@nith.no$")) {
                nithAccounts.add(account);
            }
        }

        final int size = nithAccounts.size();
        boolean success = false;

        if (size > 1) {
            promptAccount(nithAccounts);
        } else if (size == 1) {
            account = nithAccounts.get(0);
            success = true;
        } else {
            throw new NoNITHMailFoundException();
        }
        
        return success;
    }

    /**
     * 
     * @param String[] The accounts to choose from
     * @return The name which was chosen
     */
    public void promptAccount(final ArrayList<Account> nithAccounts) {
        final String [] nithAccountNames = new String[nithAccounts.size()];
        for (int i = 0; i < nithAccountNames.length; i++) {
            nithAccountNames[i] =nithAccounts.get(i).name;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_account);
        builder.setItems(nithAccountNames, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                for (String accountName : nithAccountNames) {
                    if (accountName.equals(nithAccountNames[which])) {
                        account = nithAccounts.get(which);
                        getToken();
                    }
                }
            }
        });
        builder.create(); 
        builder.show();
    }

    /**
     * Gets the token from Google
     */
    private void getToken() {
        googleAccountManager.manager.getAuthToken(account, AUTH_TYPE, true,
                new AccountManagerCallback<Bundle>() {
                    public void run(AccountManagerFuture<Bundle> future) {
                            try {
                                resource.setAccessToken(future.getResult()
                                        .getString(
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

                            final String token = resource.getAccessToken();

                            if (token == null) {
                                Toast.makeText(Main.this,
                                        R.string.email_error,
                                        Toast.LENGTH_LONG).show();
                            } else {
                                // TODO Remove ASAP
                                Log.i("token:", token);
                                sendToken(token);

                                
                            }

                    }
                }, null);
    }

    /**
     * Sends the token to the server
     * @param String the token to be sent
     */
    private void sendToken(final String token) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(SERVER_URL);
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair(TOKEN_FIELD, token)); 

        try {
            post.setEntity(new UrlEncodedFormEntity(data));
            client.execute(post);

        } catch (UnsupportedEncodingException e) {
            Log.e(getString(R.string.transport_error), e.getMessage());
        } catch (ClientProtocolException e) {
            Log.e(getString(R.string.transport_error), e.getMessage());
        } catch (IOException e) {
            Log.e(getString(R.string.transport_error), e.getMessage());
        }
    }
}