package com.aa.blanat.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealLocationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealLocation.class);
        DealLocation dealLocation1 = new DealLocation();
        dealLocation1.setId(1L);
        DealLocation dealLocation2 = new DealLocation();
        dealLocation2.setId(dealLocation1.getId());
        assertThat(dealLocation1).isEqualTo(dealLocation2);
        dealLocation2.setId(2L);
        assertThat(dealLocation1).isNotEqualTo(dealLocation2);
        dealLocation1.setId(null);
        assertThat(dealLocation1).isNotEqualTo(dealLocation2);
    }
}
