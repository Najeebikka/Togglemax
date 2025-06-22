"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";

export default function Results() {
  const baseURL = process.env.INTERNAL_API_URL || process.env.NEXT_PUBLIC_API_URL;
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const router = useRouter();

  useEffect(() => {
    fetch(`${baseURL}/api/interview-results`)
      .then((res) => res.json())
      .then((data) => {
        setResults(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching results:", err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p className="p-4">Loading...</p>;

  return (
    <div className="p-4">
      <h1 className="text-xl font-bold mb-4">Interview Results</h1>

      <table className="w-full border border-gray-300 text-sm">
        <thead className="bg-gray-100">
          <tr>
            <th className="border px-4 py-2 text-left">Candidate Name</th>
            <th className="border px-4 py-2 text-left">Job Title</th>
            <th className="border px-4 py-2">Uploaded Time</th>
            <th className="border px-4 py-2">Action</th>
          </tr>
        </thead>
        <tbody>
          {results.map((result, index) => (
            <tr key={index}>
              <td className="border px-4 py-2">{result.candidateName}</td>
              <td className="border px-4 py-2">{result.jobTitle}</td>
              <td className="border px-4 py-2">{result.uploadedAt}</td>
              <td className="border px-4 py-2 text-center">
                <button
                  onClick={() => router.push(`/results/candidate_result/${result.interviewId}`)}
                  className="bg-blue-600 text-white px-3 py-1 rounded"
                >
                  View Video
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
