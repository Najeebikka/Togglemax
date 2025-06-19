import TaskClient from "./taskClint";

export default async function TaskPage({ params }) {
  const { slug } = await params;
  const baseURL = process.env.NEXT_PUBLIC_API_URL;

  let interviewData = null;

  try {
    const response = await fetch(`${baseURL}/api/interview-questions/interview-token/${slug}`, {
      cache: "no-store",
    });

    if (!response.ok) throw new Error("Interview not found");

    interviewData = await response.json();
  } catch (error) {
    console.error("Failed to fetch interview questions:", error);
    interviewData = null;
  }

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
