"use client";

import Link from "next/link";
import { useSearchParams, useRouter, usePathname } from "next/navigation";
import { useState,useEffect,useRef } from "react";
import LoginPopup from "./loginpopup";

export default function WelcomePage() {
  const [token, setToken] = useState("");
  const router = useRouter();
  const params = useSearchParams();
  const pathname = usePathname();
  const shown = useRef(false);

  useEffect(() => {
    if (shown.current) return;
    shown.current = true;

    const expired = params.get('expired');
    const not = params.get('not');

    if (expired === 'true') {
      alert('Interview has already been taken or token expired.');
    } else if (not === 'true') {
      alert('Interview is not scheduled');
    }

    // Clear those query params without page reload
    const nextParams = new URLSearchParams(params.toString());
    nextParams.delete('expired');
    nextParams.delete('not');
    const newUrl = nextParams.toString()
      ? `${pathname}?${nextParams}`
      : pathname;

    router.replace(newUrl);
  }, [params, pathname, router]);

  const handleStart = () => {
    if (token.trim()) {
      router.push(`/task/${token}`);
    } else {
      alert("Please enter your token.");
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-blue-100 flex items-center justify-center">
      <div href="/login" className="absolute top-4 right-4 text-blue-600 hover:underline">
        <LoginPopup />
      </div>
      <div className="bg-white shadow-xl rounded-2xl p-8 max-w-md w-full text-center">
        <h1 className="text-3xl font-bold text-blue-700 mb-4">🎤 Welcome to Your Interview</h1>
        <p className="text-gray-600 mb-6">
          Please enter your unique token to start the interview.
        </p>

        <input
          type="text"
          value={token}
          onChange={(e) => setToken(e.target.value)}
          placeholder="Enter Interview Token"
          className="w-full px-4 py-2 border rounded-lg mb-4 focus:outline-none focus:ring-2 focus:ring-blue-500"
        />

        <button
          onClick={handleStart}
          className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 px-4 rounded-lg font-semibold"
        >
          Start Interview
        </button>

        <p className="text-sm text-gray-400 mt-6">Make sure you're in a quiet place with your camera on.</p>
      </div>
    </div>
  );
}
