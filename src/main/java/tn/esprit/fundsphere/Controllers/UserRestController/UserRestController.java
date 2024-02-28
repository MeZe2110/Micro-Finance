package tn.esprit.fundsphere.Controllers.UserRestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Services.UserService.UserServicelmlp;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestController {




        public UserServicelmlp usersService;

        @PostMapping(path = "/add-users")
        public User addClaims (@RequestBody User users)
        {
            return usersService.addUsers(users);
        }

        @GetMapping("/show-users")
        public List<User> getAllUsers() {
            List<User> listUsers = usersService.getAllUsers();
            return listUsers;
        }
        @DeleteMapping(path = "/delete-users/{id}")
        public void deleteClaims (@PathVariable ("id") Long idUser)
        {
            usersService.deleteUsers(idUser);
        }


        @PutMapping(path = "/update-users")
        public User updateBloc(@RequestBody User u)
        {

            return usersService.updateUsers(u);
        }
}
