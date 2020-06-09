package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealReportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealReport} and its DTO {@link DealReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealUserMapper.class, DealMapper.class})
public interface DealReportMapper extends EntityMapper<DealReportDTO, DealReport> {

    @Mapping(source = "assignedTo.id", target = "assignedToId")
    @Mapping(source = "deal.id", target = "dealId")
    DealReportDTO toDto(DealReport dealReport);

    @Mapping(source = "assignedToId", target = "assignedTo")
    @Mapping(source = "dealId", target = "deal")
    DealReport toEntity(DealReportDTO dealReportDTO);

    default DealReport fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealReport dealReport = new DealReport();
        dealReport.setId(id);
        return dealReport;
    }
}
