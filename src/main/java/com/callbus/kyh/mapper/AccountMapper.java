package com.callbus.kyh.mapper;

import com.callbus.kyh.dto.client.ClientDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {

    ClientDTO findById(int id);

    int joinAsClient(ClientDTO clientDTO);

    int phoneNumberCheck(String phoneNumber);
}
