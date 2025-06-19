"use client";

import { useState } from "react";

export default function AddSubjectPopup({ refetchSubjects }) {
  const [show, setShow] = useState(false);
  const [name, setName] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const res = await fetch("http://localhost:8080/api/subjects", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name }),
    });

    if (res.ok) {
      setName("");
      setShow(false);
      refetchSubjects();
    } else {
      alert("Failed to add subject");
    }
  };

  return (
    <>
      <button
        onClick={() => setShow(true)}
        className="bg-blue-600 text-white px-4 py-2 rounded"
      >
        Add Subject
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
            <h2 className="text-xl font-bold mb-4 text-center">Add Subject</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Subject Name"
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
