import axios from "axios";

const BASE_URL = "http://localhost:8080/api/production";

class ProductionService {

    getAllProduction() {
        return axios.get(BASE_URL);
    }

    getProductionById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    addProduction(production) {
        return axios.post(BASE_URL, production);
    }

    updateProduction(id, production) {
        return axios.put(`${BASE_URL}/${id}`, production);
    }

    deleteProduction(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }
}

export default new ProductionService();