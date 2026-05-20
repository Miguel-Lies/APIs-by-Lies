package com.enterprise.bank_lies.service;

import com.enterprise.bank_lies.dto.UserDTO;
import com.enterprise.bank_lies.entity.AddressOfUser;
import com.enterprise.bank_lies.entity.BankDataAccountUser;
import com.enterprise.bank_lies.exceptions.NotFoundUserException;
import com.enterprise.bank_lies.repository.AccountUserRepository;
import com.enterprise.bank_lies.repository.AddressRepository;
import com.enterprise.bank_lies.configs.AccessToken;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    AccessToken acessCode;

    @Transactional
    public void toCreateAccount(UserDTO userDTO) {
        BankDataAccountUser bankDataAccountUser = new BankDataAccountUser();
        AddressOfUser addressOfUser = new AddressOfUser();

        bankDataAccountUser.setName(userDTO.getName());
        bankDataAccountUser.setEmail(userDTO.getEmail());
        bankDataAccountUser.setNumberOfUser(userDTO.getNumberOfUser());
        bankDataAccountUser.setPassword(userDTO.getPassword());
        bankDataAccountUser.setDateOfBirth(userDTO.getDateOfBirth());
        addressOfUser.setCountryOfOrigin(userDTO.getCountry());
        addressOfUser.setState(userDTO.getState());
        addressOfUser.setCity(userDTO.getCity());
        addressOfUser.setNeighbordhood(userDTO.getNeighborhood());
        addressOfUser.setHouseNumber(userDTO.getNumber());

        bankDataAccountUser = repositoryAccountUser.save(bankDataAccountUser);

        addressOfUser.setUserIdWithAddress(bankDataAccountUser);

        repositoryAddress.save(addressOfUser);
    }

    @Transactional
    public void deleteInformation(String token) {
        String username = acessCode.getUsername(token);
        BankDataAccountUser user = repositoryAccountUser.findByEmail(username)
                .orElseThrow(() -> new NotFoundUserException("Not found user."));

        repositoryAccountUser.deleteById(user.getId());
        repositoryAddress.deleteById(user.getId());
    }
}
