"use client";

import { useEffect, useRef, useState } from "react";
import { useRouter } from "next/navigation";

export default function TaskClient({ name, jobtitle, totalquestions, time = 3, questions = [], interviewId }) {
  const videoRef = useRef(null);
  const mediaRecorderRef = useRef(null);
  const streamRef = useRef(null);
  const recordedChunks = useRef([]);
  const timerRef = useRef(null);

  const router = useRouter();

  const [recording, setRecording] = useState(false);
  const [recordedBlob, setRecordedBlob] = useState(null);
  const [secondsLeft, setSecondsLeft] = useState(0);
  const [started, setStarted] = useState(false);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [uploading, setUploading] = useState(false);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [uploadError, setUploadError] = useState(null);
  const [uploadSuccess, setUploadSuccess] = useState(false);

  useEffect(() => {
    async function startCamera() {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
        streamRef.current = stream;
        if (videoRef.current) {
          videoRef.current.srcObject = stream;
        }
      } catch (err) {
        console.error("Failed to access camera/mic", err);
      }
    }

    startCamera();
    return () => {
      streamRef.current?.getTracks().forEach(track => track.stop());
      clearInterval(timerRef.current);
    };
  }, []);

  const startTimer = (duration) => {
    setSecondsLeft(duration);
    timerRef.current = setInterval(() => {
      setSecondsLeft((prev) => {
        if (prev <= 1) {
          clearInterval(timerRef.current);
          handleStopRecording();
          return 0;
        }
        return prev - 1;
      });
    }, 1000);
  };

  const handleStartInterview = () => {
    setStarted(true);
    handleStartRecording();
    startTimer(time * 60); // total seconds
  };

  const handleStartRecording = () => {
    recordedChunks.current = [];
    const options = { mimeType: "video/webm" };
    const recorder = new MediaRecorder(streamRef.current, options);

    recorder.ondataavailable = (e) => {
      if (e.data.size > 0) recordedChunks.current.push(e.data);
    };

    recorder.onstop = () => {
      const blob = new Blob(recordedChunks.current, { type: "video/webm" });
      const url = URL.createObjectURL(blob);
      setRecordedBlob(url);
      uploadVideo(blob);
    };

    recorder.start();
    mediaRecorderRef.current = recorder;
    setRecording(true);
  };

  const handleStopRecording = () => {
    if (mediaRecorderRef.current?.state === "recording") {
      mediaRecorderRef.current.stop();
    }
    setRecording(false);
    clearInterval(timerRef.current);
  };

  const uploadVideo = async (blob) => {
    setUploading(true);
    setUploadProgress(0);
    setUploadSuccess(false);
    setUploadError(null);

    try {
      const formData = new FormData();
      formData.append("file", blob);
      formData.append("interviewId", interviewId);

      const res = await fetch("/task", {
        method: "POST",
        body: formData,
      });

      if (!res.ok) {
        throw new Error(`Upload failed: ${res.status}`);
      }

      const result = await res.json();
      alert("Upload successful!");
      setUploadSuccess(true);
      router.push("/");
    } catch (err) {
      setUploadError(err.message);
    } finally {
      setUploading(false);
    }
  };

  const retryUpload = () => {
    if (recordedBlob) {
      fetch(recordedBlob)
        .then(res => res.blob())
        .then(blob => {
          uploadVideo(blob);
        });
    }
  };

  const formatTime = (secs) => {
    const minutes = Math.floor(secs / 60);
    const seconds = secs % 60;
    return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
  };

  const handleNext = () => {
    if (currentQuestionIndex < questions.length - 1) {
      setCurrentQuestionIndex(currentQuestionIndex + 1);
    } else {
      handleStopRecording();
    }
  };

  return (
    <div className="flex bg-gray-100 flex-col items-center p-10 font-sans md:flex-row gap-8">
      <div className="w-full h-64 bg-white flex-6 rounded-lg shadow-md md:h-[500px] relative">
        <video ref={videoRef} autoPlay muted className="w-full h-full object-cover rounded-lg" />
        {recording && (
          <div className="absolute top-4 right-4 bg-black bg-opacity-60 text-white px-3 py-1 rounded text-sm">
            ⏳ {formatTime(secondsLeft)}
          </div>
        )}
      </div>

      <div className="rounded-lg w-full h-64 flex-5 shadow-md overflow-y-scroll bg-white md:h-[500px] p-4">
        <h1>Welcome, {name}</h1>
        <h3>Job title: {jobtitle}</h3>
        <h3>Total Questions: {totalquestions}</h3>
        <h3>Total Time: {time} minutes</h3>

        {!started ? (
          <button onClick={handleStartInterview} className="mt-4 bg-green-600 text-white px-4 py-2 rounded">
            Start Interview
          </button>
        ) : (
          <>
            <div className="mt-6">
              <h4 className="font-semibold">Question {currentQuestionIndex + 1} of {questions.length}</h4>
              <p className="text-lg mt-2">{questions[currentQuestionIndex]}</p>

              <button onClick={handleNext} className="mt-4 bg-blue-600 text-white px-4 py-1 rounded" disabled={uploading}>
                {currentQuestionIndex < questions.length - 1 ? "Next" : "Finish"}
              </button>
            </div>
          </>
        )}

        {uploading && <div className="mt-4 text-blue-700 font-semibold">Uploading...</div>}
        {uploadError && (
          <div className="mt-4 text-red-600 font-semibold">
            Upload failed: {uploadError}
            <button onClick={retryUpload} className="ml-2 underline text-blue-600 hover:text-blue-800">Retry</button>
          </div>
        )}
      </div>
    </div>
  );
}
