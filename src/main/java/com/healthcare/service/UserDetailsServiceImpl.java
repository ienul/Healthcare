package com.healthcare.service;

import com.healthcare.model.User;
import com.healthcare.repository.UserRepository;
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
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("AM APELAT USERDETAILSERVICE");
        Optional<User> optionalUser= userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException(email);
        }
        User user = optionalUser.get();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().name())); // este posibil ca SpringSecurity sa astepte ROLE_ in fata optiunilor de roluri

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);

    }
}
