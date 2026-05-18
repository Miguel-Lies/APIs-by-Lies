package com.enterprise.bank_lies.service;

import com.enterprise.bank_lies.exceptions.NotFoundUserException;
import com.enterprise.bank_lies.repository.AccountUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImplements implements UserDetailsService {

    private final AccountUserRepository accountUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountUserRepository.findByEmail(username)
                .orElseThrow(()-> new NotFoundUserException("Not found user, try again."));
    }
}
