export const dynamic = 'force-dynamic';
import CandidatesData from './candidate_show';
import InterviewPopup from './InterviewPopup';

export default async function CandidatePage() {
  
  const baseURL = process.env.INTERNAL_API_URL;

  let Candidates = [];

  try {
    const res = await fetch(
      `${baseURL}/api/candidates/interviewer/b2b74349-23de-4e76-b0d4-83fc55504eaf/applications`,
      { cache: 'no-store' }
    );
    if (!res.ok) {
      console.error('Fetch failed:', res.status, res.statusText);
      Candidates = [];
    } else {
      Candidates = await res.json();
    }
  } catch (err) {
    console.error('Fetch error:', err);
    Candidates = [];
  }

  return (
    <div>
      <div className="flex justify-between items-center mb-4">
        <h1>All Candidates</h1>
        <InterviewPopup />
      </div>
      <CandidatesData candidates={Candidates} />
    </div>
  );
}
