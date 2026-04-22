package yamen.marcketplace.Services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhoIAmService {

    public Map<String, Object> whoIAm(HttpServletRequest request) {

        Map<String, Object> response = new HashMap<>();

        String message = "Hi , welcome in my marketplace";
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String token = request.getHeader("Authorization");
        String contentType = request.getHeader("Content-Type");

        response.put("ip", ip);
        response.put("userAgent", userAgent);
        response.put("token", token);
        response.put("contentType", contentType);
        response.put("message", message);

        return response;
    }
}
