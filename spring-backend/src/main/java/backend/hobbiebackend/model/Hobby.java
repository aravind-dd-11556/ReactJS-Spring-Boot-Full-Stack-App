package backend.hobbiebackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Hobby {

    @Id
    private long id;
    private String name;

    public Hobby() {}

    public Hobby(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hobby)) return false;
        Hobby hobby = (Hobby) o;
        return id == hobby.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}