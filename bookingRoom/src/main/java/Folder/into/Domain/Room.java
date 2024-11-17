package Folder.into.Domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    private String roomNum;
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "roomStatus")
    private RoomStatus roomStatus;
 
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "buildId",referencedColumnName = "buildId")
    private Build build;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "depId",referencedColumnName = "depId")
    private Department department;
}
