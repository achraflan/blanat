package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealReportMapperTest {

    private DealReportMapper dealReportMapper;

    @BeforeEach
    public void setUp() {
        dealReportMapper = new DealReportMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealReportMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealReportMapper.fromId(null)).isNull();
    }
}
