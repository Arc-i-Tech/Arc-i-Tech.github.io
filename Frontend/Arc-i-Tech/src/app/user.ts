export class User {
  constructor(
    public username: string,
    public newPassword: string,
    public confirmPass: string
  ) {}
}