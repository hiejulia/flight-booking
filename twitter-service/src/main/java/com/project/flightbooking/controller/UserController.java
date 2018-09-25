
import com.user.dto.Role;
import com.user.dto.User;
import com.user.service.UserService;
import model.RestaurantCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserContoller {

    public static boolean usersAreCreated = false;

    // User Service 
    @Autowired
    UserService userService;

    // region User
    
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/user", produces = "application/json", consumes = "application/json")
    public @ResponseBody Integer addUser(@RequestBody RestaurantCreation restaurantCreation){
        User result = userService.createUser(restaurantCreation.getUsername(), restaurantCreation.getPassword(), restaurantCreation.getRole());
        return result.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'RESTAURANT')")
    @GetMapping(path = "/user/{id}", produces = "application/json")
    public User user(@PathVariable("id") int id){
        return userService.findUser(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/user/delete/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(path = "/users", produces = "application/json", consumes = "application/json")
    public List<User> allUsers(){
//        createUsers();
        List<User> users = userService.getAllUsers();
        return users;
    }
    // endregion

    // region Role
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "/role", produces = "application/json", consumes = "application/json")
    public Integer addRole(@RequestBody model.Role requestBody){
        Role role = userService.createRole(requestBody.getRole());
        return role.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(path = "/roles", produces = "application/json", consumes = "application/json")
    public List<Role> allRoles(){
        return userService.getAllRoles();
    }
    // endregion

}


