package SSU.SSUtudyWith.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String studentId;


    private String password;

    private String name;

    private int grade;

    private String department;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Study> studies = new ArrayList<>();  // 내가 만든 스터디 목록

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Participation> participations = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CategoryUser> categoryUsers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @NotNull
    private List<String> roles = new ArrayList<>();


    //--생성자--//
    @Builder
    public User(String studentId, String password, String name, int grade, String department, List<String> roles) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
        this.roles = roles;
    }

    //--업데이트 메소드--//
    @Builder
    public void updateUser(String password, String name, int grade, String department) {
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.department = department;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

