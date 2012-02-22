package no.niths.android.domains;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author whirlwin
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subject extends Domain {
    private Long id;

    private String topicCode;

    private String name;

    private String description;

    private String startTime;
    
    private String endTime;

    private Integer roomNumber;
    
    private String weekday;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getTopicCode() {
        return topicCode;
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


    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeekday() {
        return weekday;
    }

    @Override
    public String toString() {
        return name;
    }
}