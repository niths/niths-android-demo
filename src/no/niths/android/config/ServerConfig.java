package no.niths.android.config;

/**
 * 
 * @author NITHs
 *
 */
public enum ServerConfig {

    /**
     * URL for connecting through localhost
     */
    LOCAL_URL("http://10.0.2.2:8080/niths/"),

    /**
     * URL for connecting through the genuine server
     */
    NITHS_URL("http://ec2-46-137-44-111.eu-west-1.compute.amazonaws.com:8181/niths/"),

    /**
     * Server connection timeout
     */
    CONNECTION_TIMEOUT(3000),

    /**
     * Socket timeout
     */
    SO_TIMEOUT(5000);

    private String data;

    private Integer timeout;

    ServerConfig(String data) {
        this.data = data;
    }

    ServerConfig(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getTimeout() {
        return timeout;
    }

    @Override
    public String toString() {
        return data;
    }
}