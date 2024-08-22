package com.springboot.jwt.service.serviceImpl;

import com.springboot.jwt.dto.ResponseDTO;
import com.springboot.jwt.dto.UserInformationDTO;
import com.springboot.jwt.model.UserInformation;
import com.springboot.jwt.repository.UserInformationRepo;
import com.springboot.jwt.service.UserInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    private final ModelMapper mapper;

    private final UserInformationRepo userInformationRepo;

    public UserInformationServiceImpl(ModelMapper mapper, UserInformationRepo userInformationRepo) {
        this.mapper = mapper;
        this.userInformationRepo = userInformationRepo;
    }


    @Override
    public ResponseDTO postUser(UserInformationDTO userInformationDTO) throws NoSuchAlgorithmException {
        System.out.println("userInformationDTO = " + userInformationDTO);
        String encryptedPass = null;
        ResponseDTO responseDTO = new ResponseDTO();
        UserInformation userInformation = maptoEntity(userInformationDTO);
        encryptedPass = customHash((String) userInformationDTO.getUserId(), (String) userInformationDTO.getPassword());
        System.out.println("encryptedPass = " + encryptedPass);
        userInformation.setPassword(encryptedPass);
        System.out.println("userInformation = " + userInformation);

        try{
            UserInformation userInformation1 = userInformationRepo.save(userInformation);
            responseDTO.setResponseCode(1);
            responseDTO.setResponseMessage("SuccessFull");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return responseDTO;

    }


    String customHash(String val1, String val2) throws NoSuchAlgorithmException {
        String salt1 = "3T9T4QN423QC";
        String salt2 = "TMF0T9B3PN";
        String salt3 = "X6IW04WI";
        String salt = salt1 + salt3 + salt2;

        int lenVal1 = (int) Math.round(val1.length() / 2.0);
        int lenVal2 = (int) Math.round(val2.length() / 2.0);

        String inputString = salt.substring(19, 27) +  // SUBSTR(L_SALT, 20, 8)
                val2.substring(0, lenVal2) +
                salt.substring(9, 14) +    // SUBSTR(L_SALT, 10, 5)
                val2.substring(lenVal2) +
                salt.substring(14, 19) +   // SUBSTR(L_SALT, 15, 5)
                val1.substring(0, lenVal1) +
                salt.substring(3, 13) +    // SUBSTR(L_SALT, 4, 10)
                val1.substring(lenVal1);

        // Generate the MD5 hash
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(inputString.getBytes(StandardCharsets.UTF_8));

        // Convert the hash to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        System.out.println("hexString.substring(0, 20).toUpperCase() = " + hexString.substring(0, 20).toUpperCase());

        // Return the first 20 characters of the hash in uppercase
        return hexString.substring(0, 20).toUpperCase();
    }


    private UserInformation maptoEntity(UserInformationDTO userInformationDTO) {
        UserInformation userInformation = mapper.map(userInformationDTO, UserInformation.class);
        return userInformation;
    }
}
