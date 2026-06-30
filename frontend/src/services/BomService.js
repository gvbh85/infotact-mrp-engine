import axios from "axios";

const BASE_URL = "http://localhost:8080/api/bom";

class BomService {

    getAllBom() {
        return axios.get(BASE_URL);
    }

    getBomById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    addBom(bom) {
        return axios.post(BASE_URL, bom);
    }

    updateBom(id, bom) {
        return axios.put(`${BASE_URL}/${id}`, bom);
    }

    deleteBom(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }
}

export default new BomService();