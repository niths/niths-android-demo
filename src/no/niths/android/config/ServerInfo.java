package no.niths.android.config;

/**
 * 
 * @author NITHs
 *
 */
public enum ServerInfo {
    LOCAL_URL("http://10.0.2.2:8080/niths"),
    NITHS_URL("");

    private String data;

    ServerInfo(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
