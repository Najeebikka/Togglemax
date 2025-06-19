import { NextResponse } from "next/server";

export async function POST(req) {
  try {
    const formData = await req.formData();
    const file = formData.get("file");
    const interviewId = formData.get("interviewId");
    const baseURL = process.env.NEXT_PUBLIC_API_URL;

    if (!file || typeof file === "string") {
      return NextResponse.json({ error: "Invalid file" }, { status: 400 });
    }

    const buffer = Buffer.from(await file.arrayBuffer());

    const uploadForm = new FormData();
    uploadForm.append("file", new Blob([buffer], { type: file.type }), file.name || "video.webm");
    uploadForm.append("interviewId", interviewId);

    const backendRes = await fetch(`${baseURL}/api/interview-results/upload`, {
      method: "POST",
      body: uploadForm,
    });

    const responseText = await backendRes.text(); // or .json()
    return NextResponse.json({ message: "Uploaded to backend", response: responseText });
  } catch (err) {
    console.error("Upload error:", err);
    return NextResponse.json({ error: "Upload failed" }, { status: 500 });
  }
}
