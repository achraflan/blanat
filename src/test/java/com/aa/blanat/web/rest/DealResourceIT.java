package com.aa.blanat.web.rest;

import com.aa.blanat.BlanatApp;
import com.aa.blanat.domain.Deal;
import com.aa.blanat.domain.DealUser;
import com.aa.blanat.repository.DealRepository;
import com.aa.blanat.service.DealService;
import com.aa.blanat.service.dto.DealDTO;
import com.aa.blanat.service.mapper.DealMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aa.blanat.domain.enumeration.TypeDeal;
import com.aa.blanat.domain.enumeration.TypeCoupon;
import com.aa.blanat.domain.enumeration.StatutDeal;
/**
 * Integration tests for the {@link DealResource} REST controller.
 */
@SpringBootTest(classes = BlanatApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class DealResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final TypeDeal DEFAULT_TYPE = TypeDeal.DEAL;
    private static final TypeDeal UPDATED_TYPE = TypeDeal.COUPON;

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE = "BBBBBBBBBB";

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;

    private static final Float DEFAULT_PRICE_NORMAL = 1F;
    private static final Float UPDATED_PRICE_NORMAL = 2F;

    private static final Float DEFAULT_PRICE_SHIPPING = 1F;
    private static final Float UPDATED_PRICE_SHIPPING = 2F;

    private static final String DEFAULT_COUPON = "AAAAAAAAAA";
    private static final String UPDATED_COUPON = "BBBBBBBBBB";

    private static final TypeCoupon DEFAULT_COUPON_TYPE = TypeCoupon.PERCENTAGE;
    private static final TypeCoupon UPDATED_COUPON_TYPE = TypeCoupon.DHS;

    private static final Float DEFAULT_COUPON_VALUE = 1F;
    private static final Float UPDATED_COUPON_VALUE = 2F;

    private static final Integer DEFAULT_COUPON_PERCENTAGE = 1;
    private static final Integer UPDATED_COUPON_PERCENTAGE = 2;

    private static final LocalDate DEFAULT_DATE_START = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_START = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_END = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_END = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_PUBLICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PUBLICATION = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_VIEWS = 1L;
    private static final Long UPDATED_VIEWS = 2L;

    private static final Long DEFAULT_LIKE = 1L;
    private static final Long UPDATED_LIKE = 2L;

    private static final Long DEFAULT_DISLIKE = 1L;
    private static final Long UPDATED_DISLIKE = 2L;

    private static final Boolean DEFAULT_LOCAL = false;
    private static final Boolean UPDATED_LOCAL = true;

    private static final StatutDeal DEFAULT_STATUT = StatutDeal.ACTIVE;
    private static final StatutDeal UPDATED_STATUT = StatutDeal.HIDDEN;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final Boolean DEFAULT_IS_BLOCKED = false;
    private static final Boolean UPDATED_IS_BLOCKED = true;

    @Autowired
    private DealRepository dealRepository;

    @Mock
    private DealRepository dealRepositoryMock;

    @Autowired
    private DealMapper dealMapper;

    @Mock
    private DealService dealServiceMock;

    @Autowired
    private DealService dealService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDealMockMvc;

    private Deal deal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deal createEntity(EntityManager em) {
        Deal deal = new Deal()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .url(DEFAULT_URL)
            .image(DEFAULT_IMAGE)
            .price(DEFAULT_PRICE)
            .priceNormal(DEFAULT_PRICE_NORMAL)
            .priceShipping(DEFAULT_PRICE_SHIPPING)
            .coupon(DEFAULT_COUPON)
            .couponType(DEFAULT_COUPON_TYPE)
            .couponValue(DEFAULT_COUPON_VALUE)
            .couponPercentage(DEFAULT_COUPON_PERCENTAGE)
            .dateStart(DEFAULT_DATE_START)
            .dateEnd(DEFAULT_DATE_END)
            .datePublication(DEFAULT_DATE_PUBLICATION)
            .views(DEFAULT_VIEWS)
            .like(DEFAULT_LIKE)
            .dislike(DEFAULT_DISLIKE)
            .local(DEFAULT_LOCAL)
            .statut(DEFAULT_STATUT)
            .isDeleted(DEFAULT_IS_DELETED)
            .isBlocked(DEFAULT_IS_BLOCKED);
        // Add required entity
        DealUser dealUser;
        if (TestUtil.findAll(em, DealUser.class).isEmpty()) {
            dealUser = DealUserResourceIT.createEntity(em);
            em.persist(dealUser);
            em.flush();
        } else {
            dealUser = TestUtil.findAll(em, DealUser.class).get(0);
        }
        deal.setAssignedTo(dealUser);
        return deal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deal createUpdatedEntity(EntityManager em) {
        Deal deal = new Deal()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .url(UPDATED_URL)
            .image(UPDATED_IMAGE)
            .price(UPDATED_PRICE)
            .priceNormal(UPDATED_PRICE_NORMAL)
            .priceShipping(UPDATED_PRICE_SHIPPING)
            .coupon(UPDATED_COUPON)
            .couponType(UPDATED_COUPON_TYPE)
            .couponValue(UPDATED_COUPON_VALUE)
            .couponPercentage(UPDATED_COUPON_PERCENTAGE)
            .dateStart(UPDATED_DATE_START)
            .dateEnd(UPDATED_DATE_END)
            .datePublication(UPDATED_DATE_PUBLICATION)
            .views(UPDATED_VIEWS)
            .like(UPDATED_LIKE)
            .dislike(UPDATED_DISLIKE)
            .local(UPDATED_LOCAL)
            .statut(UPDATED_STATUT)
            .isDeleted(UPDATED_IS_DELETED)
            .isBlocked(UPDATED_IS_BLOCKED);
        // Add required entity
        DealUser dealUser;
        if (TestUtil.findAll(em, DealUser.class).isEmpty()) {
            dealUser = DealUserResourceIT.createUpdatedEntity(em);
            em.persist(dealUser);
            em.flush();
        } else {
            dealUser = TestUtil.findAll(em, DealUser.class).get(0);
        }
        deal.setAssignedTo(dealUser);
        return deal;
    }

    @BeforeEach
    public void initTest() {
        deal = createEntity(em);
    }

    @Test
    @Transactional
    public void createDeal() throws Exception {
        int databaseSizeBeforeCreate = dealRepository.findAll().size();
        // Create the Deal
        DealDTO dealDTO = dealMapper.toDto(deal);
        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isCreated());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeCreate + 1);
        Deal testDeal = dealList.get(dealList.size() - 1);
        assertThat(testDeal.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testDeal.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDeal.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testDeal.getUrl()).isEqualTo(DEFAULT_URL);
        assertThat(testDeal.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testDeal.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testDeal.getPriceNormal()).isEqualTo(DEFAULT_PRICE_NORMAL);
        assertThat(testDeal.getPriceShipping()).isEqualTo(DEFAULT_PRICE_SHIPPING);
        assertThat(testDeal.getCoupon()).isEqualTo(DEFAULT_COUPON);
        assertThat(testDeal.getCouponType()).isEqualTo(DEFAULT_COUPON_TYPE);
        assertThat(testDeal.getCouponValue()).isEqualTo(DEFAULT_COUPON_VALUE);
        assertThat(testDeal.getCouponPercentage()).isEqualTo(DEFAULT_COUPON_PERCENTAGE);
        assertThat(testDeal.getDateStart()).isEqualTo(DEFAULT_DATE_START);
        assertThat(testDeal.getDateEnd()).isEqualTo(DEFAULT_DATE_END);
        assertThat(testDeal.getDatePublication()).isEqualTo(DEFAULT_DATE_PUBLICATION);
        assertThat(testDeal.getViews()).isEqualTo(DEFAULT_VIEWS);
        assertThat(testDeal.getLike()).isEqualTo(DEFAULT_LIKE);
        assertThat(testDeal.getDislike()).isEqualTo(DEFAULT_DISLIKE);
        assertThat(testDeal.isLocal()).isEqualTo(DEFAULT_LOCAL);
        assertThat(testDeal.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testDeal.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testDeal.isIsBlocked()).isEqualTo(DEFAULT_IS_BLOCKED);
    }

    @Test
    @Transactional
    public void createDealWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dealRepository.findAll().size();

        // Create the Deal with an existing ID
        deal.setId(1L);
        DealDTO dealDTO = dealMapper.toDto(deal);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealRepository.findAll().size();
        // set the field null
        deal.setTitle(null);

        // Create the Deal, which fails.
        DealDTO dealDTO = dealMapper.toDto(deal);


        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isBadRequest());

        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealRepository.findAll().size();
        // set the field null
        deal.setDescription(null);

        // Create the Deal, which fails.
        DealDTO dealDTO = dealMapper.toDto(deal);


        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isBadRequest());

        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealRepository.findAll().size();
        // set the field null
        deal.setType(null);

        // Create the Deal, which fails.
        DealDTO dealDTO = dealMapper.toDto(deal);


        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isBadRequest());

        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatePublicationIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealRepository.findAll().size();
        // set the field null
        deal.setDatePublication(null);

        // Create the Deal, which fails.
        DealDTO dealDTO = dealMapper.toDto(deal);


        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isBadRequest());

        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatutIsRequired() throws Exception {
        int databaseSizeBeforeTest = dealRepository.findAll().size();
        // set the field null
        deal.setStatut(null);

        // Create the Deal, which fails.
        DealDTO dealDTO = dealMapper.toDto(deal);


        restDealMockMvc.perform(post("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isBadRequest());

        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDeals() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        // Get all the dealList
        restDealMockMvc.perform(get("/api/deals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deal.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].priceNormal").value(hasItem(DEFAULT_PRICE_NORMAL.doubleValue())))
            .andExpect(jsonPath("$.[*].priceShipping").value(hasItem(DEFAULT_PRICE_SHIPPING.doubleValue())))
            .andExpect(jsonPath("$.[*].coupon").value(hasItem(DEFAULT_COUPON)))
            .andExpect(jsonPath("$.[*].couponType").value(hasItem(DEFAULT_COUPON_TYPE.toString())))
            .andExpect(jsonPath("$.[*].couponValue").value(hasItem(DEFAULT_COUPON_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].couponPercentage").value(hasItem(DEFAULT_COUPON_PERCENTAGE)))
            .andExpect(jsonPath("$.[*].dateStart").value(hasItem(DEFAULT_DATE_START.toString())))
            .andExpect(jsonPath("$.[*].dateEnd").value(hasItem(DEFAULT_DATE_END.toString())))
            .andExpect(jsonPath("$.[*].datePublication").value(hasItem(DEFAULT_DATE_PUBLICATION.toString())))
            .andExpect(jsonPath("$.[*].views").value(hasItem(DEFAULT_VIEWS.intValue())))
            .andExpect(jsonPath("$.[*].like").value(hasItem(DEFAULT_LIKE.intValue())))
            .andExpect(jsonPath("$.[*].dislike").value(hasItem(DEFAULT_DISLIKE.intValue())))
            .andExpect(jsonPath("$.[*].local").value(hasItem(DEFAULT_LOCAL.booleanValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].isBlocked").value(hasItem(DEFAULT_IS_BLOCKED.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllDealsWithEagerRelationshipsIsEnabled() throws Exception {
        when(dealServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDealMockMvc.perform(get("/api/deals?eagerload=true"))
            .andExpect(status().isOk());

        verify(dealServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllDealsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(dealServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDealMockMvc.perform(get("/api/deals?eagerload=true"))
            .andExpect(status().isOk());

        verify(dealServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        // Get the deal
        restDealMockMvc.perform(get("/api/deals/{id}", deal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deal.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL))
            .andExpect(jsonPath("$.image").value(DEFAULT_IMAGE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.priceNormal").value(DEFAULT_PRICE_NORMAL.doubleValue()))
            .andExpect(jsonPath("$.priceShipping").value(DEFAULT_PRICE_SHIPPING.doubleValue()))
            .andExpect(jsonPath("$.coupon").value(DEFAULT_COUPON))
            .andExpect(jsonPath("$.couponType").value(DEFAULT_COUPON_TYPE.toString()))
            .andExpect(jsonPath("$.couponValue").value(DEFAULT_COUPON_VALUE.doubleValue()))
            .andExpect(jsonPath("$.couponPercentage").value(DEFAULT_COUPON_PERCENTAGE))
            .andExpect(jsonPath("$.dateStart").value(DEFAULT_DATE_START.toString()))
            .andExpect(jsonPath("$.dateEnd").value(DEFAULT_DATE_END.toString()))
            .andExpect(jsonPath("$.datePublication").value(DEFAULT_DATE_PUBLICATION.toString()))
            .andExpect(jsonPath("$.views").value(DEFAULT_VIEWS.intValue()))
            .andExpect(jsonPath("$.like").value(DEFAULT_LIKE.intValue()))
            .andExpect(jsonPath("$.dislike").value(DEFAULT_DISLIKE.intValue()))
            .andExpect(jsonPath("$.local").value(DEFAULT_LOCAL.booleanValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.isBlocked").value(DEFAULT_IS_BLOCKED.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDeal() throws Exception {
        // Get the deal
        restDealMockMvc.perform(get("/api/deals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        int databaseSizeBeforeUpdate = dealRepository.findAll().size();

        // Update the deal
        Deal updatedDeal = dealRepository.findById(deal.getId()).get();
        // Disconnect from session so that the updates on updatedDeal are not directly saved in db
        em.detach(updatedDeal);
        updatedDeal
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .url(UPDATED_URL)
            .image(UPDATED_IMAGE)
            .price(UPDATED_PRICE)
            .priceNormal(UPDATED_PRICE_NORMAL)
            .priceShipping(UPDATED_PRICE_SHIPPING)
            .coupon(UPDATED_COUPON)
            .couponType(UPDATED_COUPON_TYPE)
            .couponValue(UPDATED_COUPON_VALUE)
            .couponPercentage(UPDATED_COUPON_PERCENTAGE)
            .dateStart(UPDATED_DATE_START)
            .dateEnd(UPDATED_DATE_END)
            .datePublication(UPDATED_DATE_PUBLICATION)
            .views(UPDATED_VIEWS)
            .like(UPDATED_LIKE)
            .dislike(UPDATED_DISLIKE)
            .local(UPDATED_LOCAL)
            .statut(UPDATED_STATUT)
            .isDeleted(UPDATED_IS_DELETED)
            .isBlocked(UPDATED_IS_BLOCKED);
        DealDTO dealDTO = dealMapper.toDto(updatedDeal);

        restDealMockMvc.perform(put("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isOk());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeUpdate);
        Deal testDeal = dealList.get(dealList.size() - 1);
        assertThat(testDeal.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testDeal.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDeal.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testDeal.getUrl()).isEqualTo(UPDATED_URL);
        assertThat(testDeal.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testDeal.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testDeal.getPriceNormal()).isEqualTo(UPDATED_PRICE_NORMAL);
        assertThat(testDeal.getPriceShipping()).isEqualTo(UPDATED_PRICE_SHIPPING);
        assertThat(testDeal.getCoupon()).isEqualTo(UPDATED_COUPON);
        assertThat(testDeal.getCouponType()).isEqualTo(UPDATED_COUPON_TYPE);
        assertThat(testDeal.getCouponValue()).isEqualTo(UPDATED_COUPON_VALUE);
        assertThat(testDeal.getCouponPercentage()).isEqualTo(UPDATED_COUPON_PERCENTAGE);
        assertThat(testDeal.getDateStart()).isEqualTo(UPDATED_DATE_START);
        assertThat(testDeal.getDateEnd()).isEqualTo(UPDATED_DATE_END);
        assertThat(testDeal.getDatePublication()).isEqualTo(UPDATED_DATE_PUBLICATION);
        assertThat(testDeal.getViews()).isEqualTo(UPDATED_VIEWS);
        assertThat(testDeal.getLike()).isEqualTo(UPDATED_LIKE);
        assertThat(testDeal.getDislike()).isEqualTo(UPDATED_DISLIKE);
        assertThat(testDeal.isLocal()).isEqualTo(UPDATED_LOCAL);
        assertThat(testDeal.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testDeal.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testDeal.isIsBlocked()).isEqualTo(UPDATED_IS_BLOCKED);
    }

    @Test
    @Transactional
    public void updateNonExistingDeal() throws Exception {
        int databaseSizeBeforeUpdate = dealRepository.findAll().size();

        // Create the Deal
        DealDTO dealDTO = dealMapper.toDto(deal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDealMockMvc.perform(put("/api/deals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dealDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Deal in the database
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDeal() throws Exception {
        // Initialize the database
        dealRepository.saveAndFlush(deal);

        int databaseSizeBeforeDelete = dealRepository.findAll().size();

        // Delete the deal
        restDealMockMvc.perform(delete("/api/deals/{id}", deal.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Deal> dealList = dealRepository.findAll();
        assertThat(dealList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
