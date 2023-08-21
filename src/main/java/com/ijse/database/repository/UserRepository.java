package com.ijse.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijse.database.entity.User;

//Spring boot framework provides us repository which is responsible to perform various operations on the object.
//Jpa Repository contains the APIs for basic CRUD operations, the APIS for pagination, and the APIs for sorting.

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //can add custom queries here.
}
