package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealLocationMapperTest {

    private DealLocationMapper dealLocationMapper;

    @BeforeEach
    public void setUp() {
        dealLocationMapper = new DealLocationMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealLocationMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealLocationMapper.fromId(null)).isNull();
    }
}
