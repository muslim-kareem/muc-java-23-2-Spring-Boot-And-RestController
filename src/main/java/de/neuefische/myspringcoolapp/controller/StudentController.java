package de.neuefische.myspringcoolapp.controller;

import de.neuefische.myspringcoolapp.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    List<Student> students = new ArrayList<>(
            List.of( new Student("1","Elias",25),
                    new Student("2","Sahed", 28),
                  new Student("3","Anton",12))
         );


    @GetMapping
    public String hello(@RequestParam String name, int age) {
        return "Hello " + name + " your age is: " + age;

    }

    @GetMapping("/all")
    public List<Student> getStudents(){
        return this.students;
    }

    @GetMapping("{theId}")
    public Student getStudent(@PathVariable String theId){
        for (Student student : this.students) {
            if(student.getId().equals(theId)){
                return student;
            }
        }
        return null;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student theStudent){
         this.students.add(theStudent);
         return theStudent;
    }

    @DeleteMapping("{theId}")
    public List<Student> deleteStudent(@PathVariable String theId){

        for (Student student : this.students) {
            if(student.getId().equals(theId)){
                this.students.remove(student);
            }
        }
        return this.students;
    }

    @PutMapping
    public List<Student> updateStudent(@RequestBody Student jsonStudent){

        for (Student student : this.students) {
            if(student.getId().equals(jsonStudent.getId())){

                student.setAge(jsonStudent.getAge());
                student.setName(jsonStudent.getName());
            }
        }
        return this.students;
    }

}
