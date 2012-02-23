package no.niths.android.config;

/**
 * 
 * @author NITHs
 *
 */
public enum TokenConfig {

    /**
     * General purpose String used to identify key / text - value pairs
     */
    NAME("Token"),

    /**
     * The provider whom to fetch the token from
     */
    SOURCE("oauth2:https://www.googleapis.com/auth/userinfo.email");

    private String value;

    TokenConfig(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}