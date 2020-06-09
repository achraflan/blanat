package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealCategoryMapperTest {

    private DealCategoryMapper dealCategoryMapper;

    @BeforeEach
    public void setUp() {
        dealCategoryMapper = new DealCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealCategoryMapper.fromId(null)).isNull();
    }
}
