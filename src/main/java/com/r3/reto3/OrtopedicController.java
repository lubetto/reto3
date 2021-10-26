package com.r3.reto3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Ortopedic")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class OrtopedicController {

    @Autowired
    OrtopedicService ortopedicService;

    @GetMapping("/all")
    public List<Ortopedic> getOrtopedic() {
        return  ortopedicService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Ortopedic> getOrtopedic(@PathVariable("id")int id) {
        return ortopedicService.getOrtopedic(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Ortopedic save(@RequestBody Ortopedic o) {
        return ortopedicService.save(o);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Ortopedic update(@RequestBody Ortopedic o) {
        return ortopedicService.update(o);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int Id) {
        return ortopedicService.deleteOrtesis(Id);
    }
}
