# 📝 Blog Title Generator with Embabel Framework

A Spring Boot REST API application powered by the Embabel framework that generates creative and engaging blog titles from meeting transcripts using Google Gemini AI. This intelligent agent uses GOAP (Goal-Oriented Action Planning) to automatically analyze meeting content and produce catchy titles perfect for blog posts.

## 🌟 Features

- **AI-Powered Title Generation**: Leverages Google Gemini's advanced language models to create compelling blog titles
- **REST API Interface**: Easy integration with any web or mobile application
- **Goal-Driven Agent**: Uses Embabel's GOAP planning for intelligent task execution
- **Flexible Input**: Accepts meeting transcripts and generates contextually relevant titles
- **Multiple Title Options**: Returns several title variations to choose from

## 🏗️ Architecture

The application uses the Embabel framework's agent-based architecture:

```
Meeting Transcript (Input)
         ↓
  Embabel Agent Platform
         ↓
  GOAP Planner (Determines Action Sequence)
         ↓
  Google Gemini LLM Integration
         ↓
  Generated Blog Titles (Output)
```

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **Google Gemini API Key** - Get yours from [Google AI Studio](https://aistudio.google.com/app/apikey)

## 🚀 Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/sanidhya02/Embabel-Gemini.git
cd Embabel-Gemini/embabel-meeting-summarizer-agent
```

### Step 2: Set Up Environment Variables

Set your Google Gemini API key as an environment variable:

**Linux/macOS:**
```bash
export GOOGLE_GEMINI_API_KEY=your_api_key_here
```

**Windows (Command Prompt):**
```cmd
set GOOGLE_GEMINI_API_KEY=your_api_key_here
```

**Windows (PowerShell):**
```powershell
$env:GOOGLE_GEMINI_API_KEY="your_api_key_here"
```

### Step 3: Build the Project

```bash
mvn clean install
```

### Step 4: Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Usage

### Generate Blog Title Endpoint

**Endpoint:** `POST /api/v1/meeting/blog-title`

**Content-Type:** `application/json`

### Request Format

```json
{
  "transcript": "Your meeting transcript here..."
}
```

### cURL Examples

#### Example 1: Tech Product Launch Meeting

```bash
curl --location 'http://localhost:8080/api/v1/meeting/blog-title' \
--header 'Content-Type: application/json' \
--data '{
  "transcript": "Hi everyone, thanks for joining. Today we are launching our new AI-powered analytics dashboard. The key features include real-time data visualization, predictive insights using machine learning, and seamless integration with popular data sources. Our target audience is enterprise customers in the finance and healthcare sectors. The dashboard reduces analysis time by 70% and provides actionable insights within seconds."
}'
```

**Expected Response:**
```json
{
  "titles": [
    "Revolutionizing Data Analytics: Our New AI Dashboard Cuts Analysis Time by 70%",
    "From Data to Insights in Seconds: Introducing Our Enterprise Analytics Platform",
    "The Future of Business Intelligence: ML-Powered Dashboard for Finance and Healthcare"
  ]
}
```

#### Example 2: Marketing Strategy Meeting

```bash
curl --location 'http://localhost:8080/api/v1/meeting/blog-title' \
--header 'Content-Type: application/json' \
--data '{
  "transcript": "Good morning team. We discussed our Q1 marketing campaign focused on social media engagement. The main points were: increasing Instagram reels by 300%, partnering with micro-influencers, and launching a user-generated content challenge. Budget allocation is 40% social media, 30% influencer partnerships, and 30% content creation. Expected reach is 5 million impressions."
}'
```

**Expected Response:**
```json
{
  "titles": [
    "How We Plan to Reach 5 Million Users: Our Q1 Social Media Strategy Revealed",
    "The Power of Micro-Influencers: Revolutionizing Our Marketing Approach",
    "From Zero to Viral: Our 300% Instagram Growth Strategy for Q1"
  ]
}
```

#### Example 3: Product Development Sprint

```bash
curl --location 'http://localhost:8080/api/v1/meeting/blog-title' \
--header 'Content-Type: application/json' \
--data '{
  "transcript": "Sprint planning session for our mobile app redesign. We are implementing a new dark mode, improving load times by 50%, adding biometric authentication, and redesigning the checkout flow to reduce cart abandonment. User testing showed 85% preference for the new UI. We are targeting a release in 6 weeks with beta testing starting in 3 weeks."
}'
```

**Expected Response:**
```json
{
  "titles": [
    "Cutting Cart Abandonment: Inside Our Mobile App Redesign Journey",
    "Speed, Security, and Style: How We're Transforming Our Mobile Experience",
    "From Concept to Launch: Building a Mobile App That Users Actually Love"
  ]
}
```

### Response Structure

```json
{
  "titles": [
    "Primary engaging title",
    "Alternative creative title",
    "Another catchy option"
  ],
  "timestamp": "2025-12-30T10:30:00Z",
  "status": "success"
}
```

## 🔧 Configuration

### application.properties

```properties
# Server Configuration
server.port=8080

# Embabel Configuration
embabel.default-llm=gemini-2.0-flash-exp

# Logging Configuration
logging.level.com.embabel=DEBUG
logging.level.org.springframework.ai=INFO

# Gemini API Configuration (via environment variable)
# GOOGLE_GEMINI_API_KEY=${GOOGLE_GEMINI_API_KEY}
```

### Customizing Title Generation

You can customize the title generation behavior by modifying the agent's action parameters:

```java
@Action
public BlogTitles generateTitles(MeetingTranscript transcript, OperationContext context) {
    String prompt = """
        Generate 5 compelling blog titles based on this meeting transcript:
        %s
        
        Guidelines:
        - Make titles attention-grabbing and click-worthy
        - Include specific numbers or statistics when available
        - Keep titles between 50-70 characters
        - Use power words and emotional triggers
        """.formatted(transcript.getContent());
    
    return context.ai()
        .withLlm(GoogleGeminiModels.GEMINI_2_FLASH)
        .createObject(prompt, BlogTitles.class);
}
```

## 🧪 Testing

### Run Unit Tests

```bash
mvn test
```

### Run Integration Tests

```bash
mvn verify
```

### Quick Manual Test

```bash
curl --location 'http://localhost:8080/api/v1/meeting/blog-title' \
--header 'Content-Type: application/json' \
--data '{
  "transcript": "Quick test meeting about launching a new feature next week."
}'
```

## 📚 Project Structure

```
embabel-meeting-summarizer-agent/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── bootcamptoprod/
│   │   │           ├── agent/
│   │   │           │   └── BlogTitleAgent.java          # Main agent
│   │   │           ├── controller/
│   │   │           │   └── BlogTitleController.java     # REST endpoint
│   │   │           ├── model/
│   │   │           │   ├── MeetingTranscript.java       # Input model
│   │   │           │   └── BlogTitles.java              # Output model
│   │   │           └── config/
│   │   │               └── GeminiConfig.java            # LLM configuration
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## 🐛 Troubleshooting

### Common Issues

**1. API Key Error**
```
Error: GOOGLE_GEMINI_API_KEY not found
```
**Solution:** Ensure your environment variable is set correctly:
```bash
echo $GOOGLE_GEMINI_API_KEY  # Should display your API key
```

**2. Connection Timeout**
```
Error: Connection timeout to Google Gemini API
```
**Solution:** Check your internet connection and verify the API key is valid at [Google AI Studio](https://aistudio.google.com/)

**3. Port Already in Use**
```
Error: Port 8080 is already in use
```
**Solution:** Change the port in `application.properties` or kill the process using port 8080:
```bash
# Find process
lsof -i :8080
# Kill process
kill -9 <PID>
```

## 🔐 Security Best Practices

1. **Never commit API keys** to version control
2. Use environment variables or secure vaults (AWS Secrets Manager, HashiCorp Vault)
3. Implement rate limiting for production deployments
4. Add authentication/authorization to your API endpoints
5. Rotate API keys regularly

## 📈 Performance Optimization

- **Model Selection**: Use `gemini-2.0-flash-exp` for faster responses, `gemini-pro` for higher quality
- **Caching**: Implement response caching for repeated requests
- **Batch Processing**: Consider batch API calls for multiple transcripts
- **Async Processing**: Use asynchronous endpoints for long-running operations

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is part of the Embabel-Gemini repository. Please refer to the main repository for license information.

## 🔗 Related Resources

- [Embabel Framework Documentation](https://docs.embabel.com/)
- [Google Gemini API Documentation](https://ai.google.dev/docs)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [BootcampToProd Tutorial Series](https://bootcamptoprod.com/embabel-framework-guide/)

## 💡 Use Cases

- **Content Marketing**: Generate blog titles from meeting notes
- **Documentation**: Create engaging titles for technical documentation
- **Internal Communications**: Produce catchy titles for company updates
- **Social Media**: Generate post titles from team discussions
- **Product Launches**: Create compelling titles for product announcements

## 📞 Support

For questions or issues:
- Open an issue on GitHub
- Check the [Embabel Framework Guide](https://bootcamptoprod.com/embabel-framework-guide/)
- Visit the [Embabel Community](https://github.com/embabel/embabel-agent)

---

**Built with ❤️ using [Embabel Framework](https://github.com/embabel/embabel-agent) and Google Gemini**
