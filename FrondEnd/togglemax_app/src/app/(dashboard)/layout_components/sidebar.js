import Link from "next/link";
import { usePathname } from "next/navigation";
import Logo from "./logo";

export default function Sidebar() {
    return (
        <aside className="p-4 h-screen bg-blue-50 gap-4 flex flex-col items-center">
            
            <Logo />
            <SidebarLink />

        </aside>
    );
}

function SidebarLink() {
    const pagename = usePathname();
    const isActive = (path) => pagename.startsWith(path);
    const linkClass = (path) => `block mb-2 hover:text-sky-700 ${isActive(path) ? "text-violet-500 font-semibold hover:text-violet-600" : "text-black"}`;
    return (
        <div>
            <ul className="text-[0.6rem] font-thin mt-5 mb-3">Main Menu</ul>

            <Link href="/dashboard" className={linkClass("/dashboard")}><p className="flex w-50 justify-between">❖ Dashboard <span>3new</span></p></Link>
            <Link href="/job_management" className={linkClass("/job_management")}>✍︎ Job Management</Link>
            <Link href="/question_bank" className={linkClass("/question_bank")}>❖ Question Bank</Link>
            <Link href="/email_templates" className={linkClass("/email_templates")}>✍︎ Email Template</Link>
            <Link href="/candidates" className={linkClass("/candidates")}>👤 Candidates</Link>
            <Link href="/shedules" className={linkClass("/shedules")}><p className="flex w-50 justify-between">🗓️ Schedules <span>2new</span></p></Link>
            <Link href="/results" className={linkClass("/results")}><p className="flex w-50 justify-between">🔖 Results <span>1new</span></p></Link>
            <Link href="/add_candidates" className={linkClass("/add_candidates")}>👨🏻‍🎓 ADD Candidates</Link>

            <h6 className="text-[0.6rem] font-thin mt-8 mb-3">Options</h6>
            <ul>
                <Link href="#" className="block mb-2 hover:text-sky-700">⚙︎ Settings</Link>
                <Link href="#" className="block mb-2 hover:text-sky-700">ⓘ Help Center</Link>
            </ul>
        </div>
    );
}