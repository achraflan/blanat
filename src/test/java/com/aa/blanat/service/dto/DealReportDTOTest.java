package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealReportDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealReportDTO.class);
        DealReportDTO dealReportDTO1 = new DealReportDTO();
        dealReportDTO1.setId(1L);
        DealReportDTO dealReportDTO2 = new DealReportDTO();
        assertThat(dealReportDTO1).isNotEqualTo(dealReportDTO2);
        dealReportDTO2.setId(dealReportDTO1.getId());
        assertThat(dealReportDTO1).isEqualTo(dealReportDTO2);
        dealReportDTO2.setId(2L);
        assertThat(dealReportDTO1).isNotEqualTo(dealReportDTO2);
        dealReportDTO1.setId(null);
        assertThat(dealReportDTO1).isNotEqualTo(dealReportDTO2);
    }
}
