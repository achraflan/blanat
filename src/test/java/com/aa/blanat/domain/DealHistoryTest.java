package com.aa.blanat.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealHistoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealHistory.class);
        DealHistory dealHistory1 = new DealHistory();
        dealHistory1.setId(1L);
        DealHistory dealHistory2 = new DealHistory();
        dealHistory2.setId(dealHistory1.getId());
        assertThat(dealHistory1).isEqualTo(dealHistory2);
        dealHistory2.setId(2L);
        assertThat(dealHistory1).isNotEqualTo(dealHistory2);
        dealHistory1.setId(null);
        assertThat(dealHistory1).isNotEqualTo(dealHistory2);
    }
}
