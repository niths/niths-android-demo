package no.niths;

/**
 * 
 * @author NITHs
 *
 */
public class NoNITHMailFoundException extends NullPointerException {
    private final String ERROR_MESSAGE = "No NITH emails present";

    /**
     * @return String The error message
     * 
     */
    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }
}