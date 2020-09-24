package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class AttributeAuthorityDefinitionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AttributeAuthorityDefinition.class);
        AttributeAuthorityDefinition attributeAuthorityDefinition1 = new AttributeAuthorityDefinition();
        attributeAuthorityDefinition1.setId(1L);
        AttributeAuthorityDefinition attributeAuthorityDefinition2 = new AttributeAuthorityDefinition();
        attributeAuthorityDefinition2.setId(attributeAuthorityDefinition1.getId());
        assertThat(attributeAuthorityDefinition1).isEqualTo(attributeAuthorityDefinition2);
        attributeAuthorityDefinition2.setId(2L);
        assertThat(attributeAuthorityDefinition1).isNotEqualTo(attributeAuthorityDefinition2);
        attributeAuthorityDefinition1.setId(null);
        assertThat(attributeAuthorityDefinition1).isNotEqualTo(attributeAuthorityDefinition2);
    }
}
