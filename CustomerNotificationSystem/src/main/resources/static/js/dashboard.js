// dashboard.js

let currentPage = 0;
let pageSize = 10;
const customerTableBody = document.getElementById('customerTableBody');
const queryInput = document.getElementById('customerSearch');
// Fetch customers when the page loads
document.addEventListener('DOMContentLoaded', fetchCustomers);
// Fetch and display customers on the dashboard
async function fetchCustomers() {
    const auth = localStorage.getItem('auth');
    const query = queryInput ? queryInput.value : '';

    if (!auth) {
        window.location.href = 'index.html';
        return;
    }

    try {
        const response = await fetch(`/api/customer?query=${encodeURIComponent(query)}&page=${currentPage}&size=${pageSize}`, {
            headers: {
                'Authorization': `Basic ${auth}`
            }
        });

        if (response.ok) {
            const pageData = await response.json();
            displayCustomers(pageData.content);
            updatePagination(pageData.totalPages);
        } else {
            alert('Error fetching customers');
        }
    } catch (error) {
        alert('Error fetching customers');
    }
}

// Display customers in the table
function displayCustomers(customers) {
    customerTableBody.innerHTML = '';
    customers.forEach(customer => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${customer.id}</td>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.email}</td>
            <td>${customer.contactNumber}</td>
            <td>
                <button class="btn btn-sm btn-warning" onclick="openUpdateCustomerModal(${customer.id})">Update</button>
                <button class="btn btn-sm btn-danger" onclick="deleteCustomer(${customer.id})">Delete</button>
            </td>
        `;
        customerTableBody.appendChild(row);
    });
}

// Update pagination controls
function updatePagination(totalPages) {
    const paginationContainer = document.getElementById('pagination');
    paginationContainer.innerHTML = '';

    for (let i = 0; i < totalPages; i++) {
        const pageButton = document.createElement('button');
        pageButton.innerText = i + 1;
        pageButton.className = 'btn btn-sm btn-primary';
        pageButton.onclick = () => {
            currentPage = i;
            fetchCustomers();
        };
        paginationContainer.appendChild(pageButton);
    }
}



// Attach event listener to search field
if (queryInput) {
    queryInput.addEventListener('input', () => {
        currentPage = 0;
        fetchCustomers();
    });
}

// Handle creating new customer
document.getElementById('createCustomerForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    const auth = localStorage.getItem('auth');

    const data = {
        firstName: document.getElementById('create-firstName').value,
        lastName: document.getElementById('create-lastName').value,
        email: document.getElementById('create-email').value,
        contactNumber: document.getElementById('create-contactNumber').value,
        addresses: gatherAddresses(),
        preference: {
            emailOptIn: document.getElementById('create-emailOptIn').checked,
            smsOptIn: document.getElementById('create-smsOptIn').checked,
            postalOptIn: document.getElementById('create-postalOptIn').checked
        }
    };

    try {
        const response = await fetch('/api/customer', {
            method: 'POST',
            headers: {
                'Authorization': `Basic ${auth}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        if (response.ok) {
            alert('Customer created');
            fetchCustomers(); // Reload customer list
            closeModal('#createCustomerModal');
        } else {
            alert('Failed to create customer');
        }
    } catch (error) {
        alert('Error creating customer');
    }
});

// Handle updating customer
document.getElementById('updateCustomerForm').addEventListener('submit', async function(e) {
    e.preventDefault();
    const auth = localStorage.getItem('auth');
    const customerId = document.getElementById('update-customerId').value;

    const data = {
        firstName: document.getElementById('update-firstName').value,
        lastName: document.getElementById('update-lastName').value,
        email: document.getElementById('update-email').value,
        contactNumber: document.getElementById('update-contactNumber').value,
        addresses: gatherUpdateAddresses(),
        preference: {
            emailOptIn: document.getElementById('update-emailOptIn').checked,
            smsOptIn: document.getElementById('update-smsOptIn').checked,
            postalOptIn: document.getElementById('update-postalOptIn').checked
        }
    };

    try {
        const response = await fetch(`/api/customer/${customerId}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Basic ${auth}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });
        if (response.ok) {
            alert('Customer updated');
            fetchCustomers(); // Reload customer list
            closeModal('#updateCustomerModal');
        } else {
            alert('Failed to update customer');
        }
    } catch (error) {
        alert('Error updating customer');
    }
});

// Fetch customer by ID and populate the update modal
async function openUpdateCustomerModal(customerId) {
    const auth = localStorage.getItem('auth');
    try {
        const response = await fetch(`/api/customer/${customerId}`, {
            headers: {
                'Authorization': `Basic ${auth}`
            }
        });

        if (response.ok) {
            const customer = await response.json();
            populateUpdateModal(customer);
            openModal('#updateCustomerModal');
        } else {
            alert('Failed to fetch customer data');
        }
    } catch (error) {
        alert('Error fetching customer data');
    }
}

// Populate update modal with fetched customer data
function populateUpdateModal(customer) {
    document.getElementById('update-customerId').value = customer.id || '';
    document.getElementById('update-firstName').value = customer.firstName || '';
    document.getElementById('update-lastName').value = customer.lastName || '';
    document.getElementById('update-email').value = customer.email || '';
    document.getElementById('update-contactNumber').value = customer.contactNumber || '';

    // Check if addresses exist and populate them, up to 3 addresses
    if (customer.addresses && customer.addresses.length > 0) {
        for (let i = 1; i <= Math.min(customer.addresses.length, 3); i++) {
            document.getElementById(`update-addressType${i}`).value = customer.addresses[i - 1].type || '';
            document.getElementById(`update-addressLine${i}`).value = customer.addresses[i - 1].addressLine || '';
        }
    } else {
        // Clear address fields if there are no addresses
        for (let i = 1; i <= 3; i++) {
            document.getElementById(`update-addressType${i}`).value = '';
            document.getElementById(`update-addressLine${i}`).value = '';
        }
    }

    // Check if preference exists and populate preferences
    if (customer.preference) {
        document.getElementById('update-emailOptIn').checked = !!customer.preference.emailOptIn;
        document.getElementById('update-smsOptIn').checked = !!customer.preference.smsOptIn;
        document.getElementById('update-postalOptIn').checked = !!customer.preference.postalOptIn;
    } else {
        // Clear preference checkboxes if preference is not available
        document.getElementById('update-emailOptIn').checked = false;
        document.getElementById('update-smsOptIn').checked = false;
        document.getElementById('update-postalOptIn').checked = false;
    }
}

// Utility function to gather addresses for update
function gatherUpdateAddresses() {
    const addresses = [];
    for (let i = 1; i <= 3; i++) {
        const type = document.getElementById(`update-addressType${i}`);
        const addressLine = document.getElementById(`update-addressLine${i}`);
        if (addressLine && addressLine.value) {
            addresses.push({ type: type.value, addressLine: addressLine.value, active: true });
        }
    }
    return addresses;
}

// Utility function to gather addresses for create
function gatherAddresses() {
    const addresses = [];
    for (let i = 1; i <= 3; i++) {
        const type = document.getElementById(`create-addressType${i}`);
        const addressLine = document.getElementById(`create-addressLine${i}`);
        if (addressLine && addressLine.value) {
            addresses.push({ type: type.value, addressLine: addressLine.value, active: true });
        }
    }
    return addresses;
}

// Modal open and close utility
function openModal(modalId) {
    $(modalId).modal('show');
}

function closeModal(modalId) {
    $(modalId).modal('hide');
}

// Open create customer modal
document.getElementById('createCustomerBtn').addEventListener('click', function() {
    openModal('#createCustomerModal');
});

// Delete customer
async function deleteCustomer(customerId) {
    const auth = localStorage.getItem('auth');
    const confirmed = confirm('Are you sure you want to delete this customer?');
    if (!confirmed) return;

    try {
        const response = await fetch(`/api/customer/${customerId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Basic ${auth}`
            }
        });
        if (response.ok) {
            alert('Customer deleted');
            fetchCustomers();
        } else {
            alert('Failed to delete customer');
        }
    } catch (error) {
        alert('Error deleting customer');
    }
}