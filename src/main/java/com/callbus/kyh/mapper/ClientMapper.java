package com.callbus.kyh.mapper;

import com.callbus.kyh.dto.client.ClientDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMapper {

    ClientDTO findById(long id);

    Long findIdByPhoneNumber(String phoneNumber);
}
