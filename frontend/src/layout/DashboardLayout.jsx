import { Outlet } from "react-router-dom";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";

function DashboardLayout() {
    return (
        <>
            <Navbar />

            <div style={{ display: "flex" }}>
                <Sidebar />

                <div
                    style={{
                        flex: 1,
                        padding: "20px",
                        backgroundColor: "#f5f5f5",
                        minHeight: "100vh"
                    }}
                >
                    <Outlet />
                </div>
            </div>
        </>
    );
}

export default DashboardLayout;