package com.sparta.springauth.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 서버에서 클라이언트로 응답을 보낼때의 내용을 파라미터로 받아서
     * addCookie (cookieValue, response 데이터) 형식으로 쿠키를 만든다.
     * @param res 서버에서 클라이언트로 응답을 보내는 내용
     * @return
     */
    @GetMapping("/create-cookie")
    public String createCookie(HttpServletResponse res) {
        addCookie("Robbie Auth", res);

        return "createCookie";
    }

    /**
     * Request Headers에서 @CookieValue를 이용하여 쿠키밸류를 뽑아온다.
     * 여기서는 create-cookie에서 String 타입으로 "Robbe Auth"라고 밸류를 주었으니, String value로 받는다.
     * @param value Request Headers -> @CookieValue String value
     * @return
     */
    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(AUTHORIZATION_HEADER) String value) {
        System.out.println("value = " + value);

        return "getCookie : " + value;
    }

    @GetMapping("/create-session")
    public String createSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 새로운 세션을 생성한 후 반환
        HttpSession session = req.getSession(true); // true : 없으면 새로 만들어! / false : 없어도 새로 만들지마!

        // 세션에 저장될 정보 Name - Value 를 추가합니다.
        session.setAttribute(AUTHORIZATION_HEADER, "Robbie Auth");

        return "createSession";
    }

    @GetMapping("/get-session")
    public String getSession(HttpServletRequest req) {
        // 세션이 존재할 경우 세션 반환, 없을 경우 null 반환
        HttpSession session = req.getSession(false); // 가지고만 올꺼니까 새로 만들지는 마!

        String value = (String) session.getAttribute(AUTHORIZATION_HEADER); // 가져온 세션에 저장된 Value 를 Name 을 사용하여 가져옵니다.
        System.out.println("value = " + value);

        return "getSession : " + value;
    }

    /**
     * 응답보낼 데이터에 쿠키를 만들어 추가합니다.
     * @param cookieValue createCookie에서 addCookie의 첫번쨰 파라미터값에 들어간 cookieValue
     * @param res Response 하는 내용
     *            참고) HttpServletResponse :
     *                  서버에서 클라이언트로의 HTTP 응답 정보를 담고 있는 객체.
     */
    public static void addCookie(String cookieValue, HttpServletResponse res) {
        try {
            cookieValue = URLEncoder.encode(cookieValue, "utf-8").replaceAll("\\+", "%20");
            // Cookie Value 에는 공백이 불가능해서 encoding 진행

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, cookieValue); // Name-Value
            cookie.setPath("/");
            cookie.setMaxAge(30 * 60);

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}