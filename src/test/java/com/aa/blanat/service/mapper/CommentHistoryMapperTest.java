package com.aa.blanat.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentHistoryMapperTest {

    private CommentHistoryMapper commentHistoryMapper;

    @BeforeEach
    public void setUp() {
        commentHistoryMapper = new CommentHistoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(commentHistoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(commentHistoryMapper.fromId(null)).isNull();
    }
}
