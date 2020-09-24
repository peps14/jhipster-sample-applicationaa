package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class CacheConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CacheConfiguration.class);
        CacheConfiguration cacheConfiguration1 = new CacheConfiguration();
        cacheConfiguration1.setId(1L);
        CacheConfiguration cacheConfiguration2 = new CacheConfiguration();
        cacheConfiguration2.setId(cacheConfiguration1.getId());
        assertThat(cacheConfiguration1).isEqualTo(cacheConfiguration2);
        cacheConfiguration2.setId(2L);
        assertThat(cacheConfiguration1).isNotEqualTo(cacheConfiguration2);
        cacheConfiguration1.setId(null);
        assertThat(cacheConfiguration1).isNotEqualTo(cacheConfiguration2);
    }
}
