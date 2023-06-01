package SSU.SSUtudyWith.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Subject {

    private String college;

    private String department;

    private String className;

    protected Subject() {

    }

    public Subject(String college, String department, String className) {
        this.college = college;
        this.department = department;
        this.className = className;
    }
}
