package jar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jar.model.Student;
import jar.repo.StudentRepo;

@RestController
@RequestMapping("/api/v1")
public class St {

    @Autowired
    StudentRepo db;
    @GetMapping("/students")
    Map<Object, Object> getStudents() {

        Map<Object, Object> res = new HashMap<>();

        res.put("msg", "Students fetched successfully");
        res.put("status", 200);
        res.put("data", db.findAll());

        return res;
    }

    @PostMapping("/students")
    Map<Object, Object> addStudent(@RequestBody Student d) {
        Map<Object, Object> res = new HashMap<>();
        Student s = new Student();
        s.setName(d.getName());
        s.setEmail(d.getEmail());
        s.setIp(d.getIp());
        db.save(s);
        res.put("msg", "Student added successfully");
        res.put("status", 201);
        res.put("data", s);
        return res;
    }

    @PutMapping("/students/{id}")
    Map<Object, Object> updateStudent(@PathVariable Long id,
            @RequestBody Student d) {
        Map<Object, Object> res = new HashMap<>();
        Optional<Student> data = db.findById(id);
        if (data.isPresent()) {
            Student s = data.get();
            s.setName(d.getName());
            s.setEmail(d.getEmail());
            s.setIp(d.getIp());
            db.save(s);
            res.put("msg", "Student updated successfully");
            res.put("status", 200);
            res.put("data", s);
        } else {

            res.put("msg", "Student not found");
            res.put("status", 404);
        }
        return res;
    }

    @DeleteMapping("/students/{id}")
    Map<Object, Object> deleteStudent(@PathVariable Long id) {
        Map<Object, Object> res = new HashMap<>();
        if (db.existsById(id)) {
            db.deleteById(id);
            res.put("msg", "Student deleted successfully");
            res.put("status", 200);
        } else {
            res.put("msg", "Student not found");
            res.put("status", 404);

        }
        return res;
    }

}