package no.niths.android.domains;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author NITHs
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course extends Domain {
    private Long id;

    private String name;

    private String description;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}