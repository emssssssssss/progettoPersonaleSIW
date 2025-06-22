function apriModale(button) {
    const fasciaId = button.getAttribute('data-fascia-id');
    const maxPosti = button.getAttribute('data-posti-disponibili');

    document.getElementById('inputFasciaId').value = fasciaId;
    const inputPosti = document.getElementById('inputPosti');
    inputPosti.max = maxPosti;
    inputPosti.value = 1;

    document.getElementById('modalePrenotazione').style.display = 'block';
}

function chiudiModale() {
    document.getElementById('modalePrenotazione').style.display = 'none';
}

window.onclick = function (event) {
    const modale = document.getElementById('modalePrenotazione');
    if (event.target === modale) {
        chiudiModale();
    }
};
