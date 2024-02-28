package tn.esprit.fundsphere.Services.UserService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServicelmlp implements IUserService {
    private UserRepository usersRepository;

    @Override
    public User addUsers(User users) {
        return usersRepository.save(users);
    }

    @Override
    public void deleteUsers(Long idUser) {
        usersRepository.deleteById(idUser);
    }

    @Override
    public User updateUsers(User users) {
        return usersRepository.save(users);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getCUser(Long idUser) {
        return usersRepository.findById(idUser).get();    }
}
