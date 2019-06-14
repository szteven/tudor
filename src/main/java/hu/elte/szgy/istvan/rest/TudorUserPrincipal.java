package hu.elte.szgy.istvan.rest;

import hu.elte.szgy.istvan.data.Felhasznalo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class TudorUserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Felhasznalo user;
    private List<GrantedAuthority> auths=new ArrayList<GrantedAuthority>(5);

    public TudorUserPrincipal(Felhasznalo user) {
        this.user = user;

        auths.add(new SimpleGrantedAuthority(user.getTipus().name()));

    }

    public java.util.Collection<? extends GrantedAuthority> getAuthorities() { return auths; }
    public java.lang.String getUsername() { return user.getFelhasznalonev(); }
    public java.lang.String getPassword() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder.encode(user.getJelszo());
    }
    public int getTudorId() { return user.getId(); }

    public boolean isEnabled() { return true; }
    public boolean isCredentialsNonExpired() { return true; }
    public boolean isAccountNonExpired() { return true; }
    public boolean isAccountNonLocked() { return true; }
}
