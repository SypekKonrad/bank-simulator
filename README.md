# Bank Simulator (Java Desktop Application)

A simple desktop banking simulator written in Java using Swing.  
---

### Requirements
- Java 8 or newer (JRE or JDK)
- Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or terminal


## How to Run the Project
1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/bank-simulator.git
   cd bank-simulator

 2. **Launching the app:**
    - Go to IDE and run Main.java

## Project Assumptions

- Each user has exactly one bank account assigned to their login.
- The login is unique and serves as the main identifier.
- Users provide personal data during registration (e.g. name, surname, PESEL) without external verification.
- The app supports only single sessions per device.
- All operations (e.g. transfers, loans, repayments) are atomic — interruptions are not handled.
- Data is stored locally using file serialization (`.dat` files).
- No integration with external banking APIs, currency exchange services, or other online tools.
- GUI is built using Java Swing, optimized for standard screen resolutions (non-responsive).
- The user must have a compatible Java environment (JRE/JDK) installed.
- All financial values are stored as `double`, with no currency formatting or financial rounding rules.

---

## Project Limitations

- **Local-only desktop app**  
  No internet connectivity or synchronization with real banking services.

- **Unencrypted data**  
  User data and transaction history are stored in plain `.dat` files without encryption.

- **No 2FA (Two-Factor Authentication)**  
  Login is secured only with a username and password stored locally.

- **Basic Swing UI**  
  The interface uses standard Swing components and lacks modern design or responsive layout.

- **Simplified navigation**  
  The app uses `CardLayout` for switching views; no advanced navigation logic is implemented.

- **Simplified loan logic**
  - No repayment schedule, deadlines, or interest rates.
  - Multiple loans can be taken regardless of outstanding balances.

- **Minimal transfer system**
  - Recipient identification is based only on account number.
  - No identity validation beyond that.

- **Weak input validation**
  - No format checking for emails or passwords.
  - Duplicate email addresses are not blocked.

- **No activity logging**  
  Only balance changes are recorded — there's no detailed history of user actions.

- **One account per user**  
  Each user can create and manage only a single account.

- **English-only interface**  
  The app supports only the English language; no localization options are provided.

- **No automated testing**  
  There are no unit tests or automated validation of the application logic.


