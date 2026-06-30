import axios from "axios";

const BASE_URL = "http://localhost:8080/api/purchase-orders";

class PurchaseOrderService {

    getAllPurchaseOrders() {
        return axios.get(BASE_URL);
    }

    getPurchaseOrderById(id) {
        return axios.get(`${BASE_URL}/${id}`);
    }

    addPurchaseOrder(order) {
        return axios.post(BASE_URL, order);
    }

    updatePurchaseOrder(id, order) {
        return axios.put(`${BASE_URL}/${id}`, order);
    }

    deletePurchaseOrder(id) {
        return axios.delete(`${BASE_URL}/${id}`);
    }
}

export default new PurchaseOrderService();