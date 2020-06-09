package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealTrackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealTrack} and its DTO {@link DealTrackDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealMapper.class})
public interface DealTrackMapper extends EntityMapper<DealTrackDTO, DealTrack> {

    @Mapping(source = "deal.id", target = "dealId")
    DealTrackDTO toDto(DealTrack dealTrack);

    @Mapping(source = "dealId", target = "deal")
    DealTrack toEntity(DealTrackDTO dealTrackDTO);

    default DealTrack fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealTrack dealTrack = new DealTrack();
        dealTrack.setId(id);
        return dealTrack;
    }
}
