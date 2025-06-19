"use client";

import { useState, useEffect } from "react";
import AddQuestionPopup from "./popup";
import { useParams } from "next/navigation";

export default function Page() {
  const { slug } = useParams();
  const decodedSlug = decodeURIComponent(slug);
  const [questions, setQuestions] = useState([]);

  const fetchQuestions = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/questions/by-subject-name?subjectName=${encodeURIComponent(decodedSlug)}`);
      const data = await response.json();
      setQuestions(data);
    } catch (error) {
      console.error("Failed to fetch questions:", error);
      setQuestions([]);
    }
  };

  useEffect(() => {
    fetchQuestions();
  }, [decodedSlug]);

  const handleDelete = async (id) => {
    const confirmed = confirm("Are you sure you want to delete?");
    if (!confirmed) return;

    try {
      const res = await fetch(`http://localhost:8080/api/questions/${id}`, {
        method: "DELETE",
      });
      if (res.ok) {
        fetchQuestions();
      } else {
        alert("Failed to delete question");
      }
    } catch (err) {
      console.error("Delete failed", err);
      alert("An error occurred");
    }
  };

  return (
    <div className="p-4">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">
          <a href="/question_bank/" className="text-blue-600 hover:underline">←</a> {decodedSlug}
        </h1>
        <AddQuestionPopup refetchQuestions={fetchQuestions} subjectName={decodedSlug} />
      </div>

      {questions.length === 0 ? (
        <p>No questions found for this subject.</p>
      ) : (
        <ul className="space-y-4">
          {questions.map(({ id, question, answer }, index) => (
            <li key={id} className="bg-gray-100 p-4 rounded shadow">
              <p className="text-lg font-medium flex justify-between">
                {index + 1}. {question}
                <span onClick={() => handleDelete(id)} className="cursor-pointer">🗑️</span>
              </p>
              <p className="text-sm text-gray-700 mt-2">{answer}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
