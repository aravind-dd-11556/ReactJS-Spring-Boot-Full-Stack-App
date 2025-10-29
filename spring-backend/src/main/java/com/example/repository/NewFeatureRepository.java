java
package com.example.repository;

import com.example.model.NewFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewFeatureRepository extends JpaRepository<NewFeature, Long> {
}