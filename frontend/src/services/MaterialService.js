import axios from "axios";

const BASE_URL = "http://localhost:8080/api/materials";

class MaterialService {

    getAllMaterials() {
        return axios.get(BASE_URL);
    }

    getMaterialById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    addMaterial(material) {
        return axios.post(BASE_URL, material);
    }

    updateMaterial(id, material) {
        return axios.put(`${BASE_URL}/${id}`, material);
    }

    deleteMaterial(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }
}

export default new MaterialService();