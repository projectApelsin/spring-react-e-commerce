package com.dreamsdestroyer.coursework.service;


import com.dreamsdestroyer.coursework.api.model.LoginBody;
import com.dreamsdestroyer.coursework.api.model.RegistrationBody;
import com.dreamsdestroyer.coursework.exception.UserAlreadyExistsException;
import com.dreamsdestroyer.coursework.exception.UserNotVerifiedException;
import com.dreamsdestroyer.coursework.model.LocalUser;
import com.dreamsdestroyer.coursework.model.ShoppingCart;
import com.dreamsdestroyer.coursework.model.VerificationToken;
import com.dreamsdestroyer.coursework.model.repository.LocalUserRepository;
import com.dreamsdestroyer.coursework.model.repository.ShoppingCartRepository;
import com.dreamsdestroyer.coursework.model.repository.VerificationTokenRepository;
import com.dreamsdestroyer.coursework.service.EmailService;
import com.dreamsdestroyer.coursework.service.EncryptionService;
import com.dreamsdestroyer.coursework.service.JWTService;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private LocalUserRepository localUserRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    private EmailService emailService;
    private final ShoppingCartRepository shoppingCartRepository;


    public UserService(LocalUserRepository localUserDAO, VerificationTokenRepository verificationTokenRepository,
                       EncryptionService encryptionService, JWTService jwtService, EmailService emailService,
                       ShoppingCartRepository shoppingCartRepository) {
        this.localUserRepository = localUserDAO;
        this.verificationTokenRepository = verificationTokenRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (localUserRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || localUserRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setLocalUser(user);
        user.setShoppingCart(shoppingCart);
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        shoppingCartRepository.save(shoppingCart);
        return localUserRepository.save(user);
    }


    private VerificationToken createVerificationToken(LocalUser user) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setAccessToken(jwtService.generateVerificationJWT(user));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }


    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException {
        Optional<LocalUser> opUser = localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                if (user.getEmailVerified()) {
                    return jwtService.generateJWT(user);
                } else {
                    List<VerificationToken> verificationTokens = user.getVerificationTokens();
                    boolean resend = verificationTokens.size() == 0 ||
                            verificationTokens.get(0).getCreatedTimeStamp().before(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
                    if (resend) {
                        VerificationToken verificationToken = createVerificationToken(user);
                        verificationTokenRepository.save(verificationToken);
                        emailService.sendVerificationEmail(verificationToken);
                    }
                    throw new UserNotVerifiedException(resend);
                }
            }
        }
        return null;
    }


    @Transactional
    public boolean verifyUser(String token) {
        Optional<VerificationToken> opToken = verificationTokenRepository.findByAccessToken(token);
        if (opToken.isPresent()) {
            VerificationToken verificationToken = opToken.get();
            LocalUser user = verificationToken.getUser();
            if (!user.getEmailVerified()) {
                user.setEmailVerified(true);
                localUserRepository.save(user);
                verificationTokenRepository.deleteByUser(user);
                return true;
            }
        }
        return false;
    }

    public LocalUser getUserDetails(LoginBody loginBody) {
        return localUserRepository.findByUsername(loginBody.getUsername()).get();
    }

}

