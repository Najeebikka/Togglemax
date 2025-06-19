"use client";

import { useState, useEffect } from "react";

export default function AddQuestionPopup({ refetchQuestions, subjectName }) {
  const [show, setShow] = useState(false);
  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [subjectId, setSubjectId] = useState(null);

  useEffect(() => {
    const fetchSubjectId = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/subjects");
        if (res.ok) {
          const data = await res.json();
          const matchedSubject = data.find(
            (subject) => subject.name.toLowerCase() === subjectName.toLowerCase()
          );
          if (matchedSubject) {
            setSubjectId(matchedSubject.id);
          } else {
            console.error("Subject not found in list");
          }
        } else {
          console.error("Failed to fetch subjects list");
        }
      } catch (err) {
        console.error("Failed to fetch subjects:", err);
      }
    };

    if (subjectName) {
      fetchSubjectId();
    }
  }, [subjectName]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!subjectId) {
      alert("Subject not found. Cannot add question.");
      return;
    }

    const res = await fetch("http://localhost:8080/api/questions", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ question, answer, subjectId }),
    });

    if (res.ok) {
      setQuestion("");
      setAnswer("");
      setShow(false);
      refetchQuestions();
    } else {
      alert("Failed to add question");
    }
  };

  return (
    <>
      <button
        onClick={() => setShow(true)}
        className="bg-blue-600 text-white px-4 py-2 rounded"
      >
        Add Question
      </button>

      {show && (
        <div
          onClick={() => setShow(false)}
          className="fixed inset-0 bg-black/30 flex justify-center items-center z-50"
        >
          <div
            onClick={(e) => e.stopPropagation()}
            className="bg-white p-6 rounded shadow-md w-full max-w-sm"
          >
            <h2 className="text-xl font-bold mb-4 text-center">
              Add Question for <span className="text-blue-700">{subjectName}</span>
            </h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <textarea
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
                placeholder="Enter question text"
                required
                className="w-full border px-3 py-2 rounded"
              />
              <textarea
                value={answer}
                onChange={(e) => setAnswer(e.target.value)}
                placeholder="Enter answer"
                required
                className="w-full border px-3 py-2 rounded"
              />
              <div className="text-center">
                <button
                  type="submit"
                  className="bg-green-600 text-white px-6 py-2 rounded"
                >
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </>
  );
}
