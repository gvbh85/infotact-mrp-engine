import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import UserService from "../services/UserService";
import "../css/Login.css";

function Login() {

    const navigate = useNavigate();

    const [login, setLogin] = useState({
        email: "",
        password: ""
    });

    const handleChange = (e) => {

        setLogin({
            ...login,
            [e.target.name]: e.target.value
        });

    };

    const handleLogin = () => {

        if (login.email.trim() === "" || login.password.trim() === "") {

            alert("Please enter Email and Password");

            return;

        }

        UserService.login(login)

            .then((res) => {

                alert(res.data);

                navigate("/dashboard");

            })

            .catch((err) => {

                if (err.response && err.response.data) {

                    alert(err.response.data);

                } else {

                    alert("Login Failed");

                }

            });

    };

    return (

        <div className="login-container">

            <div className="login-card">

                <div className="left-panel">

                    <h1>📦 MRP SYSTEM</h1>

                    <h4>Material Requirement Planning</h4>

                    <p>Manage your inventory, suppliers and production efficiently.</p>

                </div>

                <div className="right-panel">

                    <h2>Login</h2>

                    <input
                        className="form-control mb-3"
                        type="email"
                        name="email"
                        placeholder="Enter Email"
                        value={login.email}
                        onChange={handleChange}
                    />

                    <input
                        className="form-control mb-3"
                        type="password"
                        name="password"
                        placeholder="Enter Password"
                        value={login.password}
                        onChange={handleChange}
                    />

                    <button
                        className="btn btn-primary w-100"
                        onClick={handleLogin}
                    >
                        Login
                    </button>

                    <p className="text-center mt-3">

                        Don't have an account?

                        <Link to="/register"> Register</Link>

                    </p>

                </div>

            </div>

        </div>

    );

}

export default Login;