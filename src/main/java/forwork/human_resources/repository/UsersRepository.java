package forwork.human_resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import forwork.human_resources.models.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {    
}