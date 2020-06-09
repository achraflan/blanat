package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.CommentHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommentHistory} and its DTO {@link CommentHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {DealCommentMapper.class})
public interface CommentHistoryMapper extends EntityMapper<CommentHistoryDTO, CommentHistory> {

    @Mapping(source = "dealComment.id", target = "dealCommentId")
    CommentHistoryDTO toDto(CommentHistory commentHistory);

    @Mapping(source = "dealCommentId", target = "dealComment")
    CommentHistory toEntity(CommentHistoryDTO commentHistoryDTO);

    default CommentHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        CommentHistory commentHistory = new CommentHistory();
        commentHistory.setId(id);
        return commentHistory;
    }
}
