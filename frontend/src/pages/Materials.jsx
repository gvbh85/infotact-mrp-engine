import { useEffect, useState } from "react";
import MaterialService from "../services/MaterialService";

function Materials() {

  const emptyMaterial = {
    materialCode: "",
    materialName: "",
    category: "",
    unit: "",
    price: "",
    quantity: ""
  };

  const [materials, setMaterials] = useState([]);
  const [material, setMaterial] = useState(emptyMaterial);

  const [editing, setEditing] = useState(false);
  const [selectedId, setSelectedId] = useState(null);

  useEffect(() => {
    loadMaterials();
  }, []);

  const loadMaterials = () => {
    MaterialService.getAllMaterials()
      .then((res) => {
        setMaterials(res.data);
      })
      .catch((err) => console.log(err));
  };

  const handleChange = (e) => {
    setMaterial({
      ...material,
      [e.target.name]: e.target.value
    });
  };

  const clearForm = () => {
    setMaterial(emptyMaterial);
    setEditing(false);
    setSelectedId(null);
  };

  const handleSubmit = () => {

    if (
      material.materialCode === "" ||
      material.materialName === "" ||
      material.category === "" ||
      material.unit === "" ||
      material.price === "" ||
      material.quantity === ""
    ) {
      alert("Please fill all fields");
      return;
    }

    if (editing) {

      MaterialService.updateMaterial(selectedId, material)
        .then(() => {
          alert("Material Updated Successfully");
          clearForm();
          loadMaterials();
        })
        .catch((err) => console.log(err));

    } else {

      MaterialService.addMaterial(material)
        .then(() => {
          alert("Material Added Successfully");
          clearForm();
          loadMaterials();
        })
        .catch((err) => console.log(err));

    }
  };

  const editMaterial = (m) => {
    setMaterial({
      materialCode: m.materialCode,
      materialName: m.materialName,
      category: m.category,
      unit: m.unit,
      price: m.price,
      quantity: m.quantity
    });

    setSelectedId(m.id);
    setEditing(true);
  };

  const deleteMaterial = (id) => {

    if (window.confirm("Delete this material?")) {

      MaterialService.deleteMaterial(id)
        .then(() => {
          alert("Material Deleted Successfully");
          loadMaterials();
        })
        .catch((err) => console.log(err));

    }
  };

  return (

    <div>

      <h2>Material Management</h2>

      <hr />

      <input
        type="text"
        name="materialCode"
        placeholder="Material Code"
        value={material.materialCode}
        onChange={handleChange}
      />

      <br /><br />

      <input
        type="text"
        name="materialName"
        placeholder="Material Name"
        value={material.materialName}
        onChange={handleChange}
      />

      <br /><br />

      <input
        type="text"
        name="category"
        placeholder="Category"
        value={material.category}
        onChange={handleChange}
      />

      <br /><br />

      <input
        type="text"
        name="unit"
        placeholder="Unit"
        value={material.unit}
        onChange={handleChange}
      />

      <br /><br />

      <input
        type="number"
        name="price"
        placeholder="Price"
        value={material.price}
        onChange={handleChange}
      />

      <br /><br />

      <input
        type="number"
        name="quantity"
        placeholder="Quantity"
        value={material.quantity}
        onChange={handleChange}
      />

      <br /><br />

      <button onClick={handleSubmit}>
        {editing ? "Update Material" : "Save Material"}
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
            <th>Code</th>
            <th>Name</th>
            <th>Category</th>
            <th>Unit</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Actions</th>
          </tr>

        </thead>

        <tbody>

          {
            materials.map((m) => (

              <tr key={m.id}>

                <td>{m.id}</td>
                <td>{m.materialCode}</td>
                <td>{m.materialName}</td>
                <td>{m.category}</td>
                <td>{m.unit}</td>
                <td>{m.price}</td>
                <td>{m.quantity}</td>

                <td>

                  <button
                    onClick={() => editMaterial(m)}
                  >
                    Edit
                  </button>

                  &nbsp;

                  <button
                    onClick={() => deleteMaterial(m.id)}
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

export default Materials;