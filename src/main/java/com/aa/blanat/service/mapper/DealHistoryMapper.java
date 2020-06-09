package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealHistory} and its DTO {@link DealHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealMapper.class})
public interface DealHistoryMapper extends EntityMapper<DealHistoryDTO, DealHistory> {

    @Mapping(source = "deal.id", target = "dealId")
    DealHistoryDTO toDto(DealHistory dealHistory);

    @Mapping(source = "dealId", target = "deal")
    DealHistory toEntity(DealHistoryDTO dealHistoryDTO);

    default DealHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealHistory dealHistory = new DealHistory();
        dealHistory.setId(id);
        return dealHistory;
    }
}
