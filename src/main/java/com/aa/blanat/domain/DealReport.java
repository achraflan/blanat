package com.aa.blanat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DealReport.
 */
@Entity
@Table(name = "deal_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DealReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 1000)
    @Column(name = "reason", length = 1000, nullable = false)
    private String reason;

    @NotNull
    @Column(name = "date_report", nullable = false)
    private LocalDate dateReport;

    @Column(name = "date_close")
    private LocalDate dateClose;

    @Column(name = "is_valid")
    private Boolean isValid;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dealReports", allowSetters = true)
    private DealUser assignedTo;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "dealReports", allowSetters = true)
    private Deal deal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public DealReport reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public DealReport dateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
        return this;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

    public LocalDate getDateClose() {
        return dateClose;
    }

    public DealReport dateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
        return this;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }

    public Boolean isIsValid() {
        return isValid;
    }

    public DealReport isValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public DealUser getAssignedTo() {
        return assignedTo;
    }

    public DealReport assignedTo(DealUser dealUser) {
        this.assignedTo = dealUser;
        return this;
    }

    public void setAssignedTo(DealUser dealUser) {
        this.assignedTo = dealUser;
    }

    public Deal getDeal() {
        return deal;
    }

    public DealReport deal(Deal deal) {
        this.deal = deal;
        return this;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealReport)) {
            return false;
        }
        return id != null && id.equals(((DealReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealReport{" +
            "id=" + getId() +
            ", reason='" + getReason() + "'" +
            ", dateReport='" + getDateReport() + "'" +
            ", dateClose='" + getDateClose() + "'" +
            ", isValid='" + isIsValid() + "'" +
            "}";
    }
}
