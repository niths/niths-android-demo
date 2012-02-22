package no.niths.android.config;

/**
 * 
 * @author NITHs
 *
 */
public enum ServerURL {

    /**
     * URL for connecting through localhost
     */
    LOCAL_URL("http://10.0.2.2:8080/niths/"),

    /**
     * URL for connecting through the genuine server
     */
    NITHS_URL("");

    private String data;

    ServerURL(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}