package com.aa.blanat.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.aa.blanat.domain.DealReport} entity.
 */
public class DealReportDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 1000)
    private String reason;

    @NotNull
    private LocalDate dateReport;

    private LocalDate dateClose;

    private Boolean isValid;


    private Long assignedToId;

    private Long dealId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

    public LocalDate getDateClose() {
        return dateClose;
    }

    public void setDateClose(LocalDate dateClose) {
        this.dateClose = dateClose;
    }

    public Boolean isIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long dealUserId) {
        this.assignedToId = dealUserId;
    }

    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealReportDTO)) {
            return false;
        }

        return id != null && id.equals(((DealReportDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealReportDTO{" +
            "id=" + getId() +
            ", reason='" + getReason() + "'" +
            ", dateReport='" + getDateReport() + "'" +
            ", dateClose='" + getDateClose() + "'" +
            ", isValid='" + isIsValid() + "'" +
            ", assignedToId=" + getAssignedToId() +
            ", dealId=" + getDealId() +
            "}";
    }
}
