package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealTrackMapperTest {

    private DealTrackMapper dealTrackMapper;

    @BeforeEach
    public void setUp() {
        dealTrackMapper = new DealTrackMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealTrackMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealTrackMapper.fromId(null)).isNull();
    }
}
