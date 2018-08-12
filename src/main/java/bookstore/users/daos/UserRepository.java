package bookstore.users.daos;


import bookstore.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where  u.email = :email ")
    List<User> findAllByEmail(String email);


}
