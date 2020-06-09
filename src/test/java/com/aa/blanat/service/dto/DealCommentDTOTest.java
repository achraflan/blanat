package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealCommentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealCommentDTO.class);
        DealCommentDTO dealCommentDTO1 = new DealCommentDTO();
        dealCommentDTO1.setId(1L);
        DealCommentDTO dealCommentDTO2 = new DealCommentDTO();
        assertThat(dealCommentDTO1).isNotEqualTo(dealCommentDTO2);
        dealCommentDTO2.setId(dealCommentDTO1.getId());
        assertThat(dealCommentDTO1).isEqualTo(dealCommentDTO2);
        dealCommentDTO2.setId(2L);
        assertThat(dealCommentDTO1).isNotEqualTo(dealCommentDTO2);
        dealCommentDTO1.setId(null);
        assertThat(dealCommentDTO1).isNotEqualTo(dealCommentDTO2);
    }
}
