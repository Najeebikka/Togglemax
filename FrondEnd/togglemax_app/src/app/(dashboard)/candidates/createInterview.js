"use server";

import { revalidatePath } from "next/cache";

export async function createInterviewAction(formData) {
  const interviewerId = "b2b74349-23de-4e76-b0d4-83fc55504eaf";
  const candidateId = formData.get("candidateId");
  const jobId = formData.get("jobId");
  const scheduledDate = formData.get("scheduledDate");
  const subjectIds = formData.getAll("subjectIds");

  const baseURL = process.env.NEXT_PUBLIC_API_URL || "http://backend:8080";

  const payload = {
    interviewerId,
    candidateId,
    jobId,
    scheduledDate,
    token: "",
    interviewStatus: "SCHEDULED",
    shortlistedStatus: "PENDING",
  };

  const res = await fetch(`${baseURL}/api/interviews`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (!res.ok) throw new Error("Interview creation failed.");

  const createdInterview = await res.json();

  const linkRes = await fetch(`${baseURL}/api/interview-questions/bulk`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ interviewId: createdInterview.id, subjectIds }),
  });

  if (!linkRes.ok) throw new Error("Failed to link subjects.");

  revalidatePath(`/candidates/interviewer/${interviewerId}/applications`);
  revalidatePath("/candidates");
}
