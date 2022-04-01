package com.carros.repository;

import com.carros.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CarrroRepository extends JpaRepository<Carro, Integer> {


    @Transactional(readOnly=true)
    @Query("select c from Carro c where c.marca like %:marca%")
    Carro findByMarca(@Param("marca") String marca);
}
