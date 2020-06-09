package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealUserMapperTest {

    private DealUserMapper dealUserMapper;

    @BeforeEach
    public void setUp() {
        dealUserMapper = new DealUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealUserMapper.fromId(null)).isNull();
    }
}
