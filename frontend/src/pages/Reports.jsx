function Reports() {
    return (
        <div className="container-fluid">

            <h2 className="mb-4">📊 Reports</h2>

            <div className="row">

                <div className="col-md-3 mb-4">
                    <div className="card border-primary">
                        <div className="card-body text-center">
                            <h5>Total Materials</h5>
                            <h3>120</h3>
                        </div>
                    </div>
                </div>

                <div className="col-md-3 mb-4">
                    <div className="card border-success">
                        <div className="card-body text-center">
                            <h5>Suppliers</h5>
                            <h3>25</h3>
                        </div>
                    </div>
                </div>

                <div className="col-md-3 mb-4">
                    <div className="card border-warning">
                        <div className="card-body text-center">
                            <h5>Purchase Orders</h5>
                            <h3>40</h3>
                        </div>
                    </div>
                </div>

                <div className="col-md-3 mb-4">
                    <div className="card border-danger">
                        <div className="card-body text-center">
                            <h5>Low Stock</h5>
                            <h3>8</h3>
                        </div>
                    </div>
                </div>

            </div>

            <div className="card mt-4">
                <div className="card-header bg-primary text-white">
                    Report Summary
                </div>

                <div className="card-body">

                    <table className="table table-bordered">

                        <thead className="table-dark">
                            <tr>
                                <th>Report Name</th>
                                <th>Status</th>
                                <th>Generated On</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>Inventory Report</td>
                                <td>Completed</td>
                                <td>Today</td>
                            </tr>

                            <tr>
                                <td>Purchase Report</td>
                                <td>Completed</td>
                                <td>Today</td>
                            </tr>

                            <tr>
                                <td>Production Report</td>
                                <td>Completed</td>
                                <td>Today</td>
                            </tr>
                        </tbody>

                    </table>

                </div>

            </div>

        </div>
    );
}

export default Reports;