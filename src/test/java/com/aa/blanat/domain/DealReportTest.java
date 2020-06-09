package com.aa.blanat.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealReportTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealReport.class);
        DealReport dealReport1 = new DealReport();
        dealReport1.setId(1L);
        DealReport dealReport2 = new DealReport();
        dealReport2.setId(dealReport1.getId());
        assertThat(dealReport1).isEqualTo(dealReport2);
        dealReport2.setId(2L);
        assertThat(dealReport1).isNotEqualTo(dealReport2);
        dealReport1.setId(null);
        assertThat(dealReport1).isNotEqualTo(dealReport2);
    }
}
