package jar.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jar.model.Student;
import jar.service.Dservice;

@RestController
@RequestMapping("/api/v1")
public class Update {

    @Autowired
    private Dservice service;

    @PutMapping("/{id}")
    public Map<Object, Object> update(@PathVariable Integer id, @RequestBody Student d) {
        return service.updateStudent(id, d);
    }
}
