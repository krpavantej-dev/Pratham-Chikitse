# Product Requirements Document (PRD)
## Pratham-Chikitse (Emergency First Aid Guide)

### 1. Product Overview
**Pratham-Chikitse** is an offline-first Android application designed to provide immediate, easy-to-understand, and life-saving first-aid instructions during medical emergencies. In critical situations where internet access might be unavailable or slow, this app serves as a reliable pocket guide for immediate action before professional medical help arrives.

### 2. Target Audience
*   **General Public:** People with no formal medical training who find themselves in an emergency situation.
*   **Parents & Caregivers:** Individuals responsible for the safety of children or the elderly.
*   **Travelers/Hikers:** People in remote areas where internet connectivity is poor or non-existent.

### 3. Core Problems Solved
*   **Lack of Immediate Knowledge:** People often panic and don't know what to do during medical emergencies.
*   **Internet Dependency:** Most medical information requires a web search, which is impossible in dead zones or during network outages.
*   **Accessibility under Stress:** Reading long paragraphs of text is difficult during a high-stress emergency.

### 4. Key Features & Requirements

#### 4.1. Offline-First Architecture
*   **Requirement:** The app must function entirely without an active internet connection.
*   **Implementation:** All text data, UI components, and the simulated hospital list are bundled locally within the app's `.apk`.

#### 4.2. Emergency Category Selection (Main Screen)
*   **Requirement:** Users must be able to find the relevant emergency within 2-3 seconds.
*   **Implementation:** 
    *   A grid layout displaying 20 distinct emergency cases (e.g., Snake Bite, Heart Attack, Burn, Choking, etc.).
    *   Large, easily identifiable emojis/icons.
    *   Color-coded gradient tiles for high visibility.
    *   A prominent, pulsing "SOS / Call Ambulance (108)" button for immediate dialing.

#### 4.3. Step-by-Step Instruction Guide (Detail Screen)
*   **Requirement:** Instructions must be broken down into digestible, sequential steps.
*   **Implementation:**
    *   Horizontal swipeable pager for navigating through steps (Step 1, Step 2, Step 3).
    *   Clear "Do's" (Green) and "Don'ts" (Red) sections for each step.
    *   Segmented progress bar indicating current step position.

#### 4.4. Audio Assistance (Text-To-Speech)
*   **Requirement:** Hands-free guidance for users who are actively administering first aid and cannot look at the screen.
*   **Implementation:**
    *   A prominent "Start Audio" button using Android's native Text-To-Speech (TTS) engine.
    *   Automatically reads the current step's instructions aloud.

#### 4.5. Hospital Locator (Simulated Offline)
*   **Requirement:** Quick access to nearby medical centers without requiring Google Maps to load.
*   **Implementation:**
    *   A curated, offline list of major hospitals (simulated for the demo).
    *   Ranked cards showing the "Nearest" hospital.
    *   One-tap "Call Now" button that triggers the phone's native dialer (`ACTION_DIAL`).

### 5. Technical Specifications
*   **Platform:** Android (Minimum SDK 24, Target SDK 36)
*   **Language:** Kotlin
*   **UI Framework:** Jetpack Compose (Declarative UI)
*   **Design System:** Material Design 3 (Material3)
*   **Navigation:** Jetpack Navigation Compose
*   **Local APIs:** Android TTS (Text-To-Speech), Android Intent System (Telephony)

### 6. Future Scope (V2 Ideas)
*   **GPS Integration:** Fetch real-time nearest hospitals using Location Services and Google Places API (when online).
*   **Multilingual Support:** Allow users to switch the app language (e.g., Kannada, Hindi, English).
*   **Emergency Contact Alerts:** An SOS button that automatically sends an SMS with the user's GPS coordinates to predefined emergency contacts.
*   **Video Demonstrations:** Short, compressed looping animations (GIFs/Lottie) showing CPR or Heimlich maneuver techniques.

### 7. Success Metrics
*   **Load Time:** App must open and be interactive in under 1 second.
*   **Time to Action:** User should be able to reach a specific emergency instruction page in under 3 taps.
*   **Crash Free Rate:** Must maintain a >99% crash-free session rate given the critical nature of the app.
