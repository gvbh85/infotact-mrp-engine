import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
} from "chart.js";

import { Bar } from "react-chartjs-2";

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);

function InventoryChart() {

    const data = {
        labels: [
            "Steel",
            "Cement",
            "Iron",
            "Copper",
            "Plastic"
        ],

        datasets: [
            {
                label: "Available Stock",

                data: [120, 90, 150, 70, 200],

                backgroundColor: [
                    "#0d6efd",
                    "#198754",
                    "#ffc107",
                    "#dc3545",
                    "#6f42c1"
                ]
            }
        ]
    };

    return (

        <div className="card shadow mt-4">

            <div className="card-body">

                <h4>Inventory Overview</h4>

                <Bar data={data} />

            </div>

        </div>

    );

}

export default InventoryChart;