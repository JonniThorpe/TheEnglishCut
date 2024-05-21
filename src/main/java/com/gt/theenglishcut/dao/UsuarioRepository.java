package com.gt.theenglishcut.dao;

import com.gt.theenglishcut.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.nombre = :user")
    public Usuario findByNombreUser (@Param("user") String user);

}
