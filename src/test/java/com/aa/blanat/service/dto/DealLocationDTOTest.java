package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealLocationDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealLocationDTO.class);
        DealLocationDTO dealLocationDTO1 = new DealLocationDTO();
        dealLocationDTO1.setId(1L);
        DealLocationDTO dealLocationDTO2 = new DealLocationDTO();
        assertThat(dealLocationDTO1).isNotEqualTo(dealLocationDTO2);
        dealLocationDTO2.setId(dealLocationDTO1.getId());
        assertThat(dealLocationDTO1).isEqualTo(dealLocationDTO2);
        dealLocationDTO2.setId(2L);
        assertThat(dealLocationDTO1).isNotEqualTo(dealLocationDTO2);
        dealLocationDTO1.setId(null);
        assertThat(dealLocationDTO1).isNotEqualTo(dealLocationDTO2);
    }
}
