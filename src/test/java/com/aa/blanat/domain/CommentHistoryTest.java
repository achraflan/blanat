package com.aa.blanat.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class CommentHistoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommentHistory.class);
        CommentHistory commentHistory1 = new CommentHistory();
        commentHistory1.setId(1L);
        CommentHistory commentHistory2 = new CommentHistory();
        commentHistory2.setId(commentHistory1.getId());
        assertThat(commentHistory1).isEqualTo(commentHistory2);
        commentHistory2.setId(2L);
        assertThat(commentHistory1).isNotEqualTo(commentHistory2);
        commentHistory1.setId(null);
        assertThat(commentHistory1).isNotEqualTo(commentHistory2);
    }
}
