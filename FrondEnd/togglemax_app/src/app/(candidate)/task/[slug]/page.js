export const dynamic = 'force-dynamic';

import TaskClient from "./taskClint";
import { redirect } from 'next/navigation';

export default async function TaskPage({ params }) {
  const { slug } = await params;
  const baseURL = process.env.INTERNAL_API_URL;

  let interviewData = null;


  const response = await fetch(`${baseURL}/api/interview-questions/interview-token/${slug}`, {
    cache: "no-store",
  });

  if (!response.ok) redirect('/?not=true');

  interviewData = await response.json();

  if (interviewData.tokenUsed) redirect('/?expired=true');

  // Fallbacks if API fails
  const name = interviewData?.candidateName || "Candidate";
  const jobtitle = interviewData?.jobTitle || "Unknown Role";
  const interviewId = interviewData?.interviewId || "undefined";
  const totalquestions = interviewData?.questionTitles?.length || 0;
  const time = totalquestions * 2; // Each question = 2 mins

  return (
    <TaskClient
      name={name}
      jobtitle={jobtitle}
      totalquestions={totalquestions}
      time={time}
      questions={interviewData?.questionTitles || []}
      interviewId={interviewId}
    />
  );
}
