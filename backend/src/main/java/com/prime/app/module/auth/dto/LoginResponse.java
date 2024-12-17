package com.prime.app.module.auth.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String token;
	private String refreshToken;
	@JsonRawValue // to avoid extra \\ in response
	private Object loginData;

	public LoginResponse(String token, String refreshToken) {
		this.token = token;
		this.refreshToken = refreshToken;
	}

	/**
	 * used when login response
	 *
	 * @param token        access token
	 * @param refreshToken refresh token
	 * @param loginData    user data
	 */
	public LoginResponse(String token, String refreshToken, Object loginData) {
		this.token = token;
		this.refreshToken = refreshToken;
		this.loginData = loginData;
	}

}
