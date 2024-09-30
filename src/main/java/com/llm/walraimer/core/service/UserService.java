package com.llm.walraimer.core.service;

import com.llm.walraimer.core.dto.UserDTO;
import com.llm.walraimer.core.entity.User;
import com.llm.walraimer.core.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    public User create(User user){
        return userRepository.save(user);
    }
    public User update(Long id, User newUser){
        User user = findById(id);
        updateData(user, newUser);
        return userRepository.save(user);
    }
    public void delete(Long id){
        findById(id);
        userRepository.deleteById(id);
    }


    public void updateData(User user, User newUser){
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());
    }

    public User fromDTO(UserDTO dto){
        return new User(dto.name(), dto.email(), dto.phone(), dto.password());
    }

}
