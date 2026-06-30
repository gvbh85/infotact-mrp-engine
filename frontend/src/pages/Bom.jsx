import { useEffect, useState } from "react";
import BomService from "../services/BomService";

function Bom() {

    const emptyBom = {
        productName: "",
        materialName: "",
        quantityRequired: "",
        unit: ""
    };

    const [bomList, setBomList] = useState([]);
    const [bom, setBom] = useState(emptyBom);

    const [editing, setEditing] = useState(false);
    const [selectedId, setSelectedId] = useState(null);

    useEffect(() => {
        loadBom();
    }, []);

    const loadBom = () => {
        BomService.getAllBom()
            .then((res) => {
                setBomList(res.data);
            })
            .catch((err) => console.log(err));
    };

    const handleChange = (e) => {
        setBom({
            ...bom,
            [e.target.name]: e.target.value
        });
    };

    const clearForm = () => {
        setBom(emptyBom);
        setEditing(false);
        setSelectedId(null);
    };

    const handleSubmit = () => {

        if (
            bom.productName === "" ||
            bom.materialName === "" ||
            bom.quantityRequired === "" ||
            bom.unit === ""
        ) {
            alert("Please fill all fields");
            return;
        }

        if (editing) {

            BomService.updateBom(selectedId, bom)
                .then(() => {
                    alert("BOM Updated Successfully");
                    clearForm();
                    loadBom();
                });

        } else {

            BomService.addBom(bom)
                .then(() => {
                    alert("BOM Added Successfully");
                    clearForm();
                    loadBom();
                });

        }

    };

    const editBom = (item) => {

        setBom({
            productName: item.productName,
            materialName: item.materialName,
            quantityRequired: item.quantityRequired,
            unit: item.unit
        });

        setSelectedId(item.id);
        setEditing(true);
    };

    const deleteBom = (id) => {

        if (window.confirm("Delete this BOM?")) {

            BomService.deleteBom(id)
                .then(() => {
                    alert("BOM Deleted Successfully");
                    loadBom();
                });

        }
    };

    return (

        <div>

            <h2>Bill Of Materials (BOM)</h2>

            <hr />

            <input
                name="productName"
                placeholder="Product Name"
                value={bom.productName}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="materialName"
                placeholder="Material Name"
                value={bom.materialName}
                onChange={handleChange}
            />

            <br /><br />

            <input
                type="number"
                name="quantityRequired"
                placeholder="Quantity Required"
                value={bom.quantityRequired}
                onChange={handleChange}
            />

            <br /><br />

            <input
                name="unit"
                placeholder="Unit (Kg, Nos, Litres...)"
                value={bom.unit}
                onChange={handleChange}
            />

            <br /><br />

            <button onClick={handleSubmit}>
                {editing ? "Update BOM" : "Save BOM"}
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
                        <th>Material</th>
                        <th>Quantity</th>
                        <th>Unit</th>
                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {
                        bomList.map((item) => (

                            <tr key={item.id}>

                                <td>{item.id}</td>

                                <td>{item.productName}</td>

                                <td>{item.materialName}</td>

                                <td>{item.quantityRequired}</td>

                                <td>{item.unit}</td>

                                <td>

                                    <button
                                        onClick={() => editBom(item)}
                                    >
                                        Edit
                                    </button>

                                    &nbsp;

                                    <button
                                        onClick={() => deleteBom(item.id)}
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

export default Bom;