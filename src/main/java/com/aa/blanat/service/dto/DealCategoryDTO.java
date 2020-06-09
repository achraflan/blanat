package com.aa.blanat.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.aa.blanat.domain.DealCategory} entity.
 */
public class DealCategoryDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @Size(max = 1000)
    private String description;

    private Boolean isParent;


    private Long categoryId;

    private String categoryName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long dealCategoryId) {
        this.categoryId = dealCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String dealCategoryName) {
        this.categoryName = dealCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealCategoryDTO)) {
            return false;
        }

        return id != null && id.equals(((DealCategoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealCategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", isParent='" + isIsParent() + "'" +
            ", categoryId=" + getCategoryId() +
            ", categoryName='" + getCategoryName() + "'" +
            "}";
    }
}
