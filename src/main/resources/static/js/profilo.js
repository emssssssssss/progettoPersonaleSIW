document.addEventListener('DOMContentLoaded', () => {
    const tabs = document.querySelectorAll('.tab');
    const sezioni = document.querySelectorAll('.prenotazioni-sezione');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            const target = tab.getAttribute('data-tab');

            // Rimuovo active da tutte le linguette
            tabs.forEach(t => t.classList.remove('active'));
            // Aggiungo active alla linguetta cliccata
            tab.classList.add('active');

            // Mostro solo la sezione corrispondente, nascondo le altre
            sezioni.forEach(sezione => {
                if (sezione.id === target) {
                    sezione.classList.add('active');
                } else {
                    sezione.classList.remove('active');
                }
            });
        });
    });
});

// Funzione per aprire il popup con i dettagli della prenotazione
function apriPopup(button) {
    const id = button.getAttribute('data-id'); // <-- recupera l'id
    const popup = document.getElementById('popup-' + id);
    if (popup) {
        popup.style.display = 'block';
    }
}

// Funzione per chiudere il popup
function chiudiPopup(span) {
    const popup = span.closest('.popup'); // trova il popup più vicino
    if (popup) {
        popup.style.display = 'none';
    }
}

// Chiudi il popup se clicchi fuori dal contenuto
window.onclick = function(event) {
    // Tutti i popup (se ne hai più di uno)
    const popups = document.querySelectorAll('.popup');
    popups.forEach(popup => {
        if (event.target === popup) {
            popup.style.display = 'none';
        }
    });
}
