package com.aa.blanat.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealTrackTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealTrack.class);
        DealTrack dealTrack1 = new DealTrack();
        dealTrack1.setId(1L);
        DealTrack dealTrack2 = new DealTrack();
        dealTrack2.setId(dealTrack1.getId());
        assertThat(dealTrack1).isEqualTo(dealTrack2);
        dealTrack2.setId(2L);
        assertThat(dealTrack1).isNotEqualTo(dealTrack2);
        dealTrack1.setId(null);
        assertThat(dealTrack1).isNotEqualTo(dealTrack2);
    }
}
