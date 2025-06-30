document.addEventListener("DOMContentLoaded", function () {
    const selectOpere = document.getElementById('opere');
    const container = document.getElementById('opere-selezionate');
    const hiddenInputs = document.getElementById('opere-hidden');
    const opereAggiunte = new Set();

    // --- Precarica le opere già associate (modifica evento) ---
    /*<![CDATA[*/
    let opereIniziali = /*[[${evento.opere}]]*/[];
    /*]]>*/

    opereIniziali.forEach(opera => {
        aggiungiOpera(opera.id, opera.titolo);
    });

    // --- Aggiunta opera nuova ---
    selectOpere.addEventListener('change', () => {
        const selectedId = selectOpere.value;
        const selectedText = selectOpere.options[selectOpere.selectedIndex].text;

        if (selectedId && !opereAggiunte.has(selectedId)) {
            aggiungiOpera(selectedId, selectedText);
        }

        selectOpere.selectedIndex = 0; // reset selezione
    });

    // --- Funzione per aggiungere opera ---
    function aggiungiOpera(id, titolo) {
        opereAggiunte.add(id);

        // Box visivo
        const item = document.createElement('div');
        item.className = 'opera-item';
        item.innerHTML = `
    <span>${titolo}</span>
    <button type="button" class="rimuovi-opera" data-id="${id}">❌</button>
    `;
        container.appendChild(item);

        // Input hidden
        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'opereIds';
        input.value = id;
        input.dataset.id = id;
        hiddenInputs.appendChild(input);
    }

    // --- Rimozione opera ---
    container.addEventListener('click', (e) => {
        if (e.target.classList.contains('rimuovi-opera')) {
            const id = e.target.dataset.id;
            opereAggiunte.delete(id);

            // Rimuove visual
            e.target.parentElement.remove();

            // Rimuove input hidden
            hiddenInputs.querySelector(`input[data-id="${id}"]`)?.remove();
        }
    });


    document.addEventListener("DOMContentLoaded", function () {
        const selectOpere = document.getElementById('opere');
        const container = document.getElementById('opere-selezionate');
        const hiddenInputs = document.getElementById('opere-hidden');

        // Legge gli ID già presenti nel DOM
        const opereAggiunte = new Set();
        hiddenInputs.querySelectorAll('input[name="opereIds"]').forEach(input => {
            opereAggiunte.add(input.value);
        });

        // ... (resto del codice invariato)
    });

});
