package com.task.api.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.task.api.Model.UserData;

public interface UserManagement_Repo extends JpaRepository<UserData, Integer>{

	Optional<UserData> findByEmail(String email);;

}
