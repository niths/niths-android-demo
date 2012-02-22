package no.niths.android.domains;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course extends Domain implements Serializable {
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

    @Override
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
    public String getInformation() {
        return String.format("%d\n%s\n%s",
                id, name, description);
    }
    
    @Override
    public String toString() {
        return name;
    }
}