package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A AttributeAuthorityDefinition.
 */
@Entity
@Table(name = "attribute_authority_definition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttributeAuthorityDefinition implements Serializable {

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

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "spid_level")
    private String spidLevel;

    @Column(name = "attributes_url")
    private String attributesUrl;

    @Column(name = "consent_required")
    private Boolean consentRequired;

    @Column(name = "consent_url")
    private String consentUrl;

    @Column(name = "check_consent_url")
    private String checkConsentUrl;

    @Column(name = "error_redirect_url")
    private String errorRedirectUrl;

    @Column(name = "spid_schema_version")
    private String spidSchemaVersion;

    @Column(name = "api_version")
    private String apiVersion;

    @Column(name = "test_url")
    private String testUrl;

    @OneToMany(mappedBy = "attributeAuthority")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<AttributeDefinition> attributes = new HashSet<>();

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

    public AttributeAuthorityDefinition code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public AttributeAuthorityDefinition name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public AttributeAuthorityDefinition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public AttributeAuthorityDefinition enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSpidLevel() {
        return spidLevel;
    }

    public AttributeAuthorityDefinition spidLevel(String spidLevel) {
        this.spidLevel = spidLevel;
        return this;
    }

    public void setSpidLevel(String spidLevel) {
        this.spidLevel = spidLevel;
    }

    public String getAttributesUrl() {
        return attributesUrl;
    }

    public AttributeAuthorityDefinition attributesUrl(String attributesUrl) {
        this.attributesUrl = attributesUrl;
        return this;
    }

    public void setAttributesUrl(String attributesUrl) {
        this.attributesUrl = attributesUrl;
    }

    public Boolean isConsentRequired() {
        return consentRequired;
    }

    public AttributeAuthorityDefinition consentRequired(Boolean consentRequired) {
        this.consentRequired = consentRequired;
        return this;
    }

    public void setConsentRequired(Boolean consentRequired) {
        this.consentRequired = consentRequired;
    }

    public String getConsentUrl() {
        return consentUrl;
    }

    public AttributeAuthorityDefinition consentUrl(String consentUrl) {
        this.consentUrl = consentUrl;
        return this;
    }

    public void setConsentUrl(String consentUrl) {
        this.consentUrl = consentUrl;
    }

    public String getCheckConsentUrl() {
        return checkConsentUrl;
    }

    public AttributeAuthorityDefinition checkConsentUrl(String checkConsentUrl) {
        this.checkConsentUrl = checkConsentUrl;
        return this;
    }

    public void setCheckConsentUrl(String checkConsentUrl) {
        this.checkConsentUrl = checkConsentUrl;
    }

    public String getErrorRedirectUrl() {
        return errorRedirectUrl;
    }

    public AttributeAuthorityDefinition errorRedirectUrl(String errorRedirectUrl) {
        this.errorRedirectUrl = errorRedirectUrl;
        return this;
    }

    public void setErrorRedirectUrl(String errorRedirectUrl) {
        this.errorRedirectUrl = errorRedirectUrl;
    }

    public String getSpidSchemaVersion() {
        return spidSchemaVersion;
    }

    public AttributeAuthorityDefinition spidSchemaVersion(String spidSchemaVersion) {
        this.spidSchemaVersion = spidSchemaVersion;
        return this;
    }

    public void setSpidSchemaVersion(String spidSchemaVersion) {
        this.spidSchemaVersion = spidSchemaVersion;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public AttributeAuthorityDefinition apiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getTestUrl() {
        return testUrl;
    }

    public AttributeAuthorityDefinition testUrl(String testUrl) {
        this.testUrl = testUrl;
        return this;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public Set<AttributeDefinition> getAttributes() {
        return attributes;
    }

    public AttributeAuthorityDefinition attributes(Set<AttributeDefinition> attributeDefinitions) {
        this.attributes = attributeDefinitions;
        return this;
    }

    public AttributeAuthorityDefinition addAttributes(AttributeDefinition attributeDefinition) {
        this.attributes.add(attributeDefinition);
        attributeDefinition.setAttributeAuthority(this);
        return this;
    }

    public AttributeAuthorityDefinition removeAttributes(AttributeDefinition attributeDefinition) {
        this.attributes.remove(attributeDefinition);
        attributeDefinition.setAttributeAuthority(null);
        return this;
    }

    public void setAttributes(Set<AttributeDefinition> attributeDefinitions) {
        this.attributes = attributeDefinitions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeAuthorityDefinition)) {
            return false;
        }
        return id != null && id.equals(((AttributeAuthorityDefinition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttributeAuthorityDefinition{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", spidLevel='" + getSpidLevel() + "'" +
            ", attributesUrl='" + getAttributesUrl() + "'" +
            ", consentRequired='" + isConsentRequired() + "'" +
            ", consentUrl='" + getConsentUrl() + "'" +
            ", checkConsentUrl='" + getCheckConsentUrl() + "'" +
            ", errorRedirectUrl='" + getErrorRedirectUrl() + "'" +
            ", spidSchemaVersion='" + getSpidSchemaVersion() + "'" +
            ", apiVersion='" + getApiVersion() + "'" +
            ", testUrl='" + getTestUrl() + "'" +
            "}";
    }
}
