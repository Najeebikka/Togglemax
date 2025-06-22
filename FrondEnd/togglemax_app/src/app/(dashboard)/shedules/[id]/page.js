import CandidatesData from "../../candidates/candidate_show";

export default async function ResultsOfJob({ params }) {
  const baseURL = process.env.INTERNAL_API_URL;
  const { id } = await params;

  const jobRes = await fetch(`${baseURL}/api/jobs/${id}`);
  const job = await jobRes.json();

  const candidateRes = await fetch(`${baseURL}/api/candidates/interviewer/b2b74349-23de-4e76-b0d4-83fc55504eaf/applications`);
  const candidateData = await candidateRes.json();

  const candidates = candidateData.filter(
    (c) => c.jobTitle === job.jobTitle
  );

  return (
    <div className="p-4">
      <h1 className="text-3xl font-bold">
        <a href="/shedules/" className="text-blue-600 hover:underline">←</a> Schedule Detail
      </h1>

      <Details job={job} />
      <h1 className="p-4">Scheduled Candidates</h1>
      <CandidatesData candidates={candidates} />
    </div>
  );
}

function Details({ job }) {
  return (
    <div className="bg-violet-50 p-4 mt-4 rounded shadow mb-2">
      <h1 className="text-xl font-semibold mb-2">Details</h1>
      <p><strong>Job Title:</strong> {job.jobTitle}</p>
      <p><strong>Job Type:</strong> {job.jobType}</p>
      <p><strong>Location:</strong> {job.locations}</p>
      <p><strong>Posted Date:</strong> {job.postedDate}</p>
      <p><strong>Status:</strong> {job.status}</p>
      <p><strong>Count:</strong> {job.count}</p>
    </div>
  );
}
