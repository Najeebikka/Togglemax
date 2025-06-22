"use client";

import { useEffect, useState, useRef } from "react";

export default function TaskClient({ interviewId }) {
  const videoRef = useRef(null);
  const baseURL = process.env.INTERNAL_API_URL || process.env.NEXT_PUBLIC_API_URL;

  const [name, setName] = useState("");
  const [jobtitle, setJobtitle] = useState("");
  const [questionsAndAnswers, setQuestionsAndAnswers] = useState([]);
  const [videoUrl, setVideoUrl] = useState(null);

  useEffect(() => {
    async function fetchInterviewData() {
      try {
        const res = await fetch(`${baseURL}/api/interview-results/interview/${interviewId}`);
        if (!res.ok) throw new Error("Failed to fetch interview data");

        const dataArray = await res.json();
        const data = dataArray[0];

        setName(data.candidateName || "");
        setJobtitle(data.jobTitle || "");

        if (Array.isArray(data.questionsAndAnswers)) {
          setQuestionsAndAnswers(data.questionsAndAnswers);
        } else {
          setQuestionsAndAnswers([]);
        }

        setVideoUrl(data.videoUrl || null);
      } catch (error) {
        console.error("Error fetching interview data:", error);
      }
    }

    if (interviewId) {
      fetchInterviewData();
    }
  }, [interviewId]);

  return (
    <div className="flex bg-gray-100 flex-col items-center p-10 font-sans md:flex-row gap-8">
      <div className="w-full h-64 bg-black flex-6 rounded-lg shadow-md md:h-[500px] relative">
        {videoUrl ? (
          <video
            ref={videoRef}
            src={videoUrl ? `${baseURL}/${videoUrl}` : ""}
            controls
            className="w-full h-full object-cover rounded-lg"
          />
        ) : (
          <div className="flex justify-center items-center h-full text-white">
            Loading video...
          </div>
        )}
      </div>
      
      <div className="rounded-lg w-full h-64 flex-5 shadow-md overflow-y-scroll bg-white md:h-[500px] p-4">
        <h1>Welcome, {name || "Loading..."}</h1>
        <h3>Job title: {jobtitle || "Loading..."}</h3>
        <h3>Total Questions: {questionsAndAnswers.length}</h3>

        <div className="mt-6">
          <ul className="space-y-4">
            {questionsAndAnswers.map(({ questionText, answerText }, idx) => (
              <li key={idx} className="border-b pb-3">
                <p className="font-semibold">Q{idx + 1}: {questionText}</p>
                <p className="mt-1 text-gray-700">Answer: {answerText || "No answer provided"}</p>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
}
