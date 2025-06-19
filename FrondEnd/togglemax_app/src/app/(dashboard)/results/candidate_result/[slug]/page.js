import TaskClient from "./taskclint";

export default async function Page({ params }) {
  const inter=await params;
  return (
    <>
      <h1 className="text-3xl font-bold">
        <a href="/results/" className="text-blue-600 hover:underline">←</a> Back
      </h1>

      <TaskClient interviewId={inter.slug} />
    </>
  );
}
