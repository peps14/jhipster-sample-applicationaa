package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AttributeDefinitionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttributeDefinition.class);
        AttributeDefinition attributeDefinition1 = new AttributeDefinition();
        attributeDefinition1.setId(1L);
        AttributeDefinition attributeDefinition2 = new AttributeDefinition();
        attributeDefinition2.setId(attributeDefinition1.getId());
        assertThat(attributeDefinition1).isEqualTo(attributeDefinition2);
        attributeDefinition2.setId(2L);
        assertThat(attributeDefinition1).isNotEqualTo(attributeDefinition2);
        attributeDefinition1.setId(null);
        assertThat(attributeDefinition1).isNotEqualTo(attributeDefinition2);
    }
}
