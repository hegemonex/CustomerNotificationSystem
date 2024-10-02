// register.js

// Handle registration
const registerForm = document.getElementById('register-form');
if (registerForm) {
    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = document.getElementById('register-username').value;
        const password = document.getElementById('register-password').value;

        try {
            const response = await fetch('/api/users/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                alert('Registration successful');
                window.location.href = 'index.html'; // Redirect to login page after successful registration
            } else {
                alert('Registration failed');
            }
        } catch (error) {
            alert('Registration error');
        }
    });
}