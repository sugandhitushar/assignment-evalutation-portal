package com.assignmentevaluationportal.serviceImpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.config.JwtTokenUtil;
import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.constants.UserStatus;
import com.assignmentevaluationportal.constants.UserType;
import com.assignmentevaluationportal.dto.response.JwtResponse;
import com.assignmentevaluationportal.exception.AEPException;
import com.assignmentevaluationportal.model.Student;
import com.assignmentevaluationportal.model.Teacher;
import com.assignmentevaluationportal.model.User;
import com.assignmentevaluationportal.repository.StudentRepository;
import com.assignmentevaluationportal.repository.TeacherRepository;
import com.assignmentevaluationportal.repository.UserRepository;
import com.assignmentevaluationportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

	public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
			UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userRepository = userRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}

	@Override
	public JwtResponse login(String username, String password, boolean isKeepMeSignedIn) throws Exception {
		authenticate(username, password);
        
        User user = userRepository.findByEmail(username);
        
        if(user == null) {
        	throw new AEPException(HttpStatus.BAD_REQUEST, AEPError.USER_NOT_FOUND);
        }
        
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = null;
        if(isKeepMeSignedIn) {
        	refreshToken = jwtTokenUtil.generateRefreshToken(user);
        }
        return new JwtResponse(accessToken, refreshToken);
	}
	
	@Override
	public JwtResponse generateAccessToken(String refreshToken) {
		
		if(jwtTokenUtil.isTokenExpired(refreshToken)) {
			throw new AEPException(HttpStatus.BAD_REQUEST, AEPError.REFRESH_TOKEN_EXPIRED);
		}
		
		String email = jwtTokenUtil.getUsernameFromToken(refreshToken);
		
		User user = userRepository.findByEmail(email);
		
		if(user != null) {
			String accessToken = jwtTokenUtil.generateAccessToken(user);
			String newRefreshToken = jwtTokenUtil.generateRefreshToken(user);
			return new JwtResponse(accessToken, newRefreshToken);
		} else {
			throw new AEPException(HttpStatus.BAD_REQUEST, AEPError.USER_NOT_FOUND);
		}
	}
	
	/**
	 * Authenticates the user
	 * @param username
	 * 				Email id of the user
	 * @param password
	 * 			Password of the user
	 * @return void
	 * @throws Exception
	 * 			In case of invalid credentials
	 * */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

	@Override
	public User verifyUser(Long id) {
		Optional<User> maybeUser = userRepository.findById(id);
		
		if(!maybeUser.isPresent() || (maybeUser.isPresent() && maybeUser.get().getStatus() == UserStatus.INACTIVE)) {
			throw new AEPException(AEPError.USER_NOT_FOUND);
		}
		
		User user = maybeUser.get();
		user.setStatus(UserStatus.ACTIVE);
		user = userRepository.saveAndFlush(user);
		
		return user;
	}
	
	@Override
	@Transactional
	public void rejectUser(Long id) {
		Optional<User> maybeUser = userRepository.findById(id);
		
		if(!maybeUser.isPresent()) {
			throw new AEPException(AEPError.USER_NOT_FOUND);
		}
		
		User user = maybeUser.get();
		
		if(user.getStatus() == UserStatus.ACTIVE) {
			throw new AEPException(AEPError.USER_ACTIVE);
		}
		
		if(user.getUserType() == UserType.STUDENT) {
			Optional<Student> maybeStudent = studentRepository.findById(id);
			if(maybeStudent.isPresent()) {
				Student student = maybeStudent.get();
				logger.info("Rejecting student. username: {}, collegeFileNumber: {}, rollNumber: {}", 
						student.getUser().getEmail(), student.getCollegeFileNumber(), student.getActiveStudentDivision().getRollNumber());
				studentRepository.delete(student);
			}
		} else if(user.getUserType() == UserType.TEACHER) {
			Optional<Teacher> maybeTeacher = teacherRepository.findById(id);
			
			if(maybeTeacher.isPresent()) {
				Teacher teacher = maybeTeacher.get();
				logger.info("Rejecting teacher. username: {}, employeeId: {}, designation: {}", 
						teacher.getUser().getEmail(), teacher.getEmployeeId(), teacher.getDesignation());
				teacherRepository.delete(teacher);
			}
		}
		
		userRepository.delete(user);
	}

}
