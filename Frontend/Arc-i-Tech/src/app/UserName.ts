export interface ResetPasswordRequest {
  username: string;
  newPassword: string;
  otpStr: string;
}
