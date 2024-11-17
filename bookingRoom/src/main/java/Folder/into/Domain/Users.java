package Folder.into.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersId;

    private String usersName;
    private String usersPassword;
    private String usersMail;
    private String usersPhone;
    private String token;

    @ManyToOne
    @JoinColumn(name = "divisionId",referencedColumnName = "divisionId")
    private Division division;

    @Enumerated(EnumType.STRING)
    @Column(name = "usersType", nullable = false)
    private UsersType usersType;

}
