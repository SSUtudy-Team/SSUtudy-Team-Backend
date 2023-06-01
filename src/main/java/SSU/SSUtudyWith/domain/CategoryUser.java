package SSU.SSUtudyWith.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
public class CategoryUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;


    //--연관관계 메소드--//
    public void setStudy(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryUser(User user, Category category) {
        setStudy(user);
        setCategory(category);
    }

}
