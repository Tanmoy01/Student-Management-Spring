package com.example.studentManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
public class StudentConroller {

    @Autowired
    StudentService studentService;

    //get info-by admission ID
    @GetMapping("/get_student")
    public ResponseEntity getStudent(@RequestParam("q") int admnNo){
        Student student= studentService.getStudent(admnNo);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }


    // get info by name
//    @GetMapping("/get_student_byName")
//    public Student getStudent(@RequestParam("q") String name){
//        int admnId=0;
//        for(Map.Entry<Integer, Student> e : dB.entrySet()){
//            Student stud=e.getValue();
//            String sName= stud.getName();
//            if(sName.equals(name)){
//                admnId=e.getKey();
//            }
//        }
//        if(admnId ==0){
//            System.out.println(name + "The name is invalid");
//            return null;
//        }else{
//            return dB.get(admnId);
//        }
//    }


    //Adding info
    @PostMapping("/add_student")
    public ResponseEntity addStudent(@RequestBody Student student){
        String response= studentService.addStudent(student);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //Delete info
    //1st Method
//    @DeleteMapping("/delete_student")
//    public String deleteStudent(@RequestParam("q") int admnNo){
//        if(!dB.containsKey(admnNo))
//            return "Admission no is Invalid, Try again";
//        else
//            dB.remove(admnNo);
//        return "Successfully Deleted";
//    }
    //2nd Method
    @DeleteMapping("/delete_student/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int id){
        String response=studentService.deleteStudent(id);
        if(response.equals("Invalid id")){
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


    //Update info
//    @PutMapping("/update_student")
//    public String updateStudent(@RequestParam("q") int admnNo, @RequestBody Student student){
//        if(!dB.containsKey(admnNo)){
//            return "Invalid No";
//        }
//        else{
//            dB.put(admnNo, student);
//            return "Added Sucessfully added";
//        }

    //Update Info
    @PutMapping("/update_student")
    public ResponseEntity updateStudent(@RequestParam("id") int id, @RequestParam("age") int age){
        String response= studentService.updateStudent(id,age);
        if(response == null){
            return new ResponseEntity("Invalid request", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
    }
}
