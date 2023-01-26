package com.example.studentManagement;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentRepository {
    Map<Integer,Student> dB= new HashMap<>();

    public Student getStudent(int id){
        return dB.get(id);
    }
    public String addStudent(Student student){
        int id= student.getAdmnNo();
        dB.put(id, student);
        return "Added Sucessfully";
    }
    public String deleteStudent(int id){
        if(!dB.containsKey(id))
            return "Invalid id";
        else
            dB.remove(id);
        return "Successfully Deleted";
    }
    public String updateStudent(int id, int age){
        if(!dB.containsKey(id))
            return null;
        dB.get(id).setAge(age);
        return "Age Updated";
    }
}
