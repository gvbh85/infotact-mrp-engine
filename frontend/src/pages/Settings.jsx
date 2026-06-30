function Settings() {

    return (

        <div className="container">

            <h2 className="mb-4">⚙ Settings</h2>

            <div className="card">

                <div className="card-body">

                    <div className="mb-3">

                        <label className="form-label">
                            Company Name
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            value="MRP System Pvt Ltd"
                            readOnly
                        />

                    </div>

                    <div className="mb-3">

                        <label className="form-label">
                            Admin Email
                        </label>

                        <input
                            type="email"
                            className="form-control"
                            value="admin@mrp.com"
                            readOnly
                        />

                    </div>

                    <div className="mb-3">

                        <label className="form-label">
                            Version
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            value="Version 1.0"
                            readOnly
                        />

                    </div>

                    <div className="mb-3">

                        <label className="form-label">
                            Database
                        </label>

                        <input
                            type="text"
                            className="form-control"
                            value="MySQL"
                            readOnly
                        />

                    </div>

                    <button className="btn btn-primary">
                        Save Settings
                    </button>

                </div>

            </div>

        </div>

    );
}

export default Settings;