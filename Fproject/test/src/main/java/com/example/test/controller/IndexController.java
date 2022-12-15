package com.example.test.controller;

import com.example.test.domain.ReviewCourseListResponseDTO;
import com.example.test.domain.ReviewCourseResponseDTO;
import com.example.test.service.MemberService;
import com.example.test.service.ReviewCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {//페이지에 관련된 컨트롤러
    public final ReviewCourseService reviewCourseService;
    private final MemberService memberService;

    @GetMapping("/index")
    public String index(Model model, Principal principal){
        model.addAttribute("reviewCourse", reviewCourseService.findAllDesc());
        if(principal != null){
            model.addAttribute("name",principal.getName());
        }
        return "index";
    }

    @GetMapping("/review/write")
    public String reviewWrite(Model model, Principal principal){
        model.addAttribute("name",principal.getName());
        return "review/reviewCourseWrite";
    }
    //작성(저장)
    @GetMapping("/review/update/{id}")
    public String reviewUpdate(@PathVariable Long id, Model model){
        ReviewCourseResponseDTO dto = reviewCourseService.findById(id);
        model.addAttribute("reviewCourse",dto);
        return "review/reviewCourseUpdate";
    }
    //수정
    @GetMapping("/review/detail/{id}")
    public String reviewDetail(@PathVariable Long id, Model model) {
        ReviewCourseResponseDTO dto = reviewCourseService.findById(id);
        model.addAttribute("reviewCourse",dto);
        return "review/reviewCourseDetail";
    }
    //조회
    @GetMapping("/review/search")
    public String search(@RequestParam(value="searchQuery") String keyword, Model model) {
        List<ReviewCourseListResponseDTO> dto = reviewCourseService.findByKeyword(keyword);
        model.addAttribute("reviewCourse", dto);
        return "/review/reviewCourse";
    }
    //검색

}
