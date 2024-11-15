import { User } from "./user";

describe('User', () => {
  it('should create an instance', () => {
    const user = new User('', '', ''); 
    expect(user).toBeTruthy();
  });
});