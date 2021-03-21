package com.itayventura.spaceship;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/spaceship")
public class SpaceshipController {
    private final SpaceshipRepository repository;

    @GetMapping("/")
    public String getSpaceships(Model model, HttpServletRequest request){
        List<Spaceship> spaceships = repository.findAll();
        model.addAttribute("spaceships",spaceships);
        model.addAttribute("isManager", request.isUserInRole("ROLE_MANAGER"));
        return "spaceship";
    }

    @GetMapping("/new")
    public String newSpaceship(Model model){
        model.addAttribute("spaceship", new Spaceship());
        return "newSpaceshipForm";
    }

    @PostMapping("/new")
    public String createSpaceship(@Valid @ModelAttribute("spaceship") Spaceship spaceship,
                                  BindingResult bindingResult, Model model, HttpServletRequest request){
        if (spaceship.getName().startsWith("F"))
            bindingResult.addError(new FieldError("spaceship", "name", "We dont want " +
                    "spaceships starting with an F!!!"));
        if (bindingResult.hasErrors()){
            return "newSpaceshipForm";
        }
        this.repository.save(spaceship);
        return getSpaceships(model, request);
    }

    @GetMapping("/delete/{id}")
    public String deleteSpaceship(@PathVariable("id") Integer id, Model model, HttpServletRequest request){
        this.repository.deleteById(id);
        return getSpaceships(model, request);
    }

}
