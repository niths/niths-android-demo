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
    NITHS_URL("http://ec2-46-137-44-111.eu-west-1.compute.amazonaws.com:8181/niths/");

    private String url;


    ServerURL(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}