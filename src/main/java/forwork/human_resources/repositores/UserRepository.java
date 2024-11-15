package forwork.human_resources.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import forwork.human_resources.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {    
}