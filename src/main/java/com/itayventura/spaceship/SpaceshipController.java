package com.itayventura.spaceship;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/new")
    public String newSpaceship(Model model){
        model.addAttribute("spaceship", new Spaceship());
        return "newSpaceshipForm";
    }

    @PostMapping("/new")
    public String createSpaceship(@ModelAttribute Spaceship spaceship, Model model, BindingResult bindingResult){
        this.repository.save(spaceship);
        return getSpaceships(model);
    }

}
