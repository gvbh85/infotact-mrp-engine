import axios from "axios";

const BASE_URL = "http://localhost:8080/api/dashboard";

class DashboardService {

    getDashboard() {
        return axios.get(BASE_URL);
    }

}

export default new DashboardService();