package SSU.SSUtudyWith.domain;

import lombok.Getter;

@Getter
public enum StudyStatus {
    INVITING("모집중"),
    COMPLETE("모집완료"),
    END("종료됌");

    private final String status;

    StudyStatus(String status) {
        this.status = status;
    }
}
