package com.BlogPost.BlogPost_Management.Repository;

import com.BlogPost.BlogPost_Management.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    @Query(value="select * from Users where username=:name",nativeQuery = true)
    List<Users> findByName(@Param("name") String name);
}
