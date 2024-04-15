package Common;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.io.Serial;
import java.io.Serializable;

@MappedSuperclass
public abstract class Model implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId(){
        return id;
    }
}
