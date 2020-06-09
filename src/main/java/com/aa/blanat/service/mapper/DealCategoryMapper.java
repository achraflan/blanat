package com.aa.blanat.service.mapper;


import com.aa.blanat.domain.*;
import com.aa.blanat.service.dto.DealCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DealCategory} and its DTO {@link DealCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DealCategoryMapper extends EntityMapper<DealCategoryDTO, DealCategory> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    DealCategoryDTO toDto(DealCategory dealCategory);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(target = "deals", ignore = true)
    @Mapping(target = "removeDeal", ignore = true)
    DealCategory toEntity(DealCategoryDTO dealCategoryDTO);

    default DealCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        DealCategory dealCategory = new DealCategory();
        dealCategory.setId(id);
        return dealCategory;
    }
}
