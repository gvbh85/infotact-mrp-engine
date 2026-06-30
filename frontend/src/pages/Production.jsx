import { useEffect, useState } from "react";
import ProductionService from "../services/ProductionService";

function Production() {

    const emptyProduction = {
        productName: "",
        quantity: "",
        startDate: "",
        endDate: "",
        status: ""
    };

    const [productionList, setProductionList] = useState([]);
    const [production, setProduction] = useState(emptyProduction);

    const [editing, setEditing] = useState(false);
    const [selectedId, setSelectedId] = useState(null);

    useEffect(() => {
        loadProduction();
    }, []);

    const loadProduction = () => {
        ProductionService.getAllProduction()
            .then((res) => {
                setProductionList(res.data);
            })
            .catch((err) => console.log(err));
    };

    const handleChange = (e) => {
        setProduction({
            ...production,
            [e.target.name]: e.target.value
        });
    };

    const clearForm = () => {
        setProduction(emptyProduction);
        setEditing(false);
        setSelectedId(null);
    };

    const handleSubmit = () => {

        if (
            production.productName === "" ||
            production.quantity === "" ||
            production.startDate === "" ||
            production.endDate === "" ||
            production.status === ""
        ) {
            alert("Please fill all fields");
            return;
        }

        if (editing) {

            ProductionService.updateProduction(selectedId, production)
                .then(() => {
                    alert("Production Updated Successfully");
                    clearForm();
                    loadProduction();
                });

        } else {

            ProductionService.addProduction(production)
                .then(() => {
                    alert("Production Added Successfully");
                    clearForm();
                    loadProduction();
                });

        }

    };

    const editProduction = (item) => {

        setProduction({
            productName: item.productName,
            quantity: item.quantity,
            startDate: item.startDate,
            endDate: item.endDate,
            status: item.status
        });

        setSelectedId(item.id);
        setEditing(true);
    };

    const deleteProduction = (id) => {

        if (window.confirm("Delete this Production Record?")) {

            ProductionService.deleteProduction(id)
                .then(() => {
                    alert("Production Deleted Successfully");
                    loadProduction();
                });

        }

    };

    return (

        <div>

            <h2>Production Management</h2>

            <hr />

            <input
                name="productName"
                placeholder="Product Name"
                value={production.productName}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="number"
                name="quantity"
                placeholder="Quantity"
                value={production.quantity}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="date"
                name="startDate"
                value={production.startDate}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="date"
                name="endDate"
                value={production.endDate}
                onChange={handleChange}
            />

            <br /><br />

            <select
                name="status"
                value={production.status}
                onChange={handleChange}
            >
                <option value="">Select Status</option>
                <option>Planned</option>
                <option>In Progress</option>
                <option>Completed</option>
            </select>

            <br /><br />

            <button onClick={handleSubmit}>
                {editing ? "Update Production" : "Save Production"}
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
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>

                </thead>

                <tbody>

                    {
                        productionList.map((item) => (

                            <tr key={item.id}>

                                <td>{item.id}</td>

                                <td>{item.productName}</td>

                                <td>{item.quantity}</td>

                                <td>{item.startDate}</td>

                                <td>{item.endDate}</td>

                                <td>
                                    {
                                        item.status === "Planned"
                                        ? "🟡 Planned"
                                        : item.status === "In Progress"
                                        ? "🟢 In Progress"
                                        : "🔵 Completed"
                                    }
                                </td>

                                <td>

                                    <button onClick={() => editProduction(item)}>
                                        Edit
                                    </button>

                                    &nbsp;

                                    <button onClick={() => deleteProduction(item.id)}>
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

export default Production;