package mpp.server.classes;

import mpp.domain.entities.User;
import mpp.repo.repositories.UserRepository;
import mpp.services.interfaces.Observer;
import mpp.services.interfaces.Services;

import java.util.HashMap;
import java.util.Map;

public class ServicesImplementation implements Services {
    private UserRepository userRepository;
    private Map<String, Observer> loggedUsers = new HashMap<>();

    public ServicesImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Object login(String username, String pass/*, Observer observer*/) throws Exception {
        if (loggedUsers.containsKey(username)) {
            throw new Exception("User already logged in");
        }

        User user = userRepository.findPlayer(username, pass);
        loggedUsers.put(username, null /*observer*/);
        return user;
    }
}
