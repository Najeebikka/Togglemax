"use client";

import { useState } from "react";

export default function EmailTemplatePopup({ refetchTemplates }) {
  const [showForm, setShowForm] = useState(false);
  const baseURL = process.env.INTERNAL_API_URL || process.env.NEXT_PUBLIC_API_URL;
  const [form, setForm] = useState({
    templateName: "",
    category: "INTERVIEW_INVITATION",
    subject: "",
    emailBody: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
        console.log("start")
      const res = await fetch(`${baseURL}/api/email-templates`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });
      console.log(res)

      if (res.ok) {
        setForm({
          templateName: "",
          category: "INTERVIEW_INVITATION",
          subject: "",
          emailBody: "",
        });
        setShowForm(false);
        refetchTemplates?.();
      } else {
        alert("Failed to create email template.");
      }
    } catch (err) {
      console.error(err);
      alert("Error creating email template.");
    }
  };

  return (
    <div>
      <button onClick={() => setShowForm(true)} className="bg-blue-600 text-white px-4 py-2 rounded">
        Add Email Template
      </button>
      {showForm && (
        <div
          className="fixed inset-0 flex items-center justify-center bg-black/20 z-50"
          onClick={() => setShowForm(false)}
        >
          <div
            className="bg-white bg-opacity-90 p-6 rounded shadow-lg min-w-[320px]"
            onClick={(e) => e.stopPropagation()}
          >
            <h2 className="text-xl font-semibold mb-4 text-center">New Email Template</h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input
                name="templateName"
                value={form.templateName}
                onChange={handleChange}
                placeholder="Template Name"
                required
                className="w-full border px-3 py-2 rounded"
              />
              <select
                name="category"
                value={form.category}
                onChange={handleChange}
                className="w-full border px-3 py-2 rounded"
              >
                <option value="INTERVIEW_INVITATION">Interview Invitation</option>
                <option value="REJECTION">Rejection</option>
                <option value="SHORTLIST_CONFIRMATION">Shortlist Confirmation</option>
                <option value="OFFER_LETTER">Offer Letter</option>
                <option value="GENERAL_COMMUNICATION">General Communication</option>
              </select>
              <input
                name="subject"
                value={form.subject}
                onChange={handleChange}
                placeholder="Subject"
                required
                className="w-full border px-3 py-2 rounded"
              />
              <textarea
                name="emailBody"
                value={form.emailBody}
                onChange={handleChange}
                placeholder="Email Body"
                required
                rows={6}
                className="w-full border px-3 py-2 rounded resize-none"
              />
              <div className="flex justify-center">
                <button type="submit" className="px-4 py-2 bg-green-600 text-white rounded">
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
