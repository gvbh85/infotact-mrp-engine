import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaUser, FaEnvelope, FaLock } from "react-icons/fa";
import UserService from "../services/UserService";
import "../css/Register.css";

function Register() {

    const navigate = useNavigate();

    const [user, setUser] = useState({
        fullName: "",
        email: "",
        password: "",
        role: "ADMIN"
    });

    const handleChange = (e) => {
        setUser({
            ...user,
            [e.target.name]: e.target.value
        });
    };

    const handleRegister = () => {

        if (
            user.fullName.trim() === "" ||
            user.email.trim() === "" ||
            user.password.trim() === ""
        ) {
            alert("Please fill all fields");
            return;
        }

        UserService.register(user)
            .then(() => {
                alert("Registration Successful");
                navigate("/");
            })
            .catch((err) => {

                if (err.response && err.response.data) {
                    alert(err.response.data);
                } else {
                    alert("Registration Failed");
                }

            });

    };

    return (

        <div className="register-container">

            <div className="register-card">

                <div className="left-side">

                    <h2>📦 MRP SYSTEM</h2>

                    <h4>Create Admin Account</h4>

                    <p>
                        Register to access the
                        Material Requirement Planning System.
                    </p>

                </div>

                <div className="right-side">

                    <h2 className="text-center mb-4">
                        Registration
                    </h2>

                    <div className="mb-3">

                        <label>Full Name</label>

                        <div className="input-group">

                            <span className="input-group-text">
                                <FaUser />
                            </span>

                            <input
                                type="text"
                                name="fullName"
                                className="form-control"
                                placeholder="Enter Full Name"
                                value={user.fullName}
                                onChange={handleChange}
                            />

                        </div>

                    </div>

                    <div className="mb-3">

                        <label>Email</label>

                        <div className="input-group">

                            <span className="input-group-text">
                                <FaEnvelope />
                            </span>

                            <input
                                type="email"
                                name="email"
                                className="form-control"
                                placeholder="Enter Email"
                                value={user.email}
                                onChange={handleChange}
                            />

                        </div>

                    </div>

                    <div className="mb-4">

                        <label>Password</label>

                        <div className="input-group">

                            <span className="input-group-text">
                                <FaLock />
                            </span>

                            <input
                                type="password"
                                name="password"
                                className="form-control"
                                placeholder="Enter Password"
                                value={user.password}
                                onChange={handleChange}
                            />

                        </div>

                    </div>

                    <button
                        className="btn btn-success btn-register w-100"
                        onClick={handleRegister}
                    >
                        Register
                    </button>

                    <p className="text-center mt-3">

                        Already have an account?{" "}

                        <Link to="/">
                            Login
                        </Link>

                    </p>

                </div>

            </div>

        </div>

    );

}

export default Register;