package com.aa.blanat.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealCommentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealComment.class);
        DealComment dealComment1 = new DealComment();
        dealComment1.setId(1L);
        DealComment dealComment2 = new DealComment();
        dealComment2.setId(dealComment1.getId());
        assertThat(dealComment1).isEqualTo(dealComment2);
        dealComment2.setId(2L);
        assertThat(dealComment1).isNotEqualTo(dealComment2);
        dealComment1.setId(null);
        assertThat(dealComment1).isNotEqualTo(dealComment2);
    }
}
