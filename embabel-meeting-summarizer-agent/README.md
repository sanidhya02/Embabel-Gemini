# 🤖 Embabel REST API Based Ai Agent: Meeting Summarizer Agent

This repository demonstrates how to build intelligent REST API Based Agent using the Embabel Framework and Spring Boot. The application exposes a goal-driven AI agent that can summarize meeting transcripts, extract key discussion points, and identify actionable items, all orchestrated automatically by a sophisticated GOAP-based planner.

📖 **Complete Guide**: For detailed explanations and a full code walkthrough, read our comprehensive tutorial.<br>
👉 [**Embabel Framework: Build a REST API Based AI Agent**](https://bootcamptoprod.com/embabel-framework-rest-api-agent/)

🎥 **Video Tutorial**: Prefer hands-on learning? Watch our step-by-step implementation guide.<br>
👉 YouTube Tutorial - [**Build REST API Based AI Agents using Embabel Framework 🚀**](https://youtu.be/CZCLuGRL6pg)

<p align="center">
  <a href="https://youtu.be/CZCLuGRL6pg">
    <img src="https://img.youtube.com/vi/CZCLuGRL6pg/0.jpg" alt="Build REST API Based AI Agents using Embabel Framework" />
  </a>
</p>

<p align="center">
  ▶️ <a href="https://youtu.be/CZCLuGRL6pg">Watch on YouTube</a>
</p>

---

## ✨ What This Project Demonstrates

This application showcases a **production-ready intelligent agent system** with:

- **A REST API Endpoint** for easy integration with any web or mobile application.
- **Automatic Meeting Summarization** using AI-powered analysis.
- **Key Points Extraction** highlighting main discussion topics and decisions.
- **Action Items Identification** with responsible persons and deadlines.
- **GOAP-based Planning** that automatically orchestrates the multi-step summarization workflow.
- **Programmatic Agent Invocation** using Embabel's AgentPlatform and AgentInvocation builder.

---

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java 21** or higher
- **Google Gemini API Key** (free tier available at [Google AI Studio](https://aistudio.google.com/))

---

## 🚀 Quick Start

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/BootcampToProd/embabel-meeting-summarizer-agent.git
cd embabel-meeting-summarizer-agent
```

### 2️⃣ Configure API Key
Provide your Google Gemini API key as an environment variable. You can set this in your IDE's run configuration or directly in your terminal.
```bash
GOOGLE_GEMINI_API_KEY={YOUR_GOOGLE_GEMINI_API_KEY}
```

### 3️⃣ Build the Project
```bash
mvn clean install
```

### 4️⃣ Run the Application
```bash
mvn spring-boot:run
```
The application will start on http://localhost:8080.

---

## 💡 Usage Example

Once the application is running, you can interact with the agent by sending a POST request to the API endpoint using any API client like curl or Postman.

### 📝 cURL REquest
```
curl --location 'http://localhost:8080/api/v1/meeting/summarize' \
--header 'Content-Type: application/json' \
--data '{
    "transcript": "Hi everyone, thanks for joining today. Let’s start with the authentication module, Raj, how’s the backend going? Yeah, the login and registration endpoints are complete, the password reset flow is still pending but I should have it done by Wednesday. Okay, that’s good to hear. Meera, what about the frontend side? The login screen is ready and I’ll integrate Raj’s API tomorrow, but I still need the finalized error messages from QA for the registration part. Alright, I’ll share that document today so you can move forward. Great, thanks. Any blockers from QA? The only issue right now is we don’t have test data for multiple failed login attempts. I can generate a seed script for that before the end of the month. Perfect, that should help. Now for the sprint review next week, can we plan to demo the complete login and registration flow? Yes, that should be fine as long as the APIs are stable. I’ll run regression testing on Friday and update everyone if I find issues. Sounds good, let’s close here then, thanks everyone for the updates."
}'
```

### 📊 Example Output
```
{
    "keyPoints": [
        "Backend: Login/registration endpoints complete, password reset pending (Raj).",
        "Frontend: Login screen ready, registration integration blocked by error messages (Meera).",
        "QA: Awaiting test data for multiple failed login attempts.",
        "Sprint Review: Plan to demo complete login/registration flow.",
        "Regression testing to be performed on Friday."
    ],
    "actionItems": [
        "Raj: Complete password reset flow by Wednesday.",
        "Meera: Integrate Raj’s API tomorrow.",
        "Meera: Finalize registration part after receiving error messages from QA.",
        "QA: Share error messages document today.",
        "QA: Generate seed script for multiple failed login attempts before end of month.",
        "QA: Run regression testing on Friday and update everyone if issues found."
    ]
}
```