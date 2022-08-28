package net.lirent.recipemanager.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>
 * The Instruction class is an entity model object, and it describes the steps to prepare the recipe
 *</p>
 *
 * @author Lirent
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INSTRUCTION")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer step;
    @NonNull
    private String text;

    @Override
    public String toString() {
        return "Instruction{" +
                "id=" + id +
                ", step=" + step +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return id.equals(that.id) && Objects.equals(step, that.step) && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, step, text);
    }
}
