"use client";

import { useEffect, useState } from "react";
import JobModal from "./popup";

export default function JobManagingPage() {
  const baseURL = process.env.INTERNAL_API_URL || process.env.NEXT_PUBLIC_API_URL;
    const [jobs, setJobs] = useState([]);

    const fetchJobs = async () => {

         // Uncomment this line if you need to use token for authentication
         
        // const token = localStorage.getItem("token");

        // const res = await fetch("http://localhost:8080/api/jobs", {
        //     headers: {
        //     Authorization: `Bearer ${token}`,
        //     },
        // });

        try {
            const res = await fetch(`${baseURL}/api/jobs`);
            const data = await res.json();
            setJobs(data);
        } catch (err) {
            console.error("Failed to fetch jobs:", err);
        }
    };

    useEffect(() => {
        fetchJobs();
    }, []);

    const handleDelete = async (id) => {
        try {
            const res = await fetch(`${baseURL}/api/jobs/${id}`, {
                method: "DELETE",
            });
            if (res.ok) {
                fetchJobs();
            } else {
                alert("Failed to delete job");
            }
        } catch (err) {
            console.error("Delete failed", err);
            alert("An error occurred");
        }
    };

    return (
        <div className="font-[var(--font-geist-sans)]">
            <div className="flex justify-between">
                <h1>Job Management</h1>
                <JobModal refetchJobs={fetchJobs} />
            </div>
            <table className="min-w-full">
                <thead>
                    <tr className="bg-blue-100">
                        <th className="px-4 py-2"></th>
                        <th className="px-4 py-2">Job Title</th>
                        <th className="px-4 py-2">Job Type</th>
                        <th className="px-4 py-2">Posted Date</th>
                        <th className="px-4 py-2">Application Count</th>
                        <th className="px-4 py-2">Status</th>
                        <th className="px-4 py-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {jobs.map((job, idx) => (
                        <tr key={job.id} className={idx % 2 === 0 ? "bg-white" : "bg-blue-100"}>
                            <td className="px-4 py-2">
                                <input type="checkbox" />
                            </td>
                            <td className="px-4 py-2">{job.jobTitle}</td>
                            <td className="px-4 py-2">{job.jobType}</td>
                            <td className="px-4 py-2">{job.postedDate}</td>
                            <td className="px-4 py-2">{job.count}</td>
                            <td className="px-4 py-2">{job.status}</td>
                            <td
                                className="px-4 py-2 text-red-600 cursor-pointer"
                                onClick={() => handleDelete(job.id)}
                            >
                                Delete
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
