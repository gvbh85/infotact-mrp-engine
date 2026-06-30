import { useEffect, useState } from "react";
import PurchaseOrderService from "../services/PurchaseOrderService";

function PurchaseOrders() {

    const emptyOrder = {
        poNumber: "",
        supplierName: "",
        materialName: "",
        quantity: "",
        unitPrice: "",
        orderDate: "",
        status: ""
    };

    const [orders, setOrders] = useState([]);
    const [order, setOrder] = useState(emptyOrder);

    const [editing, setEditing] = useState(false);
    const [selectedId, setSelectedId] = useState(null);

    useEffect(() => {
        loadOrders();
    }, []);

    const loadOrders = () => {
        PurchaseOrderService.getAllPurchaseOrders()
            .then((res) => {
                setOrders(res.data);
            });
    };

    const handleChange = (e) => {
        setOrder({
            ...order,
            [e.target.name]: e.target.value
        });
    };

    const clearForm = () => {
        setOrder(emptyOrder);
        setEditing(false);
        setSelectedId(null);
    };

    const handleSubmit = () => {

        if (
            order.poNumber === "" ||
            order.supplierName === "" ||
            order.materialName === "" ||
            order.quantity === "" ||
            order.unitPrice === "" ||
            order.orderDate === "" ||
            order.status === ""
        ) {
            alert("Please fill all fields");
            return;
        }

        if (editing) {

            PurchaseOrderService.updatePurchaseOrder(selectedId, order)
                .then(() => {
                    alert("Purchase Order Updated");
                    clearForm();
                    loadOrders();
                });

        } else {

            PurchaseOrderService.addPurchaseOrder(order)
                .then(() => {
                    alert("Purchase Order Saved");
                    clearForm();
                    loadOrders();
                });

        }
    };

    const editOrder = (o) => {

        setOrder({
            poNumber: o.poNumber,
            supplierName: o.supplierName,
            materialName: o.materialName,
            quantity: o.quantity,
            unitPrice: o.unitPrice,
            orderDate: o.orderDate,
            status: o.status
        });

        setSelectedId(o.id);

        setEditing(true);
    };

    const deleteOrder = (id) => {

        if (window.confirm("Delete Purchase Order?")) {

            PurchaseOrderService.deletePurchaseOrder(id)
                .then(() => {
                    alert("Deleted Successfully");
                    loadOrders();
                });

        }

    };

    return (

        <div>

            <h2>Purchase Order Management</h2>

            <hr />

            <input
                name="poNumber"
                placeholder="PO Number"
                value={order.poNumber}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="supplierName"
                placeholder="Supplier Name"
                value={order.supplierName}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="materialName"
                placeholder="Material Name"
                value={order.materialName}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="number"
                name="quantity"
                placeholder="Quantity"
                value={order.quantity}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="number"
                name="unitPrice"
                placeholder="Unit Price"
                value={order.unitPrice}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="date"
                name="orderDate"
                value={order.orderDate}
                onChange={handleChange}
            />

            <br /><br />

            <select
                name="status"
                value={order.status}
                onChange={handleChange}
            >
                <option value="">Select Status</option>
                <option>Pending</option>
                <option>Approved</option>
                <option>Completed</option>
            </select>

            <br /><br />

            <button onClick={handleSubmit}>
                {editing ? "Update Order" : "Save Order"}
            </button>

            <button onClick={clearForm}>
                Clear
            </button>

            <br /><br />

            <table className="table table-bordered table-striped">

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>PO No</th>
                        <th>Supplier</th>
                        <th>Material</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Date</th>
                        <th>Status</th>
                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {
                        orders.map((o) => (

                            <tr key={o.id}>

                                <td>{o.id}</td>
                                <td>{o.poNumber}</td>
                                <td>{o.supplierName}</td>
                                <td>{o.materialName}</td>
                                <td>{o.quantity}</td>
                                <td>{o.unitPrice}</td>
                                <td>{o.orderDate}</td>

                                <td>

                                    {
                                        o.status === "Pending"
                                        ? "🟡 Pending"
                                        : o.status === "Approved"
                                        ? "🟢 Approved"
                                        : "🔵 Completed"
                                    }

                                </td>

                                <td>

                                    <button
                                        onClick={() => editOrder(o)}
                                    >
                                        Edit
                                    </button>

                                    &nbsp;

                                    <button
                                        onClick={() => deleteOrder(o.id)}
                                    >
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        ))
                    }

                </tbody>

            </table>

        </div>

    );

}

export default PurchaseOrders;