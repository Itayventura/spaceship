package com.itayventura.spaceship;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {
    private final SpaceshipRepository repository;

    @PostConstruct
    public void postConstruct(){
        repository.saveAll(List.of(
                new Spaceship(null, "Falcon", 10),
                new Spaceship(null, "Blackbird", 30),
                new Spaceship(null, "Hawk", 20),
                new Spaceship(null, "Sourcer", 100),
                new Spaceship(null, "Eagler", 90)
                ));
    }
}
