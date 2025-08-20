package com.spring.resturant.mapper;


import com.spring.resturant.dto.ContactInfoDto;
import com.spring.resturant.models.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactInfoMapper {

    ContactInfoMapper INSTANCE = Mappers.getMapper(ContactInfoMapper.class);

    List<ContactInfoDto> toContactInfoDtoList(List<ContactInfo> contactInfoList);
}
