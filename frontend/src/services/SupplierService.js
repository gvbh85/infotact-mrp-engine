import axios from "axios";

const BASE_URL = "http://localhost:8080/api/suppliers";

class SupplierService {

    getAllSuppliers() {
        return axios.get(BASE_URL);
    }

    getSupplierById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    addSupplier(supplier) {
        return axios.post(BASE_URL, supplier);
    }

    updateSupplier(id, supplier) {
        return axios.put(`${BASE_URL}/${id}`, supplier);
    }

    deleteSupplier(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }
}

export default new SupplierService();