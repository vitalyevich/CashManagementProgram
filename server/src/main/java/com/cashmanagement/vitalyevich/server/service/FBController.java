package com.cashmanagement.vitalyevich.server.service;

import com.cashmanagement.vitalyevich.server.firebase.model.WorkTime;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FBController {

    @Autowired
    FBService fbService;

    @GetMapping("/getWork/{name}")
    public WorkTime getWork(@PathVariable String name) throws InterruptedException, ExecutionException {
        return fbService.getWork(name);
    }

    /*@PostMapping("/createWork")
    public String createWork(@RequestBody WorkTime workTime) throws InterruptedException, ExecutionException {
        return fbService.saveWork(workTime);
    }*/

    @PutMapping("/updateWork")
    public String updateWork(@RequestBody WorkTime workTime) throws InterruptedException, ExecutionException {
        return fbService.updateWork(workTime);
    }

    @DeleteMapping("/deleteWork/{name}")
    public String deleteWork(@PathVariable String name){
        return fbService.deleteWork(name);
    }

    @GetMapping("/works")
    public List<WorkTime> getAllWorks() throws  ExecutionException, InterruptedException {
        return  fbService.getWorks();
    }
}
