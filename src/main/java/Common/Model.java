package Common;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.io.Serial;
import java.io.Serializable;

/**
 * Base class for entity models.
 */
@MappedSuperclass
public abstract class Model implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    @Id
    @Column(name = "id", updatable = false)
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Gets the unique identifier of the entity.
     *
     * @return The unique identifier.
     */
    public Long getId() {
        return id;
    }
}
