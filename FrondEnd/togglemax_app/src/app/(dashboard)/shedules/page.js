"use client";

import { useEffect, useState } from "react";
import Link from "next/link";

export default function ShedulesPage() {
  const baseURL = process.env.NEXT_PUBLIC_API_URL;
  const [jobs, setJobs] = useState([]);
  const [editingJobId, setEditingJobId] = useState(null);
  const [selectedStatus, setSelectedStatus] = useState("OPEN");

  const fetchJobs = async () => {
    try {
      const res = await fetch(`${baseURL}/api/jobs`);
      const data = await res.json();
      setJobs(data);
    } catch (err) {
      console.error("Failed to fetch jobs:", err);
    }
  };

  const handleUpdate = async (job) => {
    const updatedJob = { ...job, status: selectedStatus };

    try {
        const res = await fetch(`${baseURL}/api/jobs/${job.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedJob),
        });

        if (res.ok) {
        setEditingJobId(null);
        fetchJobs(); 
        } else {
        alert("Update failed");
        }
    } catch (err) {
        console.error("Error updating job:", err);
    }
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  return (
    <div className="font-[var(--font-geist-sans)]">
      <div className="flex justify-between">
        <h1 className="text-xl font-semibold mb-4">All Schedules</h1>
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
              <td className="px-4 py-2 text-blue-600 underline cursor-pointer">
                <Link href={`/shedules/${job.id}`}>{job.jobTitle}</Link>
              </td>
              <td className="px-4 py-2">{job.jobType}</td>
              <td className="px-4 py-2">{job.postedDate}</td>
              <td className="px-4 py-2">{job.count}</td>
              <td className="px-4 py-2">{job.status}</td>
              <td className="px-4 py-2 text-blue-600 cursor-pointer">
                {editingJobId === job.id ? (
                  <select
                    value={selectedStatus}
                    onChange={(e) => setSelectedStatus(e.target.value)}
                    onBlur={() => handleUpdate(job)}
                    className="border p-1 rounded"
                  >
                    <option value="OPEN">OPEN</option>
                    <option value="DRAFT">DRAFT</option>
                    <option value="CLOSED">CLOSED</option>
                  </select>
                ) : (
                  <span
                    onClick={() => {
                      setEditingJobId(job.id);
                      setSelectedStatus(job.status);
                    }}
                  >
                    Update
                  </span>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
