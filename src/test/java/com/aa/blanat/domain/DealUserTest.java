package com.aa.blanat.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealUser.class);
        DealUser dealUser1 = new DealUser();
        dealUser1.setId(1L);
        DealUser dealUser2 = new DealUser();
        dealUser2.setId(dealUser1.getId());
        assertThat(dealUser1).isEqualTo(dealUser2);
        dealUser2.setId(2L);
        assertThat(dealUser1).isNotEqualTo(dealUser2);
        dealUser1.setId(null);
        assertThat(dealUser1).isNotEqualTo(dealUser2);
    }
}
