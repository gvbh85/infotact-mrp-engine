import axios from "axios";

const BASE_URL = "http://localhost:8080/api/users";

class UserService {

    register(user) {
        return axios.post(`${BASE_URL}/register`, user);
    }

    login(user) {
        return axios.post(`${BASE_URL}/login`, user);
    }

}

export default new UserService();