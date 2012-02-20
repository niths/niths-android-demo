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
import android.os.Bundle;
import android.util.Log;
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
                            

                            HttpClient client = new DefaultHttpClient();
                            HttpPost post = new HttpPost(
                                    "http://10.0.2.2:8080/niths/auth-user-token");
                            List<NameValuePair> data =
                                    new ArrayList<NameValuePair>();
                            data.add(new BasicNameValuePair("token", token));
                            post.setEntity(new UrlEncodedFormEntity(data));
                            
                            org.apache.http.HttpResponse response = client.execute(post);
                            
                            Log.i("token:", "" + token);
                            
                            
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
}