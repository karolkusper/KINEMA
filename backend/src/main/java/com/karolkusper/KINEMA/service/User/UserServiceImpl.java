package com.karolkusper.KINEMA.service.User;

import com.karolkusper.KINEMA.dao.RoleRepository;
import com.karolkusper.KINEMA.dao.UserRepository;
import com.karolkusper.KINEMA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        User user = null;
        Optional<User> result = userRepository.findById(id);

        if(result.isPresent()){
            user=result.get();
        }
        else{
            throw new RuntimeException("Did not find user with this id: "+id);
        }
        return user;
    }

    @Override
    public User save(User user) {

        if (user.getId() == 0) {
            user.setRoles(Arrays.asList(roleRepository.getRoleByName("ROLE_CLIENT")));
            return userRepository.save(user);
        }
        else
        {

            User existingUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));

            existingUser.setUserName(user.getUserName());
            existingUser.setPassword(user.getPassword());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRoles(user.getRoles());

            return userRepository.save(existingUser);
        }
    }


    @Override
    public void deleteById(int id) {

        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userToDelete.getRoles().clear(); // Wyczyść role użytkownika

        userRepository.delete(userToDelete);
    }
}
