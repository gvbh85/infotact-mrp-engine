import { useEffect, useState } from "react";
import DashboardService from "../services/DashboardService";
import InventoryChart from "../charts/InventoryChart";

function Dashboard() {

    const [dashboard, setDashboard] = useState({
        materialCount: 0,
        supplierCount: 0,
        inventoryCount: 0,
        purchaseOrderCount: 0,
        productionCount: 0,
        lowStockCount: 0
    });

    useEffect(() => {
        loadDashboard();
    }, []);

    const loadDashboard = () => {
        DashboardService.getDashboard()
            .then((res) => {
                setDashboard(res.data);
            })
            .catch((err) => console.log(err));
    };

    return (

        <div className="container-fluid">

            <h2 className="mb-4">
                📊 Material Requirement Planning Dashboard
            </h2>

            <div className="row">

                <div className="col-lg-4 mb-4">
                    <div className="card bg-primary text-white shadow">
                        <div className="card-body text-center">
                            <h5>Total Materials</h5>
                            <h1>{dashboard.materialCount}</h1>
                        </div>
                    </div>
                </div>

                <div className="col-lg-4 mb-4">
                    <div className="card bg-success text-white shadow">
                        <div className="card-body text-center">
                            <h5>Total Suppliers</h5>
                            <h1>{dashboard.supplierCount}</h1>
                        </div>
                    </div>
                </div>

                <div className="col-lg-4 mb-4">
                    <div className="card bg-warning shadow">
                        <div className="card-body text-center">
                            <h5>Total Inventory</h5>
                            <h1>{dashboard.inventoryCount}</h1>
                        </div>
                    </div>
                </div>

                <div className="col-lg-4 mb-4">
                    <div className="card bg-info text-white shadow">
                        <div className="card-body text-center">
                            <h5>Purchase Orders</h5>
                            <h1>{dashboard.purchaseOrderCount}</h1>
                        </div>
                    </div>
                </div>

                <div className="col-lg-4 mb-4">
                    <div className="card bg-secondary text-white shadow">
                        <div className="card-body text-center">
                            <h5>Production Orders</h5>
                            <h1>{dashboard.productionCount}</h1>
                        </div>
                    </div>
                </div>

                <div className="col-lg-4 mb-4">
                    <div className="card bg-danger text-white shadow">
                        <div className="card-body text-center">
                            <h5>Low Stock Items</h5>
                            <h1>{dashboard.lowStockCount}</h1>
                        </div>
                    </div>
                </div>

            </div>

            <div className="row">

                <div className="col-lg-8">

                    <InventoryChart />

                </div>

                <div className="col-lg-4">

                    <div className="card shadow">

                        <div className="card-header bg-danger text-white">
                            ⚠ Low Stock Alert
                        </div>

                        <div className="card-body">

                            <p>Low Stock Materials : <b>{dashboard.lowStockCount}</b></p>

                            <p>Please reorder materials before production is affected.</p>

                        </div>

                    </div>

                    <br />

                    <div className="card shadow">

                        <div className="card-header bg-success text-white">
                            📋 Recent Activities
                        </div>

                        <div className="card-body">

                            <ul>
                                <li>✔ Material Added</li>
                                <li>✔ Supplier Updated</li>
                                <li>✔ Inventory Updated</li>
                                <li>✔ Purchase Order Created</li>
                                <li>✔ Production Scheduled</li>
                            </ul>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );
}

export default Dashboard;