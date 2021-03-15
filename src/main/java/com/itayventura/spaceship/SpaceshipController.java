package com.itayventura.spaceship;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spaceship")
public class SpaceshipController {
    private final SpaceshipRepository repository;

    @GetMapping("/")
    public String getSpaceships(Model model){
        List<Spaceship> spaceships = repository.findAll();
        if (spaceships.size() != 0){
            model.addAttribute("spaceships",spaceships);
        }
        return "spaceship";
    }

}
