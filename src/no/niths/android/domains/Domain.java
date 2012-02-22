package no.niths.android.domains;

import java.io.Serializable;

/**
 * 
 * @author NITHs
 *
 */
public abstract class Domain implements Serializable {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}