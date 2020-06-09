package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealLocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealLocation} and its DTO {@link DealLocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DealLocationMapper extends EntityMapper<DealLocationDTO, DealLocation> {



    default DealLocation fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealLocation dealLocation = new DealLocation();
        dealLocation.setId(id);
        return dealLocation;
    }
}
