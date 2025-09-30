package com.example.aiagent.repository;

import com.example.aiagent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserIdAndRole(String userId, String role);
    Optional<User> findByUserId(String userId);
    List<User> findByCollegeAndClassNameAndAdmissionYearAndSchoolYearSystem(
            String college, String className, String admissionYear, String schoolYearSystem);
}