package Folder.into.Domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Other {
    @Id
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "otherId",referencedColumnName = "usersId")
    private Users users;

    @Id
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "refId",referencedColumnName = "refId")
    private BookingData bookingData;
}
