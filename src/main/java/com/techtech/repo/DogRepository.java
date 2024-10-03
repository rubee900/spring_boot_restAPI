package com.techtech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtech.enitity.DogEntity;

@Repository
public interface DogRepository extends JpaRepository<DogEntity, Integer>{

}
