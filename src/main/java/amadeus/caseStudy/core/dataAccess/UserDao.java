package amadeus.caseStudy.core.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import amadeus.caseStudy.core.entities.User;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
	Optional<User>findByEmail(String email);
}
