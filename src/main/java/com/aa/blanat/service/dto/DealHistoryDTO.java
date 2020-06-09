package com.aa.blanat.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.aa.blanat.domain.DealHistory} entity.
 */
public class DealHistoryDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String attributName;

    @NotNull
    @Size(max = 10000)
    private String attributLastValue;

    @NotNull
    private LocalDate dateModification;


    private Long dealId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributName() {
        return attributName;
    }

    public void setAttributName(String attributName) {
        this.attributName = attributName;
    }

    public String getAttributLastValue() {
        return attributLastValue;
    }

    public void setAttributLastValue(String attributLastValue) {
        this.attributLastValue = attributLastValue;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
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
        if (!(o instanceof DealHistoryDTO)) {
            return false;
        }

        return id != null && id.equals(((DealHistoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealHistoryDTO{" +
            "id=" + getId() +
            ", attributName='" + getAttributName() + "'" +
            ", attributLastValue='" + getAttributLastValue() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            ", dealId=" + getDealId() +
            "}";
    }
}
