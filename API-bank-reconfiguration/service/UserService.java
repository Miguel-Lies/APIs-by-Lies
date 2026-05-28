package com.enterprise.bank_lies.service;

import com.enterprise.bank_lies.configs.AccessToken;
import com.enterprise.bank_lies.dto.UserDTO;
import com.enterprise.bank_lies.entity.AddressOfUser;
import com.enterprise.bank_lies.entity.UserTable;
import com.enterprise.bank_lies.exceptions.InvalidTokenException;
import com.enterprise.bank_lies.exceptions.NotFoundUserException;
import com.enterprise.bank_lies.repository.AccountUserRepository;
import com.enterprise.bank_lies.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    AccountUserRepository repositoryAccountUser;

    @Autowired
    AddressRepository repositoryAddress;

    @Autowired
    AccessToken accessToken;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void toCreateAccount(UserDTO userDTO) {
        UserTable userTable = new UserTable();
        AddressOfUser addressOfUser = new AddressOfUser();

        userTable.setName(userDTO.getName());
        userTable.setEmail(userDTO.getEmail());
        userTable.setNumberOfUser(userDTO.getNumberOfUser());
        userTable.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userTable.setDateOfBirth(userDTO.getDateOfBirth());
        addressOfUser.setCountryOfOrigin(userDTO.getCountry());
        addressOfUser.setState(userDTO.getState());
        addressOfUser.setCity(userDTO.getCity());
        addressOfUser.setNeighbordhood(userDTO.getNeighborhood());
        addressOfUser.setHouseNumber(userDTO.getNumber());

        userTable = repositoryAccountUser.save(userTable);

        addressOfUser.setUserIdWithAddress(userTable);

        repositoryAddress.save(addressOfUser);
    }

    @Transactional
    public void deleteInformation(String token) {
        if (!accessToken.isTokenValid(token)){
            throw new InvalidTokenException("Invalid token, try again.");
        }
        String username = accessToken.getUsername(token);
        UserTable user = repositoryAccountUser.findByEmail(username)
                .orElseThrow(() -> new NotFoundUserException("Not found user."));

        repositoryAccountUser.delete(user);
    }
}
