package Folder.into.Domain;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.time.LocalDate;

@Getter
@Setter

@Entity
public class BookingData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long refId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "reqUsersId",referencedColumnName = "usersId")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId",referencedColumnName = "roomId")
    private Room room;

    private LocalDate dateReq;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    private LocalTime timeStart;
    private LocalTime timeEnd;

    private String topic;

    @ManyToOne(fetch =  FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacherName", referencedColumnName = "usersName")
    private Users teacherName;


    @ManyToOne(fetch =  FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "directorName", referencedColumnName = "usersName")
    private Users directorName;

    private LocalDate appDateTeacher;
    private LocalDate appDateHead;
    private LocalDate appDateDirector;

    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public BookStatus bookStatus;

}
