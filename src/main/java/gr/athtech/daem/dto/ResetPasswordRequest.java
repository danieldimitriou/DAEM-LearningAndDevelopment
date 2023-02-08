package gr.athtech.daem.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {

	private String currentPassword;
	private String newPassword;
	private String newPasswordConfirmed;
}
