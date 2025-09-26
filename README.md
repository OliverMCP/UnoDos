
# **UnoDos**
#### (Inspired by the UNO Card Game)

|![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white) |![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)|![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)|![Vite](https://img.shields.io/badge/Vite-646CFF?style=for-the-badge&logo=vite&logoColor=white)|![TailwindCSS](https://img.shields.io/badge/TailwindCSS-38B2AC?style=for-the-badge&logo=tailwindcss&logoColor=white)|![Framer Motion](https://img.shields.io/badge/Framer%20Motion-0055FF?style=for-the-badge&logo=framer&logoColor=white)|
|--|--|--|--|--|--|

---

"UnoDos" is a **UNO-inspired web app** with some custom rules.  
The backend is built with **Java (Spring Boot)**, while the frontend uses **React + Vite + Tailwind**.  

I originally wanted to create it as a monolith desktop app with plain Java and Raylib. But switching to a web app made it not only easier to build (no need to reinvent a whole UI system), but also accessible to everyone – since many of my friends and family don’t even use a PC or laptop regularly.

---

## Features
- **Custom UNO rules** (stacking, counter plays, UnoDos call, 2-player Reverse behavior, etc.)
- **Spring Boot REST API** for all game logic and state handling
- **React + Tailwind frontend** with animations (Framer Motion)
- Responsive UI/UX, optimized for desktop & mobile
- Penalty & bluff mechanics (choose not to play, save +4 cards, etc.)

---

## Tech Stack
- **Backend**: Java, Spring Boot  
- **Frontend**: React, Vite, Tailwind, Framer Motion  
- **Other**: Git, REST APIs, Clean Code principles  

---

## What I Learned
This project helped me practice and improve in multiple areas:
- **Java OOP & design patterns**
- Implementing a clean and reusable **game logic system**
- Understanding how games & engines work (game loop, deltaTime, input management)
- **Backend development** with Spring Boot & REST APIs
- **Frontend development** with React, Tailwind, animations, and routing
- Using **Git** and maintaining a clean repo structure  

---

## Future Plans
- Online multiplayer functionality  
- Account system with profiles, stats & settings  
- Smarter AI (TensorFlow + Docker)  
- Containerization with Docker  

---

## Screenshots & Preview

Example:  

![Game Screenshot](./screenshots/game.png)  
![Demo GIF](./screenshots/demo.gif)  

---

## How to Run

### Backend
```bash
cd backend
./mvnw spring-boot:run
```
### Frontend

```bash
cd frontend
npm install
npm run dev
```
