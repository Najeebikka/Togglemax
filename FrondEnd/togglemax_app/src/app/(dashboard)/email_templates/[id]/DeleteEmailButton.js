"use client";

export default function DeleteEmailButton({ id }) {
  const baseURL = process.env.INTERNAL_API_URL || process.env.NEXT_PUBLIC_API_URL;
  const handleDelete = async () => {
    if (!confirm("Are you sure you want to delete this email template?")) return;

    try {
      const res = await fetch(`${baseURL}/api/email-templates/${id}`, {
        method: "DELETE",
      });

      if (res.ok) {
        alert("Deleted successfully");
        window.location.href = "/email_templates/";
      } else {
        const error = await res.text();
        alert("Delete failed: " + error);
      }
    } catch (err) {
      alert("Error: " + err.message);
    }
  };

  return (
    <span
      onClick={handleDelete}
      className="cursor-pointer select-none"
      title="Delete email template"
    >
      🗑️
    </span>
  );
}
