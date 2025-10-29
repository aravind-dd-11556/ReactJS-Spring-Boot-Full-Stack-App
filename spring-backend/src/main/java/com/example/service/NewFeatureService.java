java
package com.example.service;

import com.example.model.NewFeature;
import com.example.repository.NewFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewFeatureService {

    @Autowired
    private NewFeatureRepository newFeatureRepository;

    public List<NewFeature> findAllFeatures() {
        return newFeatureRepository.findAll();
    }
}