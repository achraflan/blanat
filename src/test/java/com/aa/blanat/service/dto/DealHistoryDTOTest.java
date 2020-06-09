package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealHistoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealHistoryDTO.class);
        DealHistoryDTO dealHistoryDTO1 = new DealHistoryDTO();
        dealHistoryDTO1.setId(1L);
        DealHistoryDTO dealHistoryDTO2 = new DealHistoryDTO();
        assertThat(dealHistoryDTO1).isNotEqualTo(dealHistoryDTO2);
        dealHistoryDTO2.setId(dealHistoryDTO1.getId());
        assertThat(dealHistoryDTO1).isEqualTo(dealHistoryDTO2);
        dealHistoryDTO2.setId(2L);
        assertThat(dealHistoryDTO1).isNotEqualTo(dealHistoryDTO2);
        dealHistoryDTO1.setId(null);
        assertThat(dealHistoryDTO1).isNotEqualTo(dealHistoryDTO2);
    }
}
