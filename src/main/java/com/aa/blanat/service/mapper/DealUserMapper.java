package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealUser} and its DTO {@link DealUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, DealMapper.class})
public interface DealUserMapper extends EntityMapper<DealUserDTO, DealUser> {

    @Mapping(source = "user.id", target = "userId")
    DealUserDTO toDto(DealUser dealUser);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "removeDealSaved", ignore = true)
    DealUser toEntity(DealUserDTO dealUserDTO);

    default DealUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealUser dealUser = new DealUser();
        dealUser.setId(id);
        return dealUser;
    }
}
