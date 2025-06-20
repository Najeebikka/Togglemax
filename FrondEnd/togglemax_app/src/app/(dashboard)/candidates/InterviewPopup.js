import InterviewClientPopup from "./popup";

export default async function InterviewPopup() {
  const interviewerId = "b2b74349-23de-4e76-b0d4-83fc55504eaf";
  const baseURL = process.env.NEXT_PUBLIC_API_URL;

  const [candidatesRes, jobsRes, subjectsRes] = await Promise.all([
    fetch(`${baseURL}/api/candidates`),
    fetch(`${baseURL}/api/jobs`),
    fetch(`${baseURL}/api/subjects`)
  ]);

  const [candidates, jobs, subjects] = await Promise.all([
    candidatesRes.json(),
    jobsRes.json(),
    subjectsRes.json(),
  ]);

  return (
    <InterviewClientPopup
      candidates={candidates}
      jobs={jobs}
      subjects={subjects}
    />
  );
}
