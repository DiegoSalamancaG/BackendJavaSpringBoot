package Back_endSpringBoot.domain.service;

import Back_endSpringBoot.application.dto.UserDTO;
import Back_endSpringBoot.application.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponseDTO> getAllUsers();
    Optional<UserResponseDTO> getUserById(Long id);
    Optional<UserResponseDTO> postUser(UserDTO dto);
    Optional<UserResponseDTO> updateUser(Long id,UserDTO dto);
    Boolean deleteUser(Long id);
}
