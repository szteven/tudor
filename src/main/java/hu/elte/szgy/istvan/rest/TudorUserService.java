package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Felhasznalo;
import hu.elte.szgy.istvan.data.FelhasznaloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

public class TudorUserService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger( TudorUserService.class );

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        logger.info("Authenticating " + username);
        Felhasznalo user = felhasznaloRepository.findByFelhasznalonev(username);
        logger.info("User data " + user.getJelszo() + " " + user.getTipus());
        return new TudorUserPrincipal(user);
    }
}
