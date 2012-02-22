package no.niths.android.domains;

/**
 * 
 * @author NITHs
 *
 */
public abstract class Domain {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return String.format("%d\n%s", id, name);
    }
}