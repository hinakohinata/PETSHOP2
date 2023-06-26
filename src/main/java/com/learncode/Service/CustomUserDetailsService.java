//package com.learncode.Service;
//
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.learncode.Entity.Account;
//import com.learncode.Repository.AccountDAO;
//
//@Service
//public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
//
//    @Autowired
//    private AccountDAO accountRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(username);
//        if (account == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(account.getRole()));
//        
//        return new User(account.getUsername(), account.getPassword(), authorities);
//    }
//}
//
