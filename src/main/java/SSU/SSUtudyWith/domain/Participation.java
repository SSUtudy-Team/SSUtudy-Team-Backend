package SSU.SSUtudyWith.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Participation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    // 생성자 -> 즉 새로운 참여 엔티티가 생성되는 경우

    public Participation(User user, Study study) {
        setUser(user);
        setStudy(study);
    }

    public void setStudy(Study study) {
        this.study = study;
        study.getParticipations().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getParticipations().add(this);
    }

    public void remove(Participation participation) {
        study.getParticipations().remove(participation);
        user.getParticipations().remove(participation);
    }


}
