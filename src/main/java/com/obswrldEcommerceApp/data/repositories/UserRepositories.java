package com.obswrldEcommerceApp.data.repositories;
import com.obswrldEcommerceApp.data.models.Role;
import com.obswrldEcommerceApp.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepositories extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByRoles(Set<Role> roles);
    boolean existsByEmail(String email);
}
