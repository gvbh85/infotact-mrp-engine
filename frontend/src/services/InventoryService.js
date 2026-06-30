import axios from "axios";

const BASE_URL = "http://localhost:8080/api/inventory";

class InventoryService {

    getAllInventory() {
        return axios.get(BASE_URL);
    }

    getInventoryById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    addInventory(inventory) {
        return axios.post(BASE_URL, inventory);
    }

    updateInventory(id, inventory) {
        return axios.put(`${BASE_URL}/${id}`, inventory);
    }

    deleteInventory(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }

}

export default new InventoryService();