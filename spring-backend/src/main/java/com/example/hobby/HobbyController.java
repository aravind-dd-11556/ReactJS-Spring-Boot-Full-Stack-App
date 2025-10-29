java
package com.example.hobby;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class HobbyController {

    @Autowired
    private HobbyService hobbyService;

    @GetMapping("/api/hobbies")
    public List<Hobby> getAllHobbies() {
        return hobbyService.getAllHobbies();
    }
}