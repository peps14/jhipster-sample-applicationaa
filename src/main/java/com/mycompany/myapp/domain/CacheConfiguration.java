package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import com.mycompany.myapp.domain.enumeration.CacheType;

/**
 * A CacheConfiguration.
 */
@Entity
@Table(name = "cache_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CacheConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "force_default")
    private Boolean forceDefault;

    @Column(name = "auto_refresh")
    private Boolean autoRefresh;

    @Column(name = "autho_refresh_cron_expression")
    private String authoRefreshCronExpression;

    @Column(name = "autho_refresh_batch_size")
    private Integer authoRefreshBatchSize;

    @Column(name = "autho_refresh_batch_interval")
    private Long authoRefreshBatchInterval;

    @Column(name = "auto_clean")
    private Boolean autoClean;

    @Column(name = "auto_clean_cron_expression")
    private String autoCleanCronExpression;

    @Column(name = "duration")
    private Long duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "cache_type")
    private CacheType cacheType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public CacheConfiguration enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isForceDefault() {
        return forceDefault;
    }

    public CacheConfiguration forceDefault(Boolean forceDefault) {
        this.forceDefault = forceDefault;
        return this;
    }

    public void setForceDefault(Boolean forceDefault) {
        this.forceDefault = forceDefault;
    }

    public Boolean isAutoRefresh() {
        return autoRefresh;
    }

    public CacheConfiguration autoRefresh(Boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
        return this;
    }

    public void setAutoRefresh(Boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public String getAuthoRefreshCronExpression() {
        return authoRefreshCronExpression;
    }

    public CacheConfiguration authoRefreshCronExpression(String authoRefreshCronExpression) {
        this.authoRefreshCronExpression = authoRefreshCronExpression;
        return this;
    }

    public void setAuthoRefreshCronExpression(String authoRefreshCronExpression) {
        this.authoRefreshCronExpression = authoRefreshCronExpression;
    }

    public Integer getAuthoRefreshBatchSize() {
        return authoRefreshBatchSize;
    }

    public CacheConfiguration authoRefreshBatchSize(Integer authoRefreshBatchSize) {
        this.authoRefreshBatchSize = authoRefreshBatchSize;
        return this;
    }

    public void setAuthoRefreshBatchSize(Integer authoRefreshBatchSize) {
        this.authoRefreshBatchSize = authoRefreshBatchSize;
    }

    public Long getAuthoRefreshBatchInterval() {
        return authoRefreshBatchInterval;
    }

    public CacheConfiguration authoRefreshBatchInterval(Long authoRefreshBatchInterval) {
        this.authoRefreshBatchInterval = authoRefreshBatchInterval;
        return this;
    }

    public void setAuthoRefreshBatchInterval(Long authoRefreshBatchInterval) {
        this.authoRefreshBatchInterval = authoRefreshBatchInterval;
    }

    public Boolean isAutoClean() {
        return autoClean;
    }

    public CacheConfiguration autoClean(Boolean autoClean) {
        this.autoClean = autoClean;
        return this;
    }

    public void setAutoClean(Boolean autoClean) {
        this.autoClean = autoClean;
    }

    public String getAutoCleanCronExpression() {
        return autoCleanCronExpression;
    }

    public CacheConfiguration autoCleanCronExpression(String autoCleanCronExpression) {
        this.autoCleanCronExpression = autoCleanCronExpression;
        return this;
    }

    public void setAutoCleanCronExpression(String autoCleanCronExpression) {
        this.autoCleanCronExpression = autoCleanCronExpression;
    }

    public Long getDuration() {
        return duration;
    }

    public CacheConfiguration duration(Long duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    public CacheConfiguration cacheType(CacheType cacheType) {
        this.cacheType = cacheType;
        return this;
    }

    public void setCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CacheConfiguration)) {
            return false;
        }
        return id != null && id.equals(((CacheConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CacheConfiguration{" +
            "id=" + getId() +
            ", enabled='" + isEnabled() + "'" +
            ", forceDefault='" + isForceDefault() + "'" +
            ", autoRefresh='" + isAutoRefresh() + "'" +
            ", authoRefreshCronExpression='" + getAuthoRefreshCronExpression() + "'" +
            ", authoRefreshBatchSize=" + getAuthoRefreshBatchSize() +
            ", authoRefreshBatchInterval=" + getAuthoRefreshBatchInterval() +
            ", autoClean='" + isAutoClean() + "'" +
            ", autoCleanCronExpression='" + getAutoCleanCronExpression() + "'" +
            ", duration=" + getDuration() +
            ", cacheType='" + getCacheType() + "'" +
            "}";
    }
}
