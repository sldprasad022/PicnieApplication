package com.techpixe.serviceImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.entity.Admin;
import com.techpixe.entity.User;
import com.techpixe.repository.AdminRepository;
import com.techpixe.repository.UserRepository;
import com.techpixe.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("$(spring.mail.username)")
	private String fromMail;

	@Override
	public User saveUser(String email) {
		User user = new User();
		user.setEmail(email);
		String otp = generateOTP();
		user.setOtp(otp);

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromMail);
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Registration completed Successfully in GetPhoto application\n");
		simpleMailMessage.setText("Dear  user "
				+ ",\n\n Thank you for singing Up for Picnie! click bewlow to get  started on your web or mobile device .\n\nPlease check your registered email and generted OTP\n UserEmail  :"
				+ email + "\n OTP   :" + otp + "\n\n"
				+ "you will be required to reset the temporary password upon login\n\n\n if you have any question or if you would like to request a call-back,please email us at support info@techpixe.com");
		javaMailSender.send(simpleMailMessage);

		return userRepository.save(user);

	}

	private static final int OTP_LENGTH = 6; // Length of the OTP
	private static final String OTP_CHARS = "0123456789"; // Characters allowed in OTP

	public String generateOTP() {
		Random random = new Random();
		StringBuilder otp = new StringBuilder();

		// Generate OTP of desired length
		for (int i = 0; i < OTP_LENGTH; i++) {
			int index = random.nextInt(OTP_CHARS.length());
			otp.append(OTP_CHARS.charAt(index));
		}

		return otp.toString();
	}

	@Override
	public User registerUser(String fullName, String password, String otp, String email, Long admin) {
		User user1 = userRepository.findByEmail(email);

		Admin adminId = adminRepository.findById(admin).orElse(null);

		if (user1 != null && user1.getOtp().equals(otp)) {

			user1.setFullName(fullName);
			user1.setPassword(password);
			user1.setRole("User");
			user1.setAdmin(adminId);
			return userRepository.save(user1);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Email is not Present");
		}
	}

	@Override
	public String resendOTP(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			String newOTP = generateOTP();
			user.setOtp(newOTP);
			userRepository.save(user);
			// Send new OTP via email
			// sendOTPByEmail(email, newOTP);

			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(fromMail);
			simpleMailMessage.setTo(email);
			simpleMailMessage.setSubject("Registration completed Successfully in GetPhoto application\n");
			simpleMailMessage.setText("Dear  user "
					+ ",\n\n Thank you for singing Up for Picnie! click bewlow to get  started on your web or mobile device .\n\nPlease check your registered email and generted OTP\n UserEmail  :"
					+ email + "\n OTP   :" + newOTP + "\n\n"
					+ "you will be required to reset the temporary password upon login\n\n\n if you have any question or if you would like to request a call-back,please email us at support info@techpixe.com");
			javaMailSender.send(simpleMailMessage);

			return "New OTP sent successfully.";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with email: " + email);
		}
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException(" User with this Id is present " + userId));
	}

}
