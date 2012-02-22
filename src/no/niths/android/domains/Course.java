package no.niths.android.domains;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course extends Domain {
    public static final String PATH = "/courses";
    
    private Long id;

    private String name;

    private Integer grade;

    private String term;

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

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getInformation() {
        return String.format("%d\n%s\n%d\n%s\n%s",
                id, name, grade, term, description);
    }
    
    @Override
    public String toString() {
        return name;
    }
}