package com.sda.OnlineShop.services;

import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailAddress(emailAddress);  //cautam in baza de date un user

        if (optionalUser.isEmpty()) {  // daca nu am gasit aruncam exceptia
            throw new UsernameNotFoundException(emailAddress);
        }
        User user = optionalUser.get();  // exista un user cu acest email si il despachetez
        Set<GrantedAuthority> roles = new HashSet<>();  // pregatesc colectia in care voi pune rolurile userilor si adaug rolul userului pentru ca noi avem doar unu singur
            roles.add(new SimpleGrantedAuthority(user.getUserRole().name()));
        return new org.springframework.security.core.userdetails.User(emailAddress, user.getPassword(),roles);  // instantiem obiectul user din sprinsecurity
    }
}
