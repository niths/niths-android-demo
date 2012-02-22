package no.niths.android.domains;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author NITHs
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends Domain {

    private Long id;

    private String name;

    private String description;

    private String startTime;

    private String endTime;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
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

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return name;
    }
}