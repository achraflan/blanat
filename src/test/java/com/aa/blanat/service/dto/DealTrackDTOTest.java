package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealTrackDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealTrackDTO.class);
        DealTrackDTO dealTrackDTO1 = new DealTrackDTO();
        dealTrackDTO1.setId(1L);
        DealTrackDTO dealTrackDTO2 = new DealTrackDTO();
        assertThat(dealTrackDTO1).isNotEqualTo(dealTrackDTO2);
        dealTrackDTO2.setId(dealTrackDTO1.getId());
        assertThat(dealTrackDTO1).isEqualTo(dealTrackDTO2);
        dealTrackDTO2.setId(2L);
        assertThat(dealTrackDTO1).isNotEqualTo(dealTrackDTO2);
        dealTrackDTO1.setId(null);
        assertThat(dealTrackDTO1).isNotEqualTo(dealTrackDTO2);
    }
}
