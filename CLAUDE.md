# Pinballz Application - Build & Deployment Guide

Quick reference for building and deploying the MyBeautifulApp Spring Boot + Angular application on Ubuntu with systemd.

---

## Prerequisites

Ensure the following are installed:

```bash
# Check Java (requires Java 21)
java -version

# Check Maven (requires 3.6+)
mvn -version

# Check Node.js and npm
node -v
npm -v
```

**Expected versions:**
- Java: OpenJDK 21 (via SDKMAN)
- Maven: 3.6.3+
- Node.js: v20+
- npm: 10+

---

## Build Process

Navigate to the project directory and build:

```bash
cd /home/pin/pinballz
mvn clean package
```

**Build Process:**
1. Compiles backend Java code
2. Runs `npm install` and `ng build` for Angular frontend
3. Packages everything into a single executable JAR

**Build Output:**
```
delivery/target/MyBeautifulApp-0.0.2-SNAPSHOT.jar (~23 MB)
```

**Build Time:** Approximately 30-60 seconds

---

## Deployment Steps

### 1. Stop the Running Service

```bash
sudo systemctl stop myapp.service
```

### 2. Verify New JAR Exists

```bash
ls -lh /home/pin/pinballz/delivery/target/MyBeautifulApp-0.0.2-SNAPSHOT.jar
```

You should see a file approximately 23 MB in size with a current timestamp.

### 3. Start the Service

```bash
sudo systemctl start myapp.service
```

### 4. Verify Deployment

```bash
sudo systemctl status myapp.service
```

Expected output should show:
- `Active: active (running)`
- Recent log entries showing successful startup

---

## Verification

### Check Application Logs

View real-time logs:

```bash
journalctl -u myapp.service -f
```

Press `Ctrl+C` to exit log viewer.

View recent logs:

```bash
journalctl -u myapp.service -n 50
```

### Access Application

Open browser or use curl:

```bash
curl http://192.168.1.104:8080/
```

**Application URL:** http://192.168.1.104:8080

---

## Service Management

### Check Service Status

```bash
sudo systemctl status myapp.service
```

### Restart Service

```bash
sudo systemctl restart myapp.service
```

### Enable Service (auto-start on boot)

```bash
sudo systemctl enable myapp.service
```

### Disable Service (prevent auto-start)

```bash
sudo systemctl disable myapp.service
```

---

## Quick Troubleshooting

### Service Won't Start

Check logs for errors:

```bash
journalctl -u myapp.service -n 100 --no-pager
```

Common issues:
- Port 8080 already in use
- JAR file missing or corrupted
- Java not found in PATH

### Build Failures

**Frontend build fails:**
- Ensure Node.js and npm are installed
- Check `frontend/package.json` exists
- Try: `cd frontend && npm install && npm run build`

**Maven build fails:**
- Ensure Java 21 is active: `java -version`
- Check all `pom.xml` files are valid
- Try: `mvn clean` then `mvn package`

### Check JAR File Integrity

```bash
jar -tf /home/pin/pinballz/delivery/target/MyBeautifulApp-0.0.2-SNAPSHOT.jar | head -20
```

Should show Spring Boot structure with `BOOT-INF/`, `META-INF/`, etc.

---

## System Information

**Service Name:** myapp.service
**Service File:** /etc/systemd/system/myapp.service
**Startup Script:** /home/pin/run-kiosk.sh
**JAR Location:** /home/pin/pinballz/delivery/target/MyBeautifulApp-0.0.2-SNAPSHOT.jar
**Server Address:** 192.168.1.104:8080
**Java Path:** /home/pin/.sdkman/candidates/java/current/bin/java

---

## Configuring Passwordless Sudo (Optional)

To allow automated deployments and service restarts without password prompts (useful for CI/CD or tools like Claude Code), configure passwordless sudo for systemd service management.

### Steps:

1. **Edit sudoers file safely:**

```bash
sudo visudo
```

2. **Add these lines at the end of the file:**

```
# Allow pin user to manage myapp.service without password
pin ALL=(ALL) NOPASSWD: /bin/systemctl start myapp.service
pin ALL=(ALL) NOPASSWD: /bin/systemctl stop myapp.service
pin ALL=(ALL) NOPASSWD: /bin/systemctl restart myapp.service
pin ALL=(ALL) NOPASSWD: /bin/systemctl status myapp.service
```

3. **Save and exit** (in nano: `Ctrl+O`, `Enter`, `Ctrl+X`; in vi: `Esc`, `:wq`, `Enter`)

4. **Test the configuration:**

```bash
sudo systemctl status myapp.service
```

Should not prompt for a password.

**Security Note:** This configuration grants passwordless access only to specific systemctl commands for myapp.service. It does not grant broader sudo privileges.

---

## Complete Redeployment Workflow

```bash
# Navigate to project
cd /home/pin/pinballz

# Build new version
mvn clean package

# Stop service
sudo systemctl stop myapp.service

# Verify new JAR
ls -lh delivery/target/MyBeautifulApp-0.0.2-SNAPSHOT.jar

# Start service
sudo systemctl start myapp.service

# Check status
sudo systemctl status myapp.service

# View logs (optional)
journalctl -u myapp.service -f
```

---

**Last Updated:** 2025-11-06
