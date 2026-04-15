const eventForm = document.getElementById("event-form");
const participantForm = document.getElementById("participant-form");
const registrationForm = document.getElementById("registration-form");
const eventsBody = document.getElementById("events-body");
const message = document.getElementById("message");
const refreshButton = document.getElementById("refresh-events");

async function request(url, options = {}) {
    const response = await fetch(url, {
        headers: { "Content-Type": "application/json" },
        ...options
    });

    if (!response.ok) {
        let error = "Request failed";
        try {
            const body = await response.json();
            error = body.error || error;
        } catch (_) {
        }
        throw new Error(error);
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}

function showMessage(text, isError = true) {
    message.textContent = text;
    message.style.color = isError ? "#b91c1c" : "#0f766e";
}

async function loadEvents() {
    const events = await request("/api/events");
    eventsBody.innerHTML = "";

    events.forEach(event => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${event.id}</td>
            <td>${event.name}</td>
            <td>${event.location}</td>
            <td>${event.date}</td>
            <td>${event.maxParticipants}</td>
        `;
        eventsBody.appendChild(row);
    });
}

eventForm.addEventListener("submit", async event => {
    event.preventDefault();
    const formData = new FormData(eventForm);
    const payload = Object.fromEntries(formData.entries());
    payload.id = Number(payload.id);
    payload.maxParticipants = Number(payload.maxParticipants);

    try {
        await request("/api/events", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        eventForm.reset();
        showMessage("Event created", false);
        await loadEvents();
    } catch (error) {
        showMessage(error.message);
    }
});

participantForm.addEventListener("submit", async event => {
    event.preventDefault();
    const formData = new FormData(participantForm);
    const payload = Object.fromEntries(formData.entries());
    payload.id = Number(payload.id);

    try {
        await request("/api/participants", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        participantForm.reset();
        showMessage("Participant created", false);
    } catch (error) {
        showMessage(error.message);
    }
});

registrationForm.addEventListener("submit", async event => {
    event.preventDefault();
    const formData = new FormData(registrationForm);
    const payload = Object.fromEntries(formData.entries());
    payload.eventId = Number(payload.eventId);
    payload.participantId = Number(payload.participantId);

    try {
        await request("/api/registrations", {
            method: "POST",
            body: JSON.stringify(payload)
        });
        registrationForm.reset();
        showMessage("Registration created", false);
    } catch (error) {
        showMessage(error.message);
    }
});

refreshButton.addEventListener("click", async () => {
    try {
        await loadEvents();
        showMessage("Events refreshed", false);
    } catch (error) {
        showMessage(error.message);
    }
});

loadEvents().catch(error => showMessage(error.message));
