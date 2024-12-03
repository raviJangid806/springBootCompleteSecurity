package com.spring.practiceSpring.service;

import com.spring.practiceSpring.model.Student;
import com.spring.practiceSpring.model.UserPrinciple;
import com.spring.practiceSpring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    // block-4  we can give user details here to the Authentication provider.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByName(username);

        // block-5 userPrinciple extend with UserDetails which represent current user.
        return new UserPrinciple(student);
    }
}
