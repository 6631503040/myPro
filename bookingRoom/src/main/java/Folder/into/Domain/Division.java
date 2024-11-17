package Folder.into.Domain;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long divisionId;

    private String divisionName;
}
