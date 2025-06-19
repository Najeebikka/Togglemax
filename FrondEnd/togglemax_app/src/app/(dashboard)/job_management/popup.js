"use client";

import { useState } from "react";

export default function JobModal({ refetchJobs }) {
    const [showForm, setShowForm] = useState(false);
    const [form, setForm] = useState({
        jobTitle: "",
        jobType: "FULL_TIME",
        locations: "",
        postedDate: "",
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const payload = {
            ...form,
            status: "OPEN",
            interviewerId: "b2b74349-23de-4e76-b0d4-83fc55504eaf",
            count: 0,
        };

        try {
            const res = await fetch("http://localhost:8080/api/jobs", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });

            if (res.ok) {
                setForm({
                    jobTitle: "",
                    jobType: "FULL_TIME",
                    locations: "",
                    postedDate: "",
                });
                setShowForm(false);
                refetchJobs?.();
            } else {
                alert("Failed to create job.");
            }
        } catch (err) {
            console.error(err);
            alert("Error creating job.");
        }
    };

    return (
        <div>
            <button onClick={() => setShowForm(true)}>Add Job</button>
            {showForm && (
                <div
                    className="fixed inset-0 flex items-center justify-center bg-black/20 z-50"
                    onClick={() => setShowForm(false)}
                >
                    <div
                        className="bg-white bg-opacity-80 p-6 rounded shadow-lg min-w-[300px]"
                        onClick={(e) => e.stopPropagation()}
                    >
                        <h2 className="text-xl font-semibold mb-4 text-center">Add New Job</h2>
                        <form onSubmit={handleSubmit} className="space-y-4">
                            <input name="jobTitle" value={form.jobTitle} onChange={handleChange} placeholder="Job Title" required className="w-full border px-2 py-1 rounded" />
                            
                            <select name="jobType" value={form.jobType} onChange={handleChange} required className="w-full border px-2 py-1 rounded">
                                <option value="FULL_TIME">Full Time</option>
                                <option value="INTERNSHIP">Internship</option>
                                <option value="REMOTE">Remote</option>
                                <option value="HYBRID">Hybrid</option>
                            </select>
                            
                            <input name="locations" value={form.locations} onChange={handleChange} placeholder="Location" required className="w-full border px-2 py-1 rounded" />
                            
                            <input type="date" name="postedDate" value={form.postedDate} onChange={handleChange} required className="w-full border px-2 py-1 rounded" />
                            
                            <div className="flex justify-center">
                                <button type="submit" className="px-4 py-2 bg-blue-600 text-white rounded">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}
