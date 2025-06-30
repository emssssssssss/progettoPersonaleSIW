document.addEventListener("DOMContentLoaded", function () {
    const selectOpere = document.getElementById('opere');
    const container = document.getElementById('opere-selezionate');
    const hiddenJsonInput = document.getElementById('opereIdsJson');

    const opereAggiunte = new Set();

    // Precarica eventuali ID già presenti nel campo JSON
    try {
        const iniziali = JSON.parse(hiddenJsonInput.value || "[]");
        iniziali.forEach(id => opereAggiunte.add(String(id)));
    } catch (e) {
        console.error("Errore parsing iniziali:", e);
    }

    aggiornaHiddenJson();

    // Aggiunta opera da select
    selectOpere.addEventListener('change', () => {
        const selectedId = selectOpere.value;
        const selectedText = selectOpere.options[selectOpere.selectedIndex].text;

        if (selectedId && !opereAggiunte.has(selectedId)) {
            aggiungiOpera(selectedId, selectedText);
        }

        selectOpere.selectedIndex = 0; // reset selezione
    });

    // Aggiungi opera visivamente e in JSON
    function aggiungiOpera(id, titolo) {
        opereAggiunte.add(id);

        const item = document.createElement('div');
        item.className = 'opera-item';
        item.innerHTML = `
            <span>${titolo}</span>
            <button type="button" class="rimuovi-opera" data-id="${id}">❌</button>
        `;
        container.appendChild(item);

        aggiornaHiddenJson();
    }

    // Rimozione opera
    container.addEventListener('click', (e) => {
        if (e.target.classList.contains('rimuovi-opera')) {
            const id = e.target.dataset.id;
            opereAggiunte.delete(id);
            e.target.parentElement.remove();
            aggiornaHiddenJson();
        }
    });

    // Aggiorna l'input hidden JSON
    function aggiornaHiddenJson() {
        hiddenJsonInput.value = JSON.stringify(Array.from(opereAggiunte));
    }
});
