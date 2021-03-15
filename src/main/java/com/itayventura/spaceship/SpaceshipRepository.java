package com.itayventura.spaceship;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceshipRepository extends CrudRepository<Spaceship, Integer> {
    public List<Spaceship> findAll();
}
