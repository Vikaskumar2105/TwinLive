package com.twinlive.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twinlive.entity.User;
import com.twinlive.repository.UserRepository;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;

    public String generateOtp(String phoneNumber) {
        String otp = "123456";  // Static OTP for testing
        User user = userRepository.findByPhoneNumber(phoneNumber)
            .orElse(new User());

        user.setPhoneNumber(phoneNumber);
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5)); // OTP expires in 5 minutes
        userRepository.save(user);

        // Send OTP via SMS or email (for now, just log it)
        sendOtp(phoneNumber, otp);

        return otp;
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getOtp().equals(otp) && user.getOtpExpiry().isAfter(LocalDateTime.now())) {
                user.setVerified(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private void sendOtp(String phoneNumber, String otp) {
        // For now, just print the static OTP to the console
        System.out.println("OTP " + otp + " sent to phone number " + phoneNumber);
    }
}

