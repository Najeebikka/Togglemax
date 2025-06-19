import CandidatesData from "../candidates/candidate_show";
import Candidates from "../candidates_data";
import AddQuestionPopup from "../question_bank/popup";
import JobModal from "../job_management/popup";
import EmailTemplatePopup from "../email_templates/popup";

export default async function DashboardPage() {
  
  let Candidates = [];
  const baseURL = process.env.NEXT_PUBLIC_API_URL;


    try {
      const response = await fetch(`${baseURL}/api/candidates/interviewer/b2b74349-23de-4e76-b0d4-83fc55504eaf/applications`);
      Candidates = await response.json();
    } catch (error) {
        console.error('Failed to fetch jobs:', error);
        Candidates = [];
    }

  return (
    <div>
      <Notification />
      <QuickAction />
      <Candidates_section />
      <CandidatesData candidates={Candidates} />
    </div>
  );
}

export const Notification = () => {
  return (
    <>
      <h1>Notification</h1>
      <div className="flex justify-between items-center mb-4 bg-orange-100 p-4 rounded">
        <div className="flex items-center gap-4">
          <div>
            🔔
          </div>
          <div className="flex-col items-center ">
            <span className="font-semibold">New Candidate Applied?</span>
            <p className="text-orange-500">You have 3 new messages.</p>
          </div>
        </div>
        <a href="candidates/" className="bg-orange-500 text-white px-4 py-2 rounded">
          View Details
        </a>
      </div>
    </>
  );
}

export const QuickAction = () => {
  return (
    <>
      <h1>Quick Action</h1>
      <div className="flex items-center gap-5 items-center m-4 p-4">
        <QuickActionBox link={<JobModal />} icon = "" label="Create Jobs" disc="Easly post new job openings by filing in essential" button={true} />
        <QuickActionBox link={<EmailTemplatePopup />} icon = "" label="Email Template" disc="Easly post new job openings by filing in essential" button={true} />
        <QuickActionBox link={<AddQuestionPopup />} icon = "" label="Create Question" disc="Easly post new job openings by filing in essential" button={true} />
      </div>
    </>
  );
}

export const Candidates_section = () => {
  return (
    <div className="flex justify-between items-center mb-4">
      <h1>Candidates</h1>
      <a href="./candidates" className="text-blue-500 hover:underline">See all ⏵</a>
    </div>
  );
}

export const QuickActionBox = ({ icon, label, disc, button = false, link = "#" }) => {
  if (button) {
    return (
      <div className="bg-purple-100 h-[200px] w-[230px] p-2 rounded items-center gap-2">
        {icon}
        <h6>{label}</h6>
        <p className="text-sm text-gray-500">{disc}</p>
        {link}
      </div>
    );
  } else {
    return (
      <a
        className="bg-purple-100 h-[200px] w-[230px] p-2 rounded items-center gap-2 cursor-pointer block hover:bg-purple-200 transition"
        tabIndex={0}
        href={link}
      >
        {icon}
        <h6>{label}</h6>
        <p className="text-sm text-gray-500">{disc}</p>
      </a>
    );
  }
}