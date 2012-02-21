package no.niths.android.domains;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    public static final String PATH = "/students";

    private Long id;

    private Character gender;
}