package forwork.human_resources.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import forwork.human_resources.repositores.UserRepository;
import forwork.human_resources.models.Users;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/all")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users creatNewUser(@RequestBody Users user){
        
        Users createUser = new Users();
        createUser.setUsername(user.getUsername());
        createUser.setUseremail(user.getUseremail());
        createUser.setPassword(user.getPassword());
        createUser.setProfile(user.getProfile());
        createUser.setAvatar(user.getAvatar());

        return userRepository.save(createUser);
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users updateUsers(@RequestBody Users user){
        
        Users getUser = userRepository.findById(user.getId()).orElseThrow();

        Users updateUser = new Users();
        updateUser.setId(getUser.getId());
        updateUser.setUsername(user.getUsername());
        updateUser.setUseremail(user.getUseremail());
        updateUser.setPassword(user.getPassword());
        updateUser.setProfile(user.getProfile());
        updateUser.setAvatar(user.getAvatar());

        return userRepository.save(updateUser);
    }

    @DeleteMapping(value="/delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Users deleteUser(@PathVariable Long id){
        Users getUser = userRepository.findById(id).orElseThrow();
        userRepository.delete(getUser);
        return getUser;
    }
}