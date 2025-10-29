java
package com.example.hobby;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HobbyService {

    // Assuming a repository or direct data source is available
    public List<Hobby> getAllHobbies() {
        // Retrieve data from data source, e.g., database or static list
        return List.of(
            new Hobby(1, "Painting"),
            new Hobby(2, "Drawing"),
            new Hobby(3, "Sculpting")
        );
    }
}