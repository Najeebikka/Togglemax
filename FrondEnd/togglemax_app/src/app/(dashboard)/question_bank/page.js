"use client";

import { useEffect, useState } from "react";
import { QuickActionBox } from "../dashboard/page";
import AddSubjectPopup from "./popup";

export default function QuestionBankPage() {
  const baseURL = process.env.NEXT_PUBLIC_API_URL;
  const [subjects, setSubjects] = useState([]);

  const fetchSubjects = async () => {
    try {
      const response = await fetch(`${baseURL}/api/subjects`);
      const data = await response.json();
      setSubjects(data);
    } catch (error) {
      console.error("Failed to fetch subjects:", error);
    }
  };

  useEffect(() => {
    fetchSubjects();
  }, []);

  const handleDelete = async (id) => {
    const confirmDelete = confirm("Are you sure you want to delete this subject?");
    if (!confirmDelete) return;

    try {
      const res = await fetch(`${baseURL}/api/subjects/${id}`, {
        method: "DELETE",
      });

      if (res.ok) {
        fetchSubjects();
      } else {
        alert("Failed to delete subject");
      }
    } catch (err) {
      console.error("Delete failed:", err);
      alert("An error occurred");
    }
  };

  return (
    <div className="font-[var(--font-geist-sans)]">
      <div className="flex justify-between flex-wrap">
        <h1>Question Bank</h1>
        <AddSubjectPopup refetchSubjects={fetchSubjects} />
      </div>

      <div className="flex items-center gap-5 m-4 p-4 justify-start flex-wrap">
        {subjects.map((subject) => (
          <div key={subject.id} className="relative group">
            <QuickActionBox
              icon=""
              label={subject.name}
              disc={""}
              link={`question_bank/${encodeURIComponent(subject.name)}`}
            />
            <button
              onClick={() => handleDelete(subject.id)}
              className="absolute top-1 right-1 bg-white rounded-full p-1 text-sm hover:bg-red-300"
              title="Delete Subject"
            >
              🗑️
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
