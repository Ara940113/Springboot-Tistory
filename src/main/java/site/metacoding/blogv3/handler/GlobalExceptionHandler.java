package site.metacoding.blogv3.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import site.metacoding.blogv3.handler.ex.CustomApiException;
import site.metacoding.blogv3.handler.ex.CustomException;

@RestControllerAdvice // rest니까 데이터 리턴
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomApiException.class) // 어떤 익셉션이 발생할 때 얘를 실행 할 건지 결정한다.
    public ResponseEntity<?> apiException(Exception e) { // fetch 요청시 발동
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // json으로 리턴해주면 끝!
    }

    @ExceptionHandler(CustomException.class)
    public String htmlException(CustomException e) { // 일반적인 get(a태그),post(form태그) 요청시 발동
        StringBuilder sb = new StringBuilder();
        sb.append("<script>"); // html 파일로 만들어줘야한다.
        sb.append("alert('" + e.getMessage() + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

}
