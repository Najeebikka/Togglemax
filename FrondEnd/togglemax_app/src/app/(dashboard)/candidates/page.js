export const dynamic = "force-dynamic";

import CandidatesData from "./candidate_show";
import Candidates from "../candidates_data";
import InterviewPopup from "./InterviewPopup";

  const baseURL = process.env.NEXT_PUBLIC_API_URL;

export default async function CandidatePage() {
    
  let Candidates = [];

    try {
      const response = await fetch(`${baseURL}/api/candidates/interviewer/b2b74349-23de-4e76-b0d4-83fc55504eaf/applications`,
        {
          cache: "no-store", 
        }
      );
      Candidates = await response.json();
    } catch (error) {
        console.error('Failed to fetch jobs:', error);
        Candidates = [];
    }
    
    return (
        <div>
            <div className="flex justify-between items-center mb-4">
                <h1>All Candidates</h1>
                <InterviewPopup />
            </div>
            <CandidatesData candidates={Candidates}/>
        </div>
    );
}