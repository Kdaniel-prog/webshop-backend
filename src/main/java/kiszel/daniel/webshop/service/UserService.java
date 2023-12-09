package kiszel.daniel.webshop.service;

import kiszel.daniel.webshop.model.User;
import kiszel.daniel.webshop.repository.AddressRepository;
import kiszel.daniel.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void editUser(User user) {
        userRepository.save(user);
    }
}
