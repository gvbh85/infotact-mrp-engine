import { useEffect, useState } from "react";
import SupplierService from "../services/SupplierService";

function Suppliers() {

    const emptySupplier = {
        supplierName: "",
        email: "",
        phone: "",
        address: "",
        city: "",
        country: "",
        gstNumber: ""
    };

    const [suppliers, setSuppliers] = useState([]);
    const [supplier, setSupplier] = useState(emptySupplier);

    const [editing, setEditing] = useState(false);
    const [selectedId, setSelectedId] = useState(null);

    useEffect(() => {
        loadSuppliers();
    }, []);

    const loadSuppliers = () => {
        SupplierService.getAllSuppliers()
            .then((res) => {
                setSuppliers(res.data);
            })
            .catch((err) => console.log(err));
    };

    const handleChange = (e) => {
        setSupplier({
            ...supplier,
            [e.target.name]: e.target.value
        });
    };

    const clearForm = () => {
        setSupplier(emptySupplier);
        setEditing(false);
        setSelectedId(null);
    };

    const handleSubmit = () => {

        if (
            supplier.supplierName === "" ||
            supplier.email === "" ||
            supplier.phone === "" ||
            supplier.address === "" ||
            supplier.city === "" ||
            supplier.country === "" ||
            supplier.gstNumber === ""
        ) {
            alert("Please fill all fields");
            return;
        }

        if (editing) {

            SupplierService.updateSupplier(selectedId, supplier)
                .then(() => {
                    alert("Supplier Updated Successfully");
                    clearForm();
                    loadSuppliers();
                });

        } else {

            SupplierService.addSupplier(supplier)
                .then(() => {
                    alert("Supplier Added Successfully");
                    clearForm();
                    loadSuppliers();
                });

        }
    };

    const editSupplier = (s) => {

        setSupplier({
            supplierName: s.supplierName,
            email: s.email,
            phone: s.phone,
            address: s.address,
            city: s.city,
            country: s.country,
            gstNumber: s.gstNumber
        });

        setSelectedId(s.id);

        setEditing(true);
    };

    const deleteSupplier = (id) => {

        if (window.confirm("Delete this supplier?")) {

            SupplierService.deleteSupplier(id)
                .then(() => {
                    alert("Supplier Deleted Successfully");
                    loadSuppliers();
                });

        }
    };

    return (

        <div>

            <h2>Supplier Management</h2>

            <hr />

            <input
                name="supplierName"
                placeholder="Supplier Name"
                value={supplier.supplierName}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="email"
                placeholder="Email"
                value={supplier.email}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="phone"
                placeholder="Phone"
                value={supplier.phone}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="address"
                placeholder="Address"
                value={supplier.address}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="city"
                placeholder="City"
                value={supplier.city}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="country"
                placeholder="Country"
                value={supplier.country}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="gstNumber"
                placeholder="GST Number"
                value={supplier.gstNumber}
                onChange={handleChange}
            />

            <br /><br />

            <button onClick={handleSubmit}>
                {editing ? "Update Supplier" : "Save Supplier"}
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

                        <th>Name</th>

                        <th>Email</th>

                        <th>Phone</th>
                        
                        <th>Address</th>

                        <th>City</th>

                        <th>Country</th>

                        <th>GST</th>

                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {
                        suppliers.map((s) => (

                            <tr key={s.id}>

                                <td>{s.id}</td>

                                <td>{s.supplierName}</td>

                                <td>{s.email}</td>

                                <td>{s.phone}</td>

                                <td>{s.address}</td>

                                <td>{s.city}</td>

                                <td>{s.country}</td>

                                <td>{s.gstNumber}</td>

                                <td>

                                    <button
                                        onClick={() => editSupplier(s)}
                                    >
                                        Edit
                                    </button>

                                    &nbsp;

                                    <button
                                        onClick={() => deleteSupplier(s.id)}
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

export default Suppliers;