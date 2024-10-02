// login.js

// Handle login
const loginForm = document.getElementById('login-form');
if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = document.getElementById('login-username').value;
        const password = document.getElementById('login-password').value;

        const auth = btoa(`${username}:${password}`);
        try {
            const response = await fetch('/api/users/login', {
                headers: {
                    'Authorization': `Basic ${auth}`
                }
            });

            if (response.ok) {
                localStorage.setItem('auth', auth);
                window.location.href = 'dashboard.html'; // Redirect to dashboard after successful login
            } else {
                alert('Login failed');
            }
        } catch (error) {
            alert('Login error');
        }
    });
}