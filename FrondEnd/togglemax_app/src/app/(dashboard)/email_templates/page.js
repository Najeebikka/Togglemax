"use client";

import { useEffect, useState } from "react";
import { QuickActionBox } from "../dashboard/page";
import EmailTemplatePopup from "./popup";

export default function EmailPage() {
  const [emailTemplates, setEmailTemplates] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchEmailTemplates = async () => {
    setLoading(true);
    try {
      const res = await fetch("http://localhost:8080/api/email-templates", { cache: "no-store" });
      const data = await res.json();
      setEmailTemplates(data);
    } catch (err) {
      console.error("Failed to fetch email templates:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEmailTemplates();
  }, []);

  return (
    <div className="font-[var(--font-geist-sans)] p-4">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-semibold">Email Templates</h1>
        <EmailTemplatePopup refetchTemplates={fetchEmailTemplates} />
      </div>

      {loading ? (
        <p>Loading email templates...</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
          {emailTemplates.map((template) => (
            <QuickActionBox
              key={template.id}
              icon=""
              label={template.templateName}
              disc={template.category}
              link={`email_templates/${template.id}`}
            />
          ))}
        </div>
      )}
    </div>
  );
}
