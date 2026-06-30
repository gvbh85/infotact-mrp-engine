import { useNavigate } from "react-router-dom";

function Navbar() {

    const navigate = useNavigate();

    const handleLogout = () => {

        // Clear stored data (for future use)
        localStorage.clear();
        sessionStorage.clear();

        // Redirect to Login page
        navigate("/");

    };

    return (
        <div
            style={{
                height: "60px",
                background: "#1976d2",
                color: "white",
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                padding: "0 20px",
            }}
        >
            <h2>MRP System</h2>

            <div>
                Welcome Admin &nbsp;

                <button
                    onClick={handleLogout}
                    style={{
                        background: "#dc3545",
                        color: "white",
                        border: "none",
                        padding: "8px 15px",
                        borderRadius: "5px",
                        cursor: "pointer"
                    }}
                >
                    Logout
                </button>

            </div>

        </div>
    );
}

export default Navbar;