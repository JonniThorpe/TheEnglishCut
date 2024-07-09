package com.theenglishcut.dao;

import com.theenglishcut.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u where u.nombre = :user")
    public UserEntity findByNombreUser (@Param("user") String user);

}
