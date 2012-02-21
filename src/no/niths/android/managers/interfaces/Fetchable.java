package no.niths.android.managers.interfaces;

/**
 * 
 * @author NITHs
 *
 */
public interface Fetchable<T> {

    T[] fetchAll();

    void setData(T[] t);
}