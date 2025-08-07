package Back_endSpringBoot.infrastructure.controller;

import Back_endSpringBoot.application.dto.user.UserDTO;
import Back_endSpringBoot.application.dto.user.UserResponseDTO;
import Back_endSpringBoot.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST encargado de manejar operaciones CRUD para usuarios.
 * Todas las rutas requieren que el usuario tenga el rol ADMIN.
 */
@Tag(name = "Usuarios", description = "Endpoints para operaciones administrativas sobre los usuarios")
@RestController
@RequestMapping("/api/v1/users")
//@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Optional<UserResponseDTO>> postUser(@RequestBody UserDTO dto) {
        return ResponseEntity.status(201).body(userService.postUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> putUser(@PathVariable Long id, @RequestBody UserDTO dto){
        return userService.updateUser(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
