package privacy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @Builder
    public Member(Long id, String email, String name, String password) {
        this.id = id;
        this.email=email;
        this.name=name;
        this.password=password;
    }

    public void update(String email, String name, String password) {
        this.email=email;
        this.name=name;
        this.password=password;
    }
}
