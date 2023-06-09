package SSU.SSUtudyWith.filter;

import SSU.SSUtudyWith.repository.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.NumberUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{
    private final JwtTokenProvider jwtTokenProvider;

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean checkParam(String token,ServletRequest request){

        String uri = ((HttpServletRequest) request).getRequestURI();
        String[] uriParts = uri.split("/");
        String LastParam = uriParts[uriParts.length-1];
        if(uri.contains("studies")){

            return true;
        }



        if(!isNumeric(LastParam))
        {
            return true;
        }
        else{
            if(LastParam.equals(jwtTokenProvider.getUserPk(token))){
                return true;
            }
            return false;
        }
    }
    //NumberUtils numberUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);


        // 유효한 토큰인지 확인합니다.

        if (token != null && jwtTokenProvider.validateToken(token)&&checkParam(token,request)) {



            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
