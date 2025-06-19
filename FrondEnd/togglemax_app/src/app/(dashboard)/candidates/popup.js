"use client";

import { useState, useTransition } from "react";
import { useRouter } from "next/navigation";
import { createInterviewAction } from "./createInterview";

export default function InterviewClientPopup({ candidates, jobs, subjects }) {
  const [show, setShow] = useState(false);
  const [selectedSubjects, setSelectedSubjects] = useState([]);
  const [isPending, startTransition] = useTransition();
  const router = useRouter();

  const handleCheckboxChange = (e) => {
    const id = e.target.value;
    setSelectedSubjects((prev) =>
      e.target.checked ? [...prev, id] : prev.filter((sid) => sid !== id)
    );
  };

  const handleClose = () => {
    setShow(false);
    setSelectedSubjects([]);
  };

  return (
    <>
      <button onClick={() => setShow(true)} className="bg-blue-600 text-white px-4 py-2 rounded">
        Schedule Interview
      </button>

      {show && (
        <div onClick={handleClose} className="fixed inset-0 bg-black/40 flex justify-center items-center z-50">
          <div onClick={(e) => e.stopPropagation()} className="bg-white p-6 rounded-lg shadow-md w-full max-w-md overflow-y-auto max-h-screen">
            <h2 className="text-xl font-bold mb-4 text-center">Schedule Interview</h2>

            <form action={createInterviewAction} className="space-y-4">
              <select name="candidateId" required className="w-full border px-3 py-2 rounded">
                <option value="">Select Candidate</option>
                {candidates.map((c) => (
                  <option key={c.id} value={c.id}>
                    {c.name}
                  </option>
                ))}
              </select>

              <select name="jobId" required className="w-full border px-3 py-2 rounded">
                <option value="">Select Job</option>
                {jobs.map((j) => (
                  <option key={j.id} value={j.id}>
                    {j.jobTitle}
                  </option>
                ))}
              </select>

              <input type="datetime-local" name="scheduledDate" required className="w-full border px-3 py-2 rounded" />

              {subjects.length > 0 && (
                <div className="border p-3 rounded bg-gray-50 mb-4 max-h-60 overflow-y-auto">
                  <p className="font-semibold mb-2">Select Subjects:</p>
                  <ul className="space-y-2 ml-2">
                    {subjects.map((subject) => (
                      <li key={subject.id} className="flex items-start gap-2 text-sm">
                        <input
                          type="checkbox"
                          value={subject.id}
                          checked={selectedSubjects.includes(subject.id)}
                          onChange={handleCheckboxChange}
                        />
                        <span>{subject.name}</span>
                      </li>
                    ))}
                  </ul>
                </div>
              )}

              {selectedSubjects.map((subjectId) => (
                <input key={subjectId} type="hidden" name="subjectIds" value={subjectId} />
              ))}

              <div className="text-center">
                <button type="submit" disabled={isPending} className="bg-green-600 text-white px-6 py-2 rounded">
                  {isPending ? "Scheduling..." : "Submit"}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </>
  );
}
