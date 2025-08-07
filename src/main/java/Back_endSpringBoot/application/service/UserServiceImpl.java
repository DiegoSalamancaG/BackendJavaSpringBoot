package Back_endSpringBoot.application.service;

import Back_endSpringBoot.application.dto.user.UserDTO;
import Back_endSpringBoot.application.dto.user.UserResponseDTO;
import Back_endSpringBoot.application.mapper.UserMapper;
import Back_endSpringBoot.domain.model.User;
import Back_endSpringBoot.domain.repository.UserRepository;
import Back_endSpringBoot.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    //Métodos
    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findByActiveTrue()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> postUser(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email ya registrado!");
        }
        if (userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new IllegalArgumentException("Nombre de usuario ya registrado!");
        }

        User user = userMapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return Optional.of(userMapper.toResponseDTO(userRepository.save(user)));
    }

    @Override
    public Optional<UserResponseDTO> updateUser(Long id, UserDTO dto) {
        return userRepository.findById(id).map(user -> {
            userMapper.updateFromDto(dto,user);
            if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }

            User userUpdated = userRepository.save(user);
            return userMapper.toResponseDTO(userUpdated);
        });
    }

    @Override
    public Boolean deleteUser(Long id) {
        return userRepository.findById(id).map(user -> {
            user.setActive(false);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }
}
