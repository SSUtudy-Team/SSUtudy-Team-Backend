package SSU.SSUtudyWith.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CategoryStudy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_study_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    @Enumerated(EnumType.STRING)
    private Category category;


    //--연관관계 메소드--//
    public void setStudy(Study study) {
        this.study = study;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryStudy(Study study, Category category) {
        setStudy(study);
        setCategory(category);
    }
}
