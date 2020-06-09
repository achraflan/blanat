package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Deal} and its DTO {@link DealDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealLocationMapper.class, DealUserMapper.class, DealCategoryMapper.class})
public interface DealMapper extends EntityMapper<DealDTO, Deal> {

    @Mapping(source = "dealLocation.id", target = "dealLocationId")
    @Mapping(source = "assignedTo.id", target = "assignedToId")
    DealDTO toDto(Deal deal);

    @Mapping(source = "dealLocationId", target = "dealLocation")
    @Mapping(target = "dealHistories", ignore = true)
    @Mapping(target = "removeDealHistory", ignore = true)
    @Mapping(target = "dealTracks", ignore = true)
    @Mapping(target = "removeDealTrack", ignore = true)
    @Mapping(target = "dealReports", ignore = true)
    @Mapping(target = "removeDealReport", ignore = true)
    @Mapping(target = "dealComments", ignore = true)
    @Mapping(target = "removeDealComment", ignore = true)
    @Mapping(source = "assignedToId", target = "assignedTo")
    @Mapping(target = "removeDealCategory", ignore = true)
    @Mapping(target = "dealUsers", ignore = true)
    @Mapping(target = "removeDealUser", ignore = true)
    Deal toEntity(DealDTO dealDTO);

    default Deal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Deal deal = new Deal();
        deal.setId(id);
        return deal;
    }
}
