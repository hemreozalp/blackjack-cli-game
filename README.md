# Blackjack CLI — Spring Boot (Java 17)

A clean, SOLID-oriented command-line Blackjack game built with **Spring Boot** and **Java 21**.
Implements core object-oriented design principles, dependency injection, and domain-driven structure while maintaining a lightweight, testable architecture.

---

##  Architecture Overview

```
src/
├── main/
│   ├── java/com/hemreozalp/blackjack/
│   │   ├── domain/
│   │   │   ├── model/          # Core entities: Card, Deck, Hand, Player, Dealer, Wallet
│   │   │   ├── service/        # GameService, InputService, OutputService
│   │   │   └── strategy/       # Strategy interfaces and implementations (Scoring, Shuffling)
│   │   └── BlackjackApplication.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/com/hemreozalp/blackjack/
        ├── domain/model/       
        ├── domain/service/     
        └── domain/strategy/    
```

---

##  Key Features

* **SOLID & Clean Architecture**

    * Domain isolation: no direct console or framework dependencies in core logic.
    * Decoupled layers with explicit responsibilities.
* **Design Patterns Used**

    * **Strategy:** for score calculation and deck shuffling.
    * **Dependency Injection (IoC):** managed via Spring `@Bean` configuration.
    * **Template Method (implicit):** structured round flow in `GameService`.

---

##  Technical Stack

| Layer        | Technology                                                 |
| ------------ |------------------------------------------------------------|
| Runtime      | Java 17                                                    |
| Framework    | Spring Boot 3.x                                            |
| Build Tool   | Maven 3.9+                                                 |
| Testing      | JUnit 5, Mockito                                           |
| Architecture | Domain-Driven Design (tactical), SOLID, Clean Architecture |

---

##  Design Philosophy

> *"Keep the domain pure and the dependencies inverted."*

This project emphasizes:

* Small, single-purpose classes.
* Extensible domain logic through interfaces.
* Deterministic, mockable service flows.
* Testability over framework coupling.

---

##  Gameplay Flow

1. Player starts with **₿1000 balance**.
2. A new deck is shuffled each round.
3. Player places a bet → cards are dealt.
4. Turn sequence:

    * Player chooses: `h` (hit) or `s` (stand).
    * Dealer draws until 17 or higher.
5. Result is evaluated:

    * Win → 2× payout
    * Draw → bet returned
    * Lose → bet deducted
6. Player can choose to play again (`y/n`).

---

## 🚀 Build & Run

### Prerequisites

* Java 17+
* Maven 3.9+

### Build

```bash
mvn clean package
```

### Run

```bash
java -jar target/blackjack-0.0.1-SNAPSHOT.jar
```

### Test

```bash
mvn test
```

---

## Example Session

```
Your current balance: 1000
Enter your bet amount:
100
Dealer's visible card: SEVEN of DIAMONDS
You -> [TEN of HEARTS, FIVE of CLUBS] (Value: 15)
Hit or Stand? (h/s):
h
You -> [TEN of HEARTS, FIVE of CLUBS, KING of HEARTS] (Value: 25)
You busted!
Dealer wins.
Your balance: 900
Play again? (y/n):
```

---

## 🧩 Future Enhancements

Planned or potential future improvements include:

###  Gameplay

* Multi-player mode (local or network)
* Split, Double Down, and Insurance mechanics
* Configurable minimum/maximum bet limits
* Game statistics and round history tracking

###  Persistence & Infrastructure

* Database integration (JPA Repository or in-memory H2)
* Player profile persistence (balance, win/loss history)
* Leaderboard and scoring persistence

###  Architecture

* Full **State Pattern** for `PlayerTurn`, `DealerTurn`, `GameOverState`
* Event-driven design (`GameEvent`, `PlayerBustedEvent`)
* REST API or GUI front-end using Spring Web or JavaFX

###  Testing & Tooling

* Integration tests for I/O adapters
* Coverage reports via JaCoCo
* CI/CD pipeline configuration (GitHub Actions)

###  Deployment

* Dockerfile for containerized execution
* Heroku / Fly.io CLI deployment

---

## 📘 Commit Convention

This project follows [Conventional Commits](https://www.conventionalcommits.org/):

```
feat(gameplay): add Wallet system and continuous play loop
fix: adjust ace value when hand exceeds 21
test: add unit tests for domain and game logic
```

---


