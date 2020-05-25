package Auth.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    @Id
    private Long id;

    @Column
    private String name;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Collection<User> users;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
