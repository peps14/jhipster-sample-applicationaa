package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AttributeDefinition.
 */
@Entity
@Table(name = "attribute_definition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttributeDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "public_avalable")
    private String publicAvalable;

    @Column(name = "spid_level")
    private String spidLevel;

    @Column(name = "multivalue")
    private Boolean multivalue;

    @Lob
    @Column(name = "default_values")
    private String defaultValues;

    @Column(name = "consent_required")
    private Boolean consentRequired;

    @Column(name = "aa_code")
    private String aaCode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "attributes", allowSetters = true)
    private AttributeAuthorityDefinition attributeAuthority;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public AttributeDefinition code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public AttributeDefinition name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public AttributeDefinition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicAvalable() {
        return publicAvalable;
    }

    public AttributeDefinition publicAvalable(String publicAvalable) {
        this.publicAvalable = publicAvalable;
        return this;
    }

    public void setPublicAvalable(String publicAvalable) {
        this.publicAvalable = publicAvalable;
    }

    public String getSpidLevel() {
        return spidLevel;
    }

    public AttributeDefinition spidLevel(String spidLevel) {
        this.spidLevel = spidLevel;
        return this;
    }

    public void setSpidLevel(String spidLevel) {
        this.spidLevel = spidLevel;
    }

    public Boolean isMultivalue() {
        return multivalue;
    }

    public AttributeDefinition multivalue(Boolean multivalue) {
        this.multivalue = multivalue;
        return this;
    }

    public void setMultivalue(Boolean multivalue) {
        this.multivalue = multivalue;
    }

    public String getDefaultValues() {
        return defaultValues;
    }

    public AttributeDefinition defaultValues(String defaultValues) {
        this.defaultValues = defaultValues;
        return this;
    }

    public void setDefaultValues(String defaultValues) {
        this.defaultValues = defaultValues;
    }

    public Boolean isConsentRequired() {
        return consentRequired;
    }

    public AttributeDefinition consentRequired(Boolean consentRequired) {
        this.consentRequired = consentRequired;
        return this;
    }

    public void setConsentRequired(Boolean consentRequired) {
        this.consentRequired = consentRequired;
    }

    public String getAaCode() {
        return aaCode;
    }

    public AttributeDefinition aaCode(String aaCode) {
        this.aaCode = aaCode;
        return this;
    }

    public void setAaCode(String aaCode) {
        this.aaCode = aaCode;
    }

    public AttributeAuthorityDefinition getAttributeAuthority() {
        return attributeAuthority;
    }

    public AttributeDefinition attributeAuthority(AttributeAuthorityDefinition attributeAuthorityDefinition) {
        this.attributeAuthority = attributeAuthorityDefinition;
        return this;
    }

    public void setAttributeAuthority(AttributeAuthorityDefinition attributeAuthorityDefinition) {
        this.attributeAuthority = attributeAuthorityDefinition;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeDefinition)) {
            return false;
        }
        return id != null && id.equals(((AttributeDefinition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttributeDefinition{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", publicAvalable='" + getPublicAvalable() + "'" +
            ", spidLevel='" + getSpidLevel() + "'" +
            ", multivalue='" + isMultivalue() + "'" +
            ", defaultValues='" + getDefaultValues() + "'" +
            ", consentRequired='" + isConsentRequired() + "'" +
            ", aaCode='" + getAaCode() + "'" +
            "}";
    }
}
