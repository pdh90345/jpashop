package com.jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 이 클래스가 Spirng MVC의 컨트롤러임을 나타낸다
// 컨트롤러는 사용자 요청을 처리하고 적절한 뷰를 반환
@Controller
public class HelloController {
    /*
     이 어노테이션은 HTTP GET 요청을 처리하는 메서드를 지정
     hello URL로 들어오는 GET 요청을 이 메서드가 처리
    */
    @GetMapping("hello")
    // Model 객체를 매개변수로 받고 반환 타입은 String, 반환되는 문자열은 뷰의 이름
    public String hello(Model model) {
        // Model 객체에 data라는 이름의 attribute(속성)을 추가하고 그 값으로 hello!!를 설정
        model.addAttribute("data", "hello!!");
        return "hello"; // 일반적으로 .html, .jsp 같은 템플릿 파일을 가리킨다
    }
}
