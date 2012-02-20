package no.niths;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
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
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    private GoogleAccessProtectedResource resource =
            new GoogleAccessProtectedResource(null);
    private Account account;
    private boolean hasAccount;

    /**
     * @param Bundle the bundle which was provided through this method
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GoogleAccountManager googleAccountManager =
                new GoogleAccountManager(this);
        
        try {
            checkAccounts(googleAccountManager.getAccounts());
        } catch (NoNITHMailFoundException e) {
            Button btnLogin = (Button) findViewById(R.id.btnLogin);
            btnLogin.setVisibility(Button.VISIBLE);
            btnLogin.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                            "http://almondmendoza.com/android-applications/")));
                }
            });
        }
        
        if (true) return;

        final String authType =
                "oauth2:https://www.googleapis.com/auth/userinfo.email";
         
        googleAccountManager.manager.getAuthToken(account, authType, true,
                new AccountManagerCallback<Bundle>() {
                    public void run(AccountManagerFuture<Bundle> arg0) {
                        try {  
                            resource.setAccessToken(arg0.getResult().getString(AccountManager.KEY_AUTHTOKEN));
                            String token = resource.getAccessToken();
                            Toast.makeText(Main.this, "token -> " + token, Toast.LENGTH_LONG).show();
                            Log.i("token:", "" + token);

                            HttpClient client = new DefaultHttpClient();
                            HttpPost post = new HttpPost(
                                    "http://10.0.2.2:8080/niths/auth-user-token");
                            List<NameValuePair> data =
                                    new ArrayList<NameValuePair>();
                            data.add(new BasicNameValuePair("token", token));
                            post.setEntity(new UrlEncodedFormEntity(data));
                            
                            org.apache.http.HttpResponse response = client.execute(post);
                            
                            
                            
                            
                        } catch (OperationCanceledException e) {
                            Log.e("errs", e.getMessage());
                            e.printStackTrace();
                        } catch (AuthenticatorException e) {
                            Log.e("errs", e.getMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.e("errs", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("foo", "resume");
    }
    
    @Override
    public void onRestart() {
        super.onRestart();
        Log.e("foo", "restart");
    }
    
    private void configure() {
        
    }

    private  void checkAccounts(Account[] accounts) throws NoNITHMailFoundException {
        ArrayList<Account> nithAccounts = new ArrayList<Account>();
        
        for (Account account : accounts) {
            if (account.name.matches("^\\w+@nith.no$")) {
                nithAccounts.add(account);
            }
        }

        final int size = nithAccounts.size();

        if (size > 1) {
            promptAccount(nithAccounts);
        } else if (size == 1) {
            account = nithAccounts.get(0);
        } else {
            throw new NoNITHMailFoundException();
        }
    }

    /**
     * 
     * @param String[] The accounts to choose from
     * @return The name which was chosen
     */
    public void promptAccount(final ArrayList<Account> nithAccounts) {
        final String[] nithAccountNames = (String[]) nithAccounts.toArray();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.choose_account);
        builder.setItems(nithAccountNames, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                for (String accountName : nithAccountNames) {
                    if (accountName.equals(nithAccountNames[which])) {
                        account = nithAccounts.get(which);
                    }
                }
            }
        });
        builder.create();
    }

    public OnClickListener o = new OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            
        }
    };
}