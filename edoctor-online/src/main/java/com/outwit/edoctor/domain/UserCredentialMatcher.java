package com.outwit.edoctor.domain;

import com.outwit.edoctor.infrastructure.utils.PasswordHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;

@Slf4j
public class UserCredentialMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String plainTextPassword = new String((char[]) authenticationToken.getCredentials());
        String salt = new String(((SimpleAuthenticationInfo) authenticationInfo).getCredentialsSalt().getBytes());
        return authenticationInfo.getCredentials().toString().equals(new PasswordHelper().encryptPassword(plainTextPassword + salt));
    }
}
