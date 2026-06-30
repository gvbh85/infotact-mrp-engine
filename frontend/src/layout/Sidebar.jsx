import { Link } from "react-router-dom";

const linkStyle = {
  color: "white",
  textDecoration: "none",
  display: "block",
  padding: "10px 0",
};

function Sidebar() {
  return (
    <div
      style={{
        width: "220px",
        background: "#263238",
        color: "white",
        minHeight: "100vh",
        padding: "20px",
      }}
    >
      <h2>MRP System</h2>

      <hr />

      <Link to="/dashboard" style={linkStyle}>🏠 Dashboard</Link>

      <Link to="/materials" style={linkStyle}>📦 Materials</Link>

      <Link to="/inventory" style={linkStyle}>🏭 Inventory</Link>

      <Link to="/suppliers" style={linkStyle}>🚚 Suppliers</Link>

      <Link to="/purchase-orders" style={linkStyle}>🛒 Purchase Orders</Link>

      <Link to="/bom" style={linkStyle}>📋 Bill of Materials</Link>

      <Link to="/production" style={linkStyle}>📈 Production</Link>

      <Link to="/reports" style={linkStyle}>📊 Reports</Link>

      <Link to="/settings" style={linkStyle}>⚙ Settings</Link>
    </div>
  );
}

export default Sidebar;