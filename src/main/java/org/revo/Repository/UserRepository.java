package org.revo.Repository;

import org.revo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by ashraf on 18/01/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select new User(u.id,u.name,u.imageUrl,u.phone,u.info,u.email,u.createdDate,u.password,u.enable,u.locked) from User u where u.email=?1")
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User set enable=true where id=?1")
    void setActiveTrueById(Long id);
}