package com.example.test.controller;

import com.example.test.repository.MemberRepository;
import com.example.test.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
public class EmailController extends HttpServlet {

    private final EmailService emailService;
    private final MemberRepository accountRepository;
    String authCode = "";

    @RequestMapping("/m/{email}")
    public String mailConfirm(@PathVariable String email, Model model) throws MessagingException, UnsupportedEncodingException {
        authCode = emailService.sendEmail(email);
        model.addAttribute("authCode", authCode);
        return "mailCheck";

    }

    @RequestMapping("/emailCheck/{email}/{name}")
    @ResponseBody
    public String mailConfirm1(@PathVariable String email,@PathVariable String name, Model model) throws MessagingException, UnsupportedEncodingException {
        System.out.println(email);
        if(accountRepository.existsByEmailAndName(email,name)){
            authCode = emailService.sendEmail(email);
            model.addAttribute("authCode", authCode);
            return authCode;
        }else{

            return "";
        }
    }
    @RequestMapping("/emailCheck/{email}/{name}/{loginId}")
    @ResponseBody
    public String mailConfirm2(@PathVariable String email,@PathVariable String name,@PathVariable String loginId, Model model) throws MessagingException, UnsupportedEncodingException {
        System.out.println(email);
        if(accountRepository.existsByEmailAndNameAndLoginId(email,name,loginId)){
            authCode = emailService.sendEmail(email);
            model.addAttribute("authCode", authCode);
            return authCode;
        }else{

            return "";
        }
    }

//    @RequestMapping("/confirm")
//    public @ResponseBody String codeCheck(HttpServletRequest req){
//        String code = req.getParameter("code");
//        return code.equals(authCode) ? "인증에 성곻하였습니다." : "인증에 실패하였습니다";
//    }

    @RequestMapping("/{code}")
    public @ResponseBody String code(@PathVariable String code){
        String str = code.concat(authCode);
        return str;
    }
}
