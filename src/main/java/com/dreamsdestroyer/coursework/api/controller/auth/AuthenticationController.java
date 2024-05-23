/*package com.dreamsdestroyer.CourseWork.api.controller.auth;

import com.dreamsdestroyer.CourseWork.api.model.LoginBody;
import com.dreamsdestroyer.CourseWork.api.model.LoginResponse;
import com.dreamsdestroyer.CourseWork.api.model.RegistrationBody;
import com.dreamsdestroyer.CourseWork.exception.UserAlreadyExistsException;
import com.dreamsdestroyer.CourseWork.exception.UserNotVerifiedException;
import com.dreamsdestroyer.CourseWork.model.LocalUser;
import com.dreamsdestroyer.CourseWork.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;
    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam String token){
        if(userService.verifyUser(token)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = null;
        try {
            jwt = userService.loginUser(loginBody);
        } catch (UserNotVerifiedException e) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccess(false);
            String reason = "USER_NOT_VERIFIED";
            if(e.isNewEmailSent()){
                reason += "_EMAIL_RESENT";
            }
            loginResponse.setFailureReason(reason);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);
        }
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setSuccessToken(jwt);
            response.setSuccess(true);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/me")
    public LocalUser  getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user){
        return user;
    }


}
*/