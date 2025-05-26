# 👁️ Be My Eyes – Android App for the Visually Impaired

[![Android](https://img.shields.io/badge/platform-android-green.svg)](https://developer.android.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

> A real-time object detection and voice-assistance Android application developed to empower blind and visually impaired individuals.

---

## 📱 Overview

**Be My Eyes** is an assistive Android application designed to help visually impaired users interact with their environment using real-time video processing and voice commands. By integrating AI models and voice interfaces, the app offers object detection, disease prediction, and basic smartphone utilities—accessible via simple voice commands.

---

## 🎯 Key Features

- 🔍 **Real-Time Object Detection** using YOLOv3-tiny
- 🧠 **AI-based Disease Prediction**
- 🗣️ **Voice Command Capabilities**
  - Check battery status
  - Show date and time
  - Send and receive SMS
  - Make calls
- 🔊 **Text-to-Speech Conversion**
- 🎮 **Simple, Accessible UI for Blind Users**

---

## 🏗️ System Architecture

```mermaid
graph TD;
    App-->Camera
    App-->VoiceCommandHandler
    App-->YOLOv3TinyAlgorithm
    App-->DiseasePredictionSystem
    VoiceCommandHandler-->BatteryStatus
    VoiceCommandHandler-->SMS
    VoiceCommandHandler-->Call
    VoiceCommandHandler-->DiseasePrediction
    YOLOv3TinyAlgorithm-->RealTimeObjectDetection

