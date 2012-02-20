package no.niths;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

public class Main extends Activity {
    
    GoogleAccessProtectedResource resource = new GoogleAccessProtectedResource(null);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GoogleAccountManager googleAccountManager = new GoogleAccountManager(this);
        Account account = googleAccountManager.getAccountByName("degyvi09@nith.no");
         
        String authType = "oauth2:https://www.googleapis.com/auth/userinfo.email";
         
        googleAccountManager.manager.getAuthToken(account, authType, true,
                new AccountManagerCallback<Bundle>() {
                    
                    public void run(AccountManagerFuture<Bundle> arg0) {
                        try {
                            resource.setAccessToken(arg0.getResult().getString(AccountManager.KEY_AUTHTOKEN));
                            String token = resource.getAccessToken();
                            
                            Toast.makeText(Main.this, "token -> " + token, Toast.LENGTH_LONG).show();
                        } catch (OperationCanceledException e) {
                            e.printStackTrace();
                        } catch (AuthenticatorException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, null);
    }
}