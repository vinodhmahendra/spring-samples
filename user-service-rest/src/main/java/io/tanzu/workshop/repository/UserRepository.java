package io.tanzu.workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.tanzu.workshop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
