package com.aa.blanat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.aa.blanat.domain.enumeration.TypeDeal;

import com.aa.blanat.domain.enumeration.TypeCoupon;

import com.aa.blanat.domain.enumeration.StatutDeal;

/**
 * A Deal.
 */
@Entity
@Table(name = "deal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Size(max = 10000)
    @Column(name = "description", length = 10000, nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeDeal type;

    @Column(name = "url")
    private String url;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Float price;

    @Column(name = "price_normal")
    private Float priceNormal;

    @Column(name = "price_shipping")
    private Float priceShipping;

    @Column(name = "coupon")
    private String coupon;

    @Enumerated(EnumType.STRING)
    @Column(name = "coupon_type")
    private TypeCoupon couponType;

    @Column(name = "coupon_value")
    private Float couponValue;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "coupon_percentage")
    private Integer couponPercentage;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @NotNull
    @Column(name = "date_publication", nullable = false)
    private LocalDate datePublication;

    @Column(name = "views")
    private Long views;

    @Column(name = "jhi_like")
    private Long like;

    @Column(name = "dislike")
    private Long dislike;

    @Column(name = "local")
    private Boolean local;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false)
    private StatutDeal statut;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @OneToOne
    @JoinColumn(unique = true)
    private DealLocation dealLocation;

    @OneToMany(mappedBy = "deal")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DealHistory> dealHistories = new HashSet<>();

    @OneToMany(mappedBy = "deal")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DealTrack> dealTracks = new HashSet<>();

    @OneToMany(mappedBy = "deal")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DealReport> dealReports = new HashSet<>();

    @OneToMany(mappedBy = "deal")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DealComment> dealComments = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "deals", allowSetters = true)
    private DealUser assignedTo;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "deal_deal_category",
               joinColumns = @JoinColumn(name = "deal_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "deal_category_id", referencedColumnName = "id"))
    private Set<DealCategory> dealCategories = new HashSet<>();

    @ManyToMany(mappedBy = "dealSaveds")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<DealUser> dealUsers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Deal title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Deal description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeDeal getType() {
        return type;
    }

    public Deal type(TypeDeal type) {
        this.type = type;
        return this;
    }

    public void setType(TypeDeal type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public Deal url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public Deal image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrice() {
        return price;
    }

    public Deal price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPriceNormal() {
        return priceNormal;
    }

    public Deal priceNormal(Float priceNormal) {
        this.priceNormal = priceNormal;
        return this;
    }

    public void setPriceNormal(Float priceNormal) {
        this.priceNormal = priceNormal;
    }

    public Float getPriceShipping() {
        return priceShipping;
    }

    public Deal priceShipping(Float priceShipping) {
        this.priceShipping = priceShipping;
        return this;
    }

    public void setPriceShipping(Float priceShipping) {
        this.priceShipping = priceShipping;
    }

    public String getCoupon() {
        return coupon;
    }

    public Deal coupon(String coupon) {
        this.coupon = coupon;
        return this;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public TypeCoupon getCouponType() {
        return couponType;
    }

    public Deal couponType(TypeCoupon couponType) {
        this.couponType = couponType;
        return this;
    }

    public void setCouponType(TypeCoupon couponType) {
        this.couponType = couponType;
    }

    public Float getCouponValue() {
        return couponValue;
    }

    public Deal couponValue(Float couponValue) {
        this.couponValue = couponValue;
        return this;
    }

    public void setCouponValue(Float couponValue) {
        this.couponValue = couponValue;
    }

    public Integer getCouponPercentage() {
        return couponPercentage;
    }

    public Deal couponPercentage(Integer couponPercentage) {
        this.couponPercentage = couponPercentage;
        return this;
    }

    public void setCouponPercentage(Integer couponPercentage) {
        this.couponPercentage = couponPercentage;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public Deal dateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public Deal dateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public Deal datePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
        return this;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public Long getViews() {
        return views;
    }

    public Deal views(Long views) {
        this.views = views;
        return this;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getLike() {
        return like;
    }

    public Deal like(Long like) {
        this.like = like;
        return this;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Long getDislike() {
        return dislike;
    }

    public Deal dislike(Long dislike) {
        this.dislike = dislike;
        return this;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public Boolean isLocal() {
        return local;
    }

    public Deal local(Boolean local) {
        this.local = local;
        return this;
    }

    public void setLocal(Boolean local) {
        this.local = local;
    }

    public StatutDeal getStatut() {
        return statut;
    }

    public Deal statut(StatutDeal statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(StatutDeal statut) {
        this.statut = statut;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public Deal isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean isIsBlocked() {
        return isBlocked;
    }

    public Deal isBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
        return this;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public DealLocation getDealLocation() {
        return dealLocation;
    }

    public Deal dealLocation(DealLocation dealLocation) {
        this.dealLocation = dealLocation;
        return this;
    }

    public void setDealLocation(DealLocation dealLocation) {
        this.dealLocation = dealLocation;
    }

    public Set<DealHistory> getDealHistories() {
        return dealHistories;
    }

    public Deal dealHistories(Set<DealHistory> dealHistories) {
        this.dealHistories = dealHistories;
        return this;
    }

    public Deal addDealHistory(DealHistory dealHistory) {
        this.dealHistories.add(dealHistory);
        dealHistory.setDeal(this);
        return this;
    }

    public Deal removeDealHistory(DealHistory dealHistory) {
        this.dealHistories.remove(dealHistory);
        dealHistory.setDeal(null);
        return this;
    }

    public void setDealHistories(Set<DealHistory> dealHistories) {
        this.dealHistories = dealHistories;
    }

    public Set<DealTrack> getDealTracks() {
        return dealTracks;
    }

    public Deal dealTracks(Set<DealTrack> dealTracks) {
        this.dealTracks = dealTracks;
        return this;
    }

    public Deal addDealTrack(DealTrack dealTrack) {
        this.dealTracks.add(dealTrack);
        dealTrack.setDeal(this);
        return this;
    }

    public Deal removeDealTrack(DealTrack dealTrack) {
        this.dealTracks.remove(dealTrack);
        dealTrack.setDeal(null);
        return this;
    }

    public void setDealTracks(Set<DealTrack> dealTracks) {
        this.dealTracks = dealTracks;
    }

    public Set<DealReport> getDealReports() {
        return dealReports;
    }

    public Deal dealReports(Set<DealReport> dealReports) {
        this.dealReports = dealReports;
        return this;
    }

    public Deal addDealReport(DealReport dealReport) {
        this.dealReports.add(dealReport);
        dealReport.setDeal(this);
        return this;
    }

    public Deal removeDealReport(DealReport dealReport) {
        this.dealReports.remove(dealReport);
        dealReport.setDeal(null);
        return this;
    }

    public void setDealReports(Set<DealReport> dealReports) {
        this.dealReports = dealReports;
    }

    public Set<DealComment> getDealComments() {
        return dealComments;
    }

    public Deal dealComments(Set<DealComment> dealComments) {
        this.dealComments = dealComments;
        return this;
    }

    public Deal addDealComment(DealComment dealComment) {
        this.dealComments.add(dealComment);
        dealComment.setDeal(this);
        return this;
    }

    public Deal removeDealComment(DealComment dealComment) {
        this.dealComments.remove(dealComment);
        dealComment.setDeal(null);
        return this;
    }

    public void setDealComments(Set<DealComment> dealComments) {
        this.dealComments = dealComments;
    }

    public DealUser getAssignedTo() {
        return assignedTo;
    }

    public Deal assignedTo(DealUser dealUser) {
        this.assignedTo = dealUser;
        return this;
    }

    public void setAssignedTo(DealUser dealUser) {
        this.assignedTo = dealUser;
    }

    public Set<DealCategory> getDealCategories() {
        return dealCategories;
    }

    public Deal dealCategories(Set<DealCategory> dealCategories) {
        this.dealCategories = dealCategories;
        return this;
    }

    public Deal addDealCategory(DealCategory dealCategory) {
        this.dealCategories.add(dealCategory);
        dealCategory.getDeals().add(this);
        return this;
    }

    public Deal removeDealCategory(DealCategory dealCategory) {
        this.dealCategories.remove(dealCategory);
        dealCategory.getDeals().remove(this);
        return this;
    }

    public void setDealCategories(Set<DealCategory> dealCategories) {
        this.dealCategories = dealCategories;
    }

    public Set<DealUser> getDealUsers() {
        return dealUsers;
    }

    public Deal dealUsers(Set<DealUser> dealUsers) {
        this.dealUsers = dealUsers;
        return this;
    }

    public Deal addDealUser(DealUser dealUser) {
        this.dealUsers.add(dealUser);
        dealUser.getDealSaveds().add(this);
        return this;
    }

    public Deal removeDealUser(DealUser dealUser) {
        this.dealUsers.remove(dealUser);
        dealUser.getDealSaveds().remove(this);
        return this;
    }

    public void setDealUsers(Set<DealUser> dealUsers) {
        this.dealUsers = dealUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deal)) {
            return false;
        }
        return id != null && id.equals(((Deal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deal{" +
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
            "}";
    }
}
