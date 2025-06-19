import DeleteEmailButton from './DeleteEmailButton';

export default async function EmailTemplatePage({ params }) {
  const { id } = params;
  const baseURL = process.env.NEXT_PUBLIC_API_URL;

  const res = await fetch(`${baseURL}/api/email-templates/${id}`);

  if (!res.ok) {
    return <div className="p-4 text-red-500">Email template not found.</div>;
  }

  const email = await res.json();

  return (
    <div className="p-8 font-sans bg-gray-50 min-h-screen">
      <h1 className="text-3xl font-bold mb-8 flex justify-between">
        <div>
          <a href="/email_templates/" className="text-blue-600 hover:underline">
            ←
          </a>{" "}
          {email.templateName}
        </div>
        <DeleteEmailButton id={id} />
      </h1>

      <h1 className="text-2xl font-bold mb-6">subject : {email.subject}</h1>

      <div className="whitespace-pre-line text-gray-800 leading-relaxed">
        {email.emailBody}
      </div>
    </div>
  );
}
