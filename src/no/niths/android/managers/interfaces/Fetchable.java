package no.niths.android.managers.interfaces;

/**
 * 
 * @author NITHs
 *
 */
public interface Fetchable<T> {

    /**
     * Fetches all of the given domain
     */
    void fetchAll();

    /**
     * 
     * @param t
     */
    void setData(T[] t);
}