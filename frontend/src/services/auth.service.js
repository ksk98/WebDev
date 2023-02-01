import axios from 'axios';
import { Buffer } from 'buffer/';

class AuthService {
  login(user) {
    return axios
      .post('http://localhost:8080/login',
          {
        username: user.username,
        password: user.password
      },
          {
        headers: {
          'Authorization': 'Basic ' + this.tokenFrom(user.username, user.password)
        }
      }
      )
      .then(response => {
        if (response.data.token) {
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

  tokenFrom(username, password) {
    return Buffer.from(`${username}:${password}`, 'utf8').toString('base64');
  }
}

export default new AuthService();
