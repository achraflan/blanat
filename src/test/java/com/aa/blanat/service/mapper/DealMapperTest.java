package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealMapperTest {

    private DealMapper dealMapper;

    @BeforeEach
    public void setUp() {
        dealMapper = new DealMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealMapper.fromId(null)).isNull();
    }
}
