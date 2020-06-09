package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealUserDTO.class);
        DealUserDTO dealUserDTO1 = new DealUserDTO();
        dealUserDTO1.setId(1L);
        DealUserDTO dealUserDTO2 = new DealUserDTO();
        assertThat(dealUserDTO1).isNotEqualTo(dealUserDTO2);
        dealUserDTO2.setId(dealUserDTO1.getId());
        assertThat(dealUserDTO1).isEqualTo(dealUserDTO2);
        dealUserDTO2.setId(2L);
        assertThat(dealUserDTO1).isNotEqualTo(dealUserDTO2);
        dealUserDTO1.setId(null);
        assertThat(dealUserDTO1).isNotEqualTo(dealUserDTO2);
    }
}
