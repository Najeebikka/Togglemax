import { usePathname } from "next/navigation";
import Link from "next/link";
import { logout } from "@/lib/api";
import { useRouter } from "next/navigation";

export default function Nav() {
    const pathname = usePathname();
    return (
        <nav className="flex sticky top-0 right-0 bg-blue-50 w-full p-4 justify-between items-center">

            <Search />
            {pathname.startsWith('/candidates') && <CandidateFilter /> }
            <Profile />

        </nav>
    );
}
9
function CandidateFilter() {
    const pathname = usePathname();
    const isActive = (path) => pathname === path;
    const linkClass = (path) => `px-3 py-1 rounded ${isActive(path) ? "bg-orange-500 text-white" : "text-black hover:text-orange-300"}`;
    return (
        <div className="flex gap-2">
            <Link href="/candidates"><button className={linkClass('/candidates')}>All Candidate</button></Link>
            <Link href="/candidates/shortlisted_candidates"><button className={linkClass('/candidates/shortlisted_candidates')}>Shortlisted Candidate</button></Link>
            <Link href="/candidates/rejected_candidates"><button className={linkClass('/candidates/rejected_candidates')}>Rejected Candidate</button></Link>
            <Link href="/candidates/pending_candidates"><button className={linkClass('/candidates/pending_candidates')}>Pending Candidate</button></Link>
        </div>
    );
}

function Search() {
    return (
        <div className="flex items-center gap-2">
            <input
                type="text"
                placeholder="🔍 ✺ Search"
                className="px-3 py-1.5 border bg-white border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-purple-400"
            />
        </div>
    );
}

function Profile() {
    return (
        <div className="flex items-center gap-2">
            <p>🔔</p>
            <img
                alt=""
                src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                className="size-10 rounded-full"
            />
            <h5>Name</h5>
            <p>⎋</p>
            <LogoutButton />
        </div>
    );
}

function LogoutButton() {
  const router = useRouter();

  const handleLogout = async () => {
    try {
      await logout();
      router.push("/login");
    } catch (error) {
      console.error("Logout failed", error);
    }
  };

  return (
    <button onClick={handleLogout} className="bg-red-600 text-white px-4 py-2 rounded">
      Logout
    </button>
  );
}
