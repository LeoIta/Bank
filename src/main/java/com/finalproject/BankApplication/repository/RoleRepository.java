package com.finalproject.BankApplication.repository;

<<<<<<< HEAD

=======
>>>>>>> main
import com.finalproject.BankApplication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface RoleRepository extends JpaRepository<Role, Integer> {
=======
public
interface RoleRepository extends JpaRepository<Role, Integer> {
>>>>>>> main
    public Role findByRole(String role);
}
