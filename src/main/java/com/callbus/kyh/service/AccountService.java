package com.callbus.kyh.service;

import com.callbus.kyh.dao.RedisDAO;
import com.callbus.kyh.dto.client.ClientDTO;
import com.callbus.kyh.error.DuplicatedPhoneNumberException;
import com.callbus.kyh.error.UnknownException;
import com.callbus.kyh.mapper.AccountMapper;
import com.callbus.kyh.utils.RedisKeyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private final RedisDAO redisDAO;

    @Autowired
    private final AccountMapper mapper;

    public String getCertNumber(String phoneNumber) {
        return redisDAO.getCertNumber(phoneNumber);
    }

    public String saveCertNumber(String phoneNumber) {

        String existCertNumber = redisDAO.getCertNumber(phoneNumber);
        if (!StringUtils.isEmpty(existCertNumber)) { // 키가 존재하는 경우
            redisDAO.initCertNumberExpireTime(phoneNumber);
            return existCertNumber;
        }

        String certNumber = RedisKeyFactory.generateCertNumber();
        redisDAO.saveCertNumber(phoneNumber, certNumber);
        return certNumber;
    }

    public void joinAsClient(String phoneNumber) {

        boolean isDuplicatePhoneNumber = isDuplicatePhoneNumber(phoneNumber);

        if (isDuplicatePhoneNumber) {
            throw new DuplicatedPhoneNumberException("이미 가입한 번호 입니다");
        }

        ClientDTO clientDTO = createClientDTO(phoneNumber);
        int result = mapper.joinAsClient(clientDTO);

        if (result != 1) {
            throw new UnknownException("회원가입 중 알 수 없는 에러가 발생하였습니다");
        }
    }

    /**
     * 회원가입시 번호 중복 체크
     *
     * @param phoneNumber
     * @return
     */
    private boolean isDuplicatePhoneNumber(String phoneNumber) {
        return mapper.phoneNumberCheck(phoneNumber) == 1;
    }

    private ClientDTO createClientDTO(String phoneNumber) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setPhoneNumber(phoneNumber);
        clientDTO.setPushAgree(true);
        return clientDTO;
    }
}
