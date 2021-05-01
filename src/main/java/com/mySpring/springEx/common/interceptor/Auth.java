package com.mySpring.springEx.common.interceptor;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Auth {
public enum Role {ADMIN, USER, PARTNER, PA, CREW, NON_PA} //ADMIN, USER, PARNER, PA(PARTNER + ADMIN), CREW Authorization check
	
	// �̿� ���� �ۼ��ϸ� �޼��� ���� @Auth(role=Role.ADMIN)�� ���� �ۼ� ����
	public Role role() default Role.USER;

}
