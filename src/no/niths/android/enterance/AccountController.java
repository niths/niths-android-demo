package no.niths.android.enterance;

import java.io.IOException;
import java.util.ArrayList;

import no.niths.android.R;
import no.niths.android.common.AppController;
import no.niths.android.exceptions.NoNITHMailFoundException;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessProtectedResource;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

/**
 * 
 * @author NITHs
 *
 */
public class AccountController {
    private Context context;
    private GoogleAccessProtectedResource googleAccessProtectedResource;
    private GoogleAccountManager googleAccountManager;
    private Account account;

    public AccountController(Context context) {
        this.context = context;
        googleAccessProtectedResource =
                new GoogleAccessProtectedResource(null);
        googleAccountManager = new GoogleAccountManager(context);
    }

    /**
     * Checks the accounts on the device
     * @param Account[] The account on the device
     * @return boolean Success
     * @throws NoNITHMailFoundException
     */
    public boolean checkAccounts()
            throws NoNITHMailFoundException {
        ArrayList<Account> nithAccounts = new ArrayList<Account>();
        
        for (Account account : googleAccountManager.getAccounts()) {
            if (account.name.matches("^\\w+@nith.no$")) {
                nithAccounts.add(account);
            }
        }

        final int size = nithAccounts.size();
        boolean success = false;

        if (account != null) {
            success = true;
        } else if (size > 1) {
            promptAccount(nithAccounts);
        } else if (size == 1) {
            account = nithAccounts.get(0);
            success = true;
        } else {
            throw new NoNITHMailFoundException();
        }
        
        return success;
    }

    public void refreshToken() {
        try { 
            googleAccessProtectedResource.refreshToken();
        } catch (IOException e) {
        }
        googleAccountManager.invalidateAuthToken(AppController.token);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.choose_account);
        builder.setItems(nithAccountNames, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                for (String accountName : nithAccountNames) {
                    if (accountName.equals(nithAccountNames[which])) {
                        account = nithAccounts.get(which);
                        Toast.makeText(context, context.getString(
                                R.string.account_chosen),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        builder.create(); 
        builder.show();
    }

    public void setAccessToken(String accessToken) {
        googleAccessProtectedResource.setAccessToken(accessToken);
        AppController.token = accessToken;
    }
    
    public AccountManager getAccountManager() {
        return googleAccountManager.manager;
    }

    public Account getAccount() {
        return account;
    }
}