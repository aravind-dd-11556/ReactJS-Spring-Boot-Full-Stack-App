java
package com.example.controller;

import com.example.service.NewFeatureService;
import com.example.model.NewFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/new-feature-endpoint")
public class NewFeatureController {

    @Autowired
    private NewFeatureService newFeatureService;

    @GetMapping
    public List<NewFeature> getAllFeatures() {
        return newFeatureService.findAllFeatures();
    }
}