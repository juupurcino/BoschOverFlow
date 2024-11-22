document.getElementById('addSpace').addEventListener('click', function () {
    const spaceName = document.getElementById('spaceName').value;

    if (spaceName.trim() !== '') {
        fetch("http://localhost:8080/spaces", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(spaceName),
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text); });
            }
            return response.text();
        })
        .then(data => {

            const cardsContainer = document.querySelector('.cards');
            const newCard = `
                <div class="card" style="width: 22rem;">
                    <img src="https://www.wearebosch.com/img/globe.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">${spaceName}</h5>
                        <div style="display: flex; flex-direction: row; justify-content: space-between;">
                            <a href="space.html" class="btn btn-primary">See more</a>
                            <span class="material-symbols-outlined" data-bs-toggle="modal" data-bs-target="#modaldelete">delete</span>
                        </div>
                    </div>
                </div>`;
            cardsContainer.insertAdjacentHTML('beforeend', newCard);

            document.getElementById('spaceName').value = '';
            const modal = bootstrap.Modal.getInstance(document.getElementById('modal'));
            modal.hide();
        })
        .catch(error => {
            alert(error.message);
        });
    } else {
        alert('Please enter a name for the space.');
    }
});
