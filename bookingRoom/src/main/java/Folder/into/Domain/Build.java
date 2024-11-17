package Folder.into.Domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter

@Entity
@Table(name = "BUILD")
public class Build {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildId;

    private String buildName;
}
