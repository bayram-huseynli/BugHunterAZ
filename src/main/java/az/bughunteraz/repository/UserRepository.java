package az.bughunteraz.repository;

import az.bughunteraz.entity.Role;
import az.bughunteraz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRoleAndEmailContainingIgnoreCaseOrRoleAndNameContainingIgnoreCase(Role role,
                                                                                       String keyword,
                                                                                       Role role1,
                                                                                       String keyword1);

    List<User> findByRole(Role role);

    Optional<User> findByResetToken(String token);
}
