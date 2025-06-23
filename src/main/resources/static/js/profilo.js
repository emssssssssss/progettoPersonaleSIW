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
