package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CacheConfiguration;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CacheConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CacheConfigurationRepository extends JpaRepository<CacheConfiguration, Long> {
}
