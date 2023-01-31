import axios from 'axios';

class AuthService {
  login(user) {
    return axios
      .post('http://localhost:8080/token', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post('http://localhost:8080/register', {
      username: user.username,
      password: user.password
    });
  }
}

export default new AuthService();
