package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealCommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealComment} and its DTO {@link DealCommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealUserMapper.class, DealMapper.class})
public interface DealCommentMapper extends EntityMapper<DealCommentDTO, DealComment> {

    @Mapping(source = "assignedTo.id", target = "assignedToId")
    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(source = "deal.id", target = "dealId")
    DealCommentDTO toDto(DealComment dealComment);

    @Mapping(target = "dealHistories", ignore = true)
    @Mapping(target = "removeDealHistory", ignore = true)
    @Mapping(source = "assignedToId", target = "assignedTo")
    @Mapping(source = "parentId", target = "parent")
    @Mapping(source = "dealId", target = "deal")
    DealComment toEntity(DealCommentDTO dealCommentDTO);

    default DealComment fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealComment dealComment = new DealComment();
        dealComment.setId(id);
        return dealComment;
    }
}
