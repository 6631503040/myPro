package Folder.into.Controller;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Folder.into.Domain.Login;
import Folder.into.Domain.Users;
import Folder.into.Repository.UserRepository;

@Controller
public class LoginController {


    @GetMapping("/home")
    public String handlelogin(){
        return "home";
    }
    /*
     * @Autowired
    private UserRepository userrepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login request) {
    Optional<Users> userOptional = userrepo.findByUsersId(request.getUserId());
    if (userOptional.isPresent()) {
        Users user = userOptional.get();

        if (user.getUsersPassword().equals(request.getPassword())) {
            // สร้าง Token 
            String token = UUID.randomUUID().toString();
            
            // เก็บ Token 
            user.setToken(token);
            userrepo.save(user);

            // ส่ง Token กลับไปยัง Client
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unauthorized: Incorrect password", HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
     * 
     */
    
}
