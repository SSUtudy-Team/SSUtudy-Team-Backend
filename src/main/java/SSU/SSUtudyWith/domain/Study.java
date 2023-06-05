package SSU.SSUtudyWith.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
public class Study {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    @Embedded
    private Subject subject;

    private String title;

    private String content;

    private int userCount; // 최대 참여 인원수

    private String roomLink;  // 오픈톡방 링크

    private LocalDateTime madeTime; // 스터디 방 만들어진 시간
    @Enumerated(EnumType.STRING)
    private StudyStatus status; // 모집중, 모집완료, 종료 [INVITING, COMPLETE, END]

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;  // 스터디 방 만든 유저
    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<CategoryStudy> categoryStudies = new ArrayList<>();  // 해당 스터디가 가지고 있는 카테고리

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Participation> participations = new ArrayList<>();  // 해당 스터디에 참여한 유저


    //--연관관계 메소드-//
    public void setUser(User user) {
        this.user = user;
        user.getStudies().add(this);
    }


    //--생성자--//
    protected Study() {

    }

    public Study(Subject subject, String title, String content, int userCount, String roomLink, User user) {
        this.subject = subject;
        this.title = title;
        this.content = content;
        this.userCount = userCount;
        this.roomLink = roomLink;
        this.madeTime = LocalDateTime.now();
        this.status = StudyStatus.INVITING;
        this.user = user;
    }


    public void changeStatus(StudyStatus status) {
        this.status = status;
    }

}

