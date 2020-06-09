package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class CommentHistoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommentHistoryDTO.class);
        CommentHistoryDTO commentHistoryDTO1 = new CommentHistoryDTO();
        commentHistoryDTO1.setId(1L);
        CommentHistoryDTO commentHistoryDTO2 = new CommentHistoryDTO();
        assertThat(commentHistoryDTO1).isNotEqualTo(commentHistoryDTO2);
        commentHistoryDTO2.setId(commentHistoryDTO1.getId());
        assertThat(commentHistoryDTO1).isEqualTo(commentHistoryDTO2);
        commentHistoryDTO2.setId(2L);
        assertThat(commentHistoryDTO1).isNotEqualTo(commentHistoryDTO2);
        commentHistoryDTO1.setId(null);
        assertThat(commentHistoryDTO1).isNotEqualTo(commentHistoryDTO2);
    }
}
