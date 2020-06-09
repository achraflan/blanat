package com.aa.blanat.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.aa.blanat.domain.enumeration.TypeDeal;
import com.aa.blanat.domain.enumeration.TypeCoupon;
import com.aa.blanat.domain.enumeration.StatutDeal;

/**
 * A DTO for the {@link com.aa.blanat.domain.Deal} entity.
 */
public class DealDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @Size(max = 10000)
    private String description;

    @NotNull
    private TypeDeal type;

    private String url;

    private String image;

    private Float price;

    private Float priceNormal;

    private Float priceShipping;

    private String coupon;

    private TypeCoupon couponType;

    private Float couponValue;

    @Min(value = 1)
    @Max(value = 100)
    private Integer couponPercentage;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    @NotNull
    private LocalDate datePublication;

    private Long views;

    private Long like;

    private Long dislike;

    private Boolean local;

    @NotNull
    private StatutDeal statut;

    private Boolean isDeleted;

    private Boolean isBlocked;


    private Long dealLocationId;

    private Long assignedToId;
    private Set<DealCategoryDTO> dealCategories = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeDeal getType() {
        return type;
    }

    public void setType(TypeDeal type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPriceNormal() {
        return priceNormal;
    }

    public void setPriceNormal(Float priceNormal) {
        this.priceNormal = priceNormal;
    }

    public Float getPriceShipping() {
        return priceShipping;
    }

    public void setPriceShipping(Float priceShipping) {
        this.priceShipping = priceShipping;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public TypeCoupon getCouponType() {
        return couponType;
    }

    public void setCouponType(TypeCoupon couponType) {
        this.couponType = couponType;
    }

    public Float getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Float couponValue) {
        this.couponValue = couponValue;
    }

    public Integer getCouponPercentage() {
        return couponPercentage;
    }

    public void setCouponPercentage(Integer couponPercentage) {
        this.couponPercentage = couponPercentage;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getDislike() {
        return dislike;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public Boolean isLocal() {
        return local;
    }

    public void setLocal(Boolean local) {
        this.local = local;
    }

    public StatutDeal getStatut() {
        return statut;
    }

    public void setStatut(StatutDeal statut) {
        this.statut = statut;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Long getDealLocationId() {
        return dealLocationId;
    }

    public void setDealLocationId(Long dealLocationId) {
        this.dealLocationId = dealLocationId;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long dealUserId) {
        this.assignedToId = dealUserId;
    }

    public Set<DealCategoryDTO> getDealCategories() {
        return dealCategories;
    }

    public void setDealCategories(Set<DealCategoryDTO> dealCategories) {
        this.dealCategories = dealCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DealDTO)) {
            return false;
        }

        return id != null && id.equals(((DealDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DealDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", url='" + getUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", price=" + getPrice() +
            ", priceNormal=" + getPriceNormal() +
            ", priceShipping=" + getPriceShipping() +
            ", coupon='" + getCoupon() + "'" +
            ", couponType='" + getCouponType() + "'" +
            ", couponValue=" + getCouponValue() +
            ", couponPercentage=" + getCouponPercentage() +
            ", dateStart='" + getDateStart() + "'" +
            ", dateEnd='" + getDateEnd() + "'" +
            ", datePublication='" + getDatePublication() + "'" +
            ", views=" + getViews() +
            ", like=" + getLike() +
            ", dislike=" + getDislike() +
            ", local='" + isLocal() + "'" +
            ", statut='" + getStatut() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", isBlocked='" + isIsBlocked() + "'" +
            ", dealLocationId=" + getDealLocationId() +
            ", assignedToId=" + getAssignedToId() +
            ", dealCategories='" + getDealCategories() + "'" +
            "}";
    }
}
