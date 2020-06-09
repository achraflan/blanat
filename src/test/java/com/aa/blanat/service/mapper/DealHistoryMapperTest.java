package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealHistoryMapperTest {

    private DealHistoryMapper dealHistoryMapper;

    @BeforeEach
    public void setUp() {
        dealHistoryMapper = new DealHistoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealHistoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealHistoryMapper.fromId(null)).isNull();
    }
}
