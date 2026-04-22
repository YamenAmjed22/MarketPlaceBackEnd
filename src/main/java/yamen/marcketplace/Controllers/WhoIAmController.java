package yamen.marcketplace.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yamen.marcketplace.Services.WhoIAmService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class WhoIAmController {

    @Autowired
    private WhoIAmService whoIAmService;


    @GetMapping("/who-i-am")
    public Map<String, Object> whoIAm(HttpServletRequest request) {
        return whoIAmService.whoIAm(request);
    }
}
