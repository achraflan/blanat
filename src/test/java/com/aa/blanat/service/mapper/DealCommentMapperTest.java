package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DealCommentMapperTest {

    private DealCommentMapper dealCommentMapper;

    @BeforeEach
    public void setUp() {
        dealCommentMapper = new DealCommentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dealCommentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dealCommentMapper.fromId(null)).isNull();
    }
}
