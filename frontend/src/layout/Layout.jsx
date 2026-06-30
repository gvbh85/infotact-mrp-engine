import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import { Outlet } from "react-router-dom";

function Layout() {
  return (
    <div>
      <Navbar />

      <div style={{ display: "flex" }}>
        <Sidebar />

        <div
          style={{
            flex: 1,
            padding: "20px",
            background: "#f4f6f9",
            minHeight: "100vh"
          }}
        >
          <Outlet />
        </div>
      </div>
    </div>
  );
}

export default Layout;