package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewCourseController {

    @RequestMapping("/reviewC")
    public  String review2(){
        return "review/reviewCourse";
    }

}

