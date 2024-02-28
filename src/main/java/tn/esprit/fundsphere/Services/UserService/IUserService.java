package tn.esprit.fundsphere.Services.UserService;


import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.util.List;

public interface IUserService {
    public User addUsers(User users);
    public void deleteUsers(Long idUser);
    public User updateUsers(User users);
    public List<User> getAllUsers();
    public User getCUser(Long idUser);
}
