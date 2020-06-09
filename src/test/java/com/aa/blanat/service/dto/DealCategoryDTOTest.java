package com.aa.blanat.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.aa.blanat.web.rest.TestUtil;

public class DealCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DealCategoryDTO.class);
        DealCategoryDTO dealCategoryDTO1 = new DealCategoryDTO();
        dealCategoryDTO1.setId(1L);
        DealCategoryDTO dealCategoryDTO2 = new DealCategoryDTO();
        assertThat(dealCategoryDTO1).isNotEqualTo(dealCategoryDTO2);
        dealCategoryDTO2.setId(dealCategoryDTO1.getId());
        assertThat(dealCategoryDTO1).isEqualTo(dealCategoryDTO2);
        dealCategoryDTO2.setId(2L);
        assertThat(dealCategoryDTO1).isNotEqualTo(dealCategoryDTO2);
        dealCategoryDTO1.setId(null);
        assertThat(dealCategoryDTO1).isNotEqualTo(dealCategoryDTO2);
    }
}
