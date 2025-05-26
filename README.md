📱 Be My Eyes – Android App for the Visually Impaired
👁️‍🗨️ Overview
Be My Eyes is an Android application developed to assist visually impaired individuals by leveraging voice commands, real-time video processing, and AI-based object recognition. This app aims to enhance their independence and safety in daily life through features like object detection, voice-based interaction, disease prediction, and essential smartphone utilities (e.g., calls, SMS, battery status).

🧠 Motivation
Millions of people face vision impairment, making everyday tasks challenging. Inspired by this reality, our app bridges the gap using mobile vision and speech recognition technologies—offering sight and support through smart devices.

🛠️ Features
🎥 Real-time Object Detection using YOLOv3-tiny

🎙️ Voice Commands for:

Checking battery level

Getting date/time

Sending/receiving SMS

Making calls

Predicting diseases

🧠 Disease Prediction using symptom input and a HashSet dataset

🔊 Text-to-Speech audio output for all interactions

🧩 Simple UI designed for the visually impaired

📐 System Architecture
On app launch, the camera starts capturing video.

Pressing the up button triggers object detection.

Pressing the down button enables voice command interaction.

Outputs are provided as audio cues.

🧱 Class Diagram (Key Components)
BeMyEyesApplication: App entry point and core controller

YOLOv3TinyAlgorithm: Handles object recognition

VoiceCommandHandler: Manages speech-based commands

DiseasePredictionSystem: Predicts diseases from user symptoms

🧰 Tools & Technologies
Android Studio

Backend: Kotlin

Frontend: XML

Libraries: YOLOv3-tiny, Text-to-Speech, Voice Recognition

📚 Literature References
Object Recognition App for Visually Impaired People – Nasreen et al.

Distributed Multi-image Feature Matching via QuickMatch – Serlin et al.

AI in Disease Diagnosis – Yogesh Kumar et al.

Be My Eyes Survey – Mauro Avila et al.


🎯 Conclusion
The Be My Eyes app is a step toward empowering the blind community by enabling them to interpret their surroundings through voice-guided AI technology. Its intuitive design and assistive features aim to enhance mobility, safety, and autonomy.
