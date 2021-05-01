package com.mySpring.springEx.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mySpring.springEx.member.vo.MemberVO;


public class AuthViewInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 1. handler ���� Ȯ��
				// �츮�� ���� �ִ� ���� Controller�� �ִ� �޼����̹Ƿ� HandlerMethod Ÿ������ üũ
				if( handler instanceof HandlerMethod == false ) {
					// return true�̸�  Controller�� �ִ� �޼��尡 �ƴϹǷ�, �״�� ��Ʈ�ѷ��� ����
					return true;
				}

				// 2.�� ��ȯ
				HandlerMethod handlerMethod = (HandlerMethod)handler;
				
				// 3. @Auth �޾ƿ���
				Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
				
				// 4. method�� @Auth�� ���� ���, �� ������ �ʿ� ���� ��û
				if( auth == null ) {
					return true;
				}
				
				// 5. @Auth�� �ִ� ����̹Ƿ�, ������ �ִ��� üũ
				HttpSession session = request.getSession();
				if( session == null ) {
					// ȭ�� ���� �������� �̵�
					response.sendRedirect(request.getContextPath() + "/noAuth.do");
					return false;
				}
				
				// 6. ������ �����ϸ� ��ȿ�� �������� Ȯ��
				MemberVO authUser = (MemberVO)session.getAttribute("member");
				if ( authUser == null ) {
					// ȭ�� ���� �������� �̵�
					response.sendRedirect(request.getContextPath() + "/noAuth.do");
					return false;
				}

				// 7. admin�� ���
				String role = auth.role().toString();
				System.out.println("���� ����============="+role+"������============"+authUser.getUserPosition());
				if( "ADMIN".equals(role) ) {
					// admin���� �� �� �ִ� ������ �ۼ��Ѵ�.
					// ex) ������ id�� root�̸� admin�̴�.
					if( "ADMIN".equals(authUser.getUserPosition()) == false ){   // admin�� �ƴϹǷ� return false
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					}
				} else if("PARTNER".equals(role)) {
					// ���»����� �� �� �ִ� ������ �ۼ��Ѵ�.
					// ex) ������ id�� root�̸� admin�̴�.
					if( "PARTNER".equals(authUser.getUserPosition()) == false ){   // partner�� �ƴϹǷ� return false
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					}
				} else if("CREW".equals(role)) {
					if("ä�뿹����".equals(authUser.getUserPosition()) == false) {
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					}
				} else if("USER".equals(role)) {
						if( "ADMIN".equals(authUser.getUserPosition()) == true ){   // ADMIN�� PARTNER�� ���, ������ ���� �ϳ��� üũ�� �� ��� true�� üũ
							response.sendRedirect(request.getContextPath() + "/noAuth.do");
							return false;
						} else if( "PARTNER".equals(authUser.getUserPosition()) == true) {
							response.sendRedirect(request.getContextPath() + "/noAuth.do");
							return false;
						}
				} else if("PA".equals(role)) {
					if( "������".equals(authUser.getUserPosition()) == true ){   // User�� ä�뿹����, ������, �Ϲ�ȸ���� �ƴѰ��, �ϳ��� üũ�� �� ��� true�� üũ
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					} else if( "ä�뿹����".equals(authUser.getUserPosition()) == true) {
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					} else if("������".equals(authUser.getUserPosition()) == true) {
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					
					}
				} else if("NON_PA".equals(role)) {
					if( "PARTNER".equals(authUser.getUserPosition()) == true ){   // User�� ä�뿹����, ������, �Ϲ�ȸ���� �ƴѰ��, �ϳ��� üũ�� �� ��� true�� üũ
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					} else if( "ADMIN".equals(authUser.getUserPosition()) == true) {
						response.sendRedirect(request.getContextPath() + "/noAuth.do");
						return false;
					}
				}
				// 8. �����㰡, �� �޼��带 �����ϵ��� ��
				return true;
	}
}
