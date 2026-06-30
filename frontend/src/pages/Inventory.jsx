import { useEffect, useState } from "react";
import InventoryService from "../services/InventoryService";

function Inventory() {

    const emptyInventory = {
        materialName: "",
        currentStock: "",
        minimumStock: "",
        maximumStock: "",
        warehouse: ""
    };

    const [inventoryList, setInventoryList] = useState([]);
    const [inventory, setInventory] = useState(emptyInventory);

    const [editing, setEditing] = useState(false);
    const [selectedId, setSelectedId] = useState(null);

    useEffect(() => {
        loadInventory();
    }, []);

    const loadInventory = () => {
        InventoryService.getAllInventory()
            .then((res) => {
                setInventoryList(res.data);
            });
    };

    const handleChange = (e) => {
        setInventory({
            ...inventory,
            [e.target.name]: e.target.value
        });
    };

    const clearForm = () => {
        setInventory(emptyInventory);
        setEditing(false);
        setSelectedId(null);
    };

    const handleSubmit = () => {

        if (
            inventory.materialName === "" ||
            inventory.currentStock === "" ||
            inventory.minimumStock === "" ||
            inventory.maximumStock === "" ||
            inventory.warehouse === ""
        ) {
            alert("Fill all fields");
            return;
        }

        if (editing) {

            InventoryService.updateInventory(selectedId, inventory)
                .then(() => {
                    alert("Inventory Updated");
                    clearForm();
                    loadInventory();
                });

        } else {

            InventoryService.addInventory(inventory)
                .then(() => {
                    alert("Inventory Added");
                    clearForm();
                    loadInventory();
                });

        }

    };

    const editInventory = (item) => {

        setInventory({
            materialName: item.materialName,
            currentStock: item.currentStock,
            minimumStock: item.minimumStock,
            maximumStock: item.maximumStock,
            warehouse: item.warehouse
        });

        setSelectedId(item.id);

        setEditing(true);
    };

    const deleteInventory = (id) => {

        if (window.confirm("Delete Inventory?")) {

            InventoryService.deleteInventory(id)
                .then(() => {
                    alert("Deleted Successfully");
                    loadInventory();
                });

        }
    };

    return (

        <div>

            <h2>Inventory Management</h2>

            <hr />

            <input
                name="materialName"
                placeholder="Material Name"
                value={inventory.materialName}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="number"
                name="currentStock"
                placeholder="Current Stock"
                value={inventory.currentStock}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="number"
                name="minimumStock"
                placeholder="Minimum Stock"
                value={inventory.minimumStock}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="number"
                name="maximumStock"
                placeholder="Maximum Stock"
                value={inventory.maximumStock}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="warehouse"
                placeholder="Warehouse"
                value={inventory.warehouse}
                onChange={handleChange}
            />

            <br /><br />

            <button onClick={handleSubmit}>
                {editing ? "Update Inventory" : "Save Inventory"}
            </button>

            &nbsp;

            <button onClick={clearForm}>
                Clear
            </button>

            <br /><br />

            <table className="table table-bordered table-striped">

                <thead>

                    <tr>

                        <th>ID</th>

                        <th>Material</th>

                        <th>Current</th>

                        <th>Minimum</th>

                        <th>Maximum</th>

                        <th>Warehouse</th>

                        <th>Status</th>

                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {
                        inventoryList.map((item) => (

                            <tr key={item.id}>

                                <td>{item.id}</td>

                                <td>{item.materialName}</td>

                                <td>{item.currentStock}</td>

                                <td>{item.minimumStock}</td>

                                <td>{item.maximumStock}</td>

                                <td>{item.warehouse}</td>

                                <td>

                                    {
                                        item.currentStock <= item.minimumStock
                                            ? "⚠ LOW STOCK"
                                            : "✅ AVAILABLE"
                                    }

                                </td>

                                <td>

                                    <button
                                        onClick={() => editInventory(item)}
                                    >
                                        Edit
                                    </button>

                                    &nbsp;

                                    <button
                                        onClick={() => deleteInventory(item.id)}
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

export default Inventory;