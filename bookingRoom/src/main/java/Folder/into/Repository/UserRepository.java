package Folder.into.Repository;
import Folder.into.Domain.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;



public interface UserRepository extends JpaRepository<Users,Long>{
    Optional<Users> findByUsersName(String usersName);
    Optional<Users> findByUsersId(Long usersId);
    Optional<Users> findByToken(String token);

    

}
