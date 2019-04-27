package com.fajarmf.aop;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fajarmf.service.SecurityServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Aspect
@Component
public class AdminTokenRequiredAspect {
	
	@Before("@annotation(adminTokenRequired)")
	public void adminTokenRequiredWithAnnotation(AdminTokenRequired adminTokenRequired) throws Throwable{
		
		ServletRequestAttributes reqAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = reqAttributes.getRequest();
		
		String tokenInHeader = request.getHeader("token");
		
		if(StringUtils.isEmpty(tokenInHeader)){
			throw new IllegalArgumentException("Empty token");
		}		
		
		SecretKey secKey = SecurityServiceImpl.secretKey;
		
		Claims claims = Jwts.parser()         
			       .setSigningKey(secKey)
			       .parseClaimsJws(tokenInHeader).getBody();
		
		if(claims == null || claims.getSubject() == null){
			throw new IllegalArgumentException("Token Error : Claim is null");
		}
		
		String subject = claims.getSubject();
		
		System.out.println(" usertype : "+subject.split("=")[1]);
		
		if(subject.split("=").length != 2 || new Integer(subject.split("=")[1]) != 3){
			throw new IllegalArgumentException("User is not authorized");
		}		
	}
}
