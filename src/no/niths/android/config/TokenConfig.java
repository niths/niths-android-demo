package no.niths.android.config;

/**
 * 
 * @author NITHs
 *
 */
public enum TokenConfig {
    HEADER_NAME("Token");

    private String value;

    TokenConfig(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}