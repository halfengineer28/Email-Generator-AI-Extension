# 📧 Email Generator AI Extension

A Chrome browser extension that injects an **AI Reply** button into Gmail's compose toolbar, letting you generate smart email replies with one click — powered by AI.

---

## ✨ Features

- Adds an **AI Reply** button directly inside Gmail's compose window
- Detects the email you're replying to and reads its content automatically
- Generates a professional reply and inserts it into the compose box
- Works seamlessly with Gmail's UI using MutationObserver

---

## 🗂️ Project Structure

```
Email-Generator-AI-Extension/
├── content.js          # Main extension script (injects button, handles click)
├── manifest.json       # Chrome extension manifest
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

- Google Chrome browser
- A running backend at `http://localhost:8080` that exposes:
  ```
  POST /api/email/generate
  ```
  **Request body:**
  ```json
  {
    "emailContent": "string",
    "tone": "professional"
  }
  ```
  **Response:** plain text string (the generated reply)

### Installation

1. **Clone the repo**
   ```bash
   git clone https://github.com/halfengineer28/Email-Generator-AI-Extension.git
   cd Email-Generator-AI-Extension
   ```

2. **Load the extension in Chrome**
   - Open Chrome and go to `chrome://extensions/`
   - Enable **Developer mode** (toggle in the top right)
   - Click **Load unpacked**
   - Select the project folder

3. **Start your backend server** on port `8080`

4. **Open Gmail** — open any email and click **Reply**. You'll see the **AI Reply** button in the toolbar.

---

## 🛠️ How It Works

1. A `MutationObserver` watches for Gmail's compose window to appear
2. When detected, it injects an **AI Reply** button into the toolbar
3. On click, it reads the email thread content from the DOM
4. Sends it to the backend API with a `professional` tone
5. Inserts the generated reply directly into the compose box

---

## ⚙️ Configuration

To change the tone or API endpoint, edit `content.js`:

```js
// Change tone
tone: "professional"  // options: professional | casual | friendly

// Change API endpoint
const response = await fetch("http://localhost:8080/api/email/generate", ...);
```

---

## 🔒 Security Notes

- **Never commit `.env` files or API keys** to this repo
- The extension communicates with a local server only (`localhost`) — no data is sent to third parties
- Add a `.gitignore` to exclude sensitive files:
  ```
  .env
  node_modules/
  ```

---

## 🐛 Known Limitations

- Gmail's DOM selectors (`.btC`, `.aDh`, `.h7`, etc.) may change with Gmail updates — if the button stops appearing, the selectors may need updating
- `document.execCommand("insertText")` is deprecated but still widely supported in Chromium

---

## 🤝 Contributing

Pull requests are welcome! Please open an issue first to discuss what you'd like to change.

---

## 📄 License

MIT
