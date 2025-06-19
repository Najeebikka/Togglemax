"use client";

import { useState } from "react";

export default function CandidatesData({ candidates = [] }) {

    const [data, setData] = useState(candidates);

    async function handleDelete(interviewid) {
        const confirmed = window.confirm("Are you sure you want to delete this interview?");
        if (!confirmed) return;

        try {
            const res = await fetch(`http://localhost:8080/api/interviews/${interviewid}`, {
                method: "DELETE",
            });
            if (res.ok) {
                alert("Interview deleted successfully");
                setData(data.filter(c => c.i_id !== interviewid));
            } else {
                alert("Failed to delete interview");
            }
        } catch (err) {
            console.error("Error deleting interview:", err);
        }
    }

    return (
        <table className="min-w-full">
            <thead>
                <tr className="bg-blue-100">
                    <th className="px-4 py-2"></th>
                    <th className="px-4 py-2">Candidate Name</th>
                    <th className="px-4 py-2">Job Title</th>
                    <th className="px-4 py-2">Date</th>
                    <th className="px-4 py-2">Status</th>
                    <th className="px-4 py-2">Resume</th>
                    <th className="px-4 py-2">Interview Status</th>
                    <th className="px-4 py-2">Delete</th>
                </tr>
            </thead>
            <tbody>
                {data.map((candidate, idx) => (
                    <tr
                        key={idx}
                        className={idx % 2 === 0 ? "bg-white" : "bg-blue-100"}
                    >
                        <td className="px-4 py-2">
                            <input type="checkbox" />
                        </td>
                        <td className="px-4 py-2 flex items-center space-x-2">
                            <img src={candidate.photo} alt={candidate.name} className="size-10 rounded-full" />
                            <div>
                                <div className="font-bold">{candidate.name}</div>
                                <div className="text-sm text-gray-500">{candidate.email}</div>
                            </div>
                        </td>
                        <td className="px-4 py-2">{candidate.jobTitle}</td>
                        <td className="px-4 py-2">{candidate.scheduledDate}</td>
                        <td className="px-4 py-2">{candidate.shortlistStatus}</td>
                        <td className="px-4 py-2">{candidate.resume}</td>
                        <td className="px-4 py-2">{candidate.interviewStatus}</td>
                        <td
                            className="px-4 py-2 text-red-600 cursor-pointer"
                            onClick={() => handleDelete(candidate.i_id)}
                        >
                            Delete
                        </td>

                    </tr>
                ))}
            </tbody>
        </table>
    );
}