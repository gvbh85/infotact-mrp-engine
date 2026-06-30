import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./components/Login";
import Register from "./components/Register";
import Dashboard from "./components/Dashboard";

import DashboardLayout from "./layout/DashboardLayout";

import Materials from "./pages/Materials";
import Inventory from "./pages/Inventory";
import Suppliers from "./pages/Suppliers";
import PurchaseOrders from "./pages/PurchaseOrders";
import Bom from "./pages/Bom";
import Production from "./pages/Production";
import Reports from "./pages/Reports";
import Settings from "./pages/Settings";

function App() {
    return (
        <BrowserRouter>

            <Routes>

                {/* Public Routes */}
                <Route path="/" element={<Login />} />
                <Route path="/register" element={<Register />} />

                {/* Protected Layout */}
                <Route element={<DashboardLayout />}>
                

                    <Route path="/dashboard" element={<Dashboard />} />

                    <Route path="/materials" element={<Materials />} />

                    <Route path="/inventory" element={<Inventory />} />

                    <Route path="/suppliers" element={<Suppliers />} />

                    <Route path="/purchase-orders" element={<PurchaseOrders />} />

                    <Route path="/bom" element={<Bom />} />

                    <Route path="/production" element={<Production />} />

                    <Route path="/reports" element={<Reports />} />

                    <Route path="/settings" element={<Settings />} />

                </Route>

            </Routes>

        </BrowserRouter>
    );
}

export default App;