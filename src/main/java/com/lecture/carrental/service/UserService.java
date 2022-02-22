package com.lecture.carrental.service;
import com.lecture.carrental.domain.Role;
import com.lecture.carrental.domain.User;
import com.lecture.carrental.domain.enumeration.UserRole;
import com.lecture.carrental.exception.AuthException;
import com.lecture.carrental.exception.BadRequestException;
import com.lecture.carrental.exception.ConflictException;
import com.lecture.carrental.exception.ResourceNotFoundException;
import com.lecture.carrental.repository.RoleRepository;
import com.lecture.carrental.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final static String USER_NOT_FOUND_MSG = "user with id %d not found";


    public UserDTO findById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
    }

    public void register(User user) throws BadRequestException {

        if (userRepository.existsByEmail(user.getEmail())){
            throw new ConflictException("Error: Email is already in use!");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Set<Role> roles = new HashSet<>();
        Role customerRole = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
        .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));

        roles.add(customerRole);

        user.setRoles(roles);
        userRepository.save(user);                        }

    public void login(String email, String password) throws AuthException {
        try {
            Optional<User> user = userRepository.findByEmail(email);

            if (!BCrypt.checkpw(password, user.get().getPassword()))
                throw new AuthException("invalid credentials");

        }catch (Exception e){
            throw new AuthException("invalid credentials");}
    }
}

