document.addEventListener("DOMContentLoaded", () => {
    const body = document.body;
    const eventoId = body.getAttribute("data-evento-id");
    const csrfToken = body.getAttribute("data-csrf-token");
    const csrfHeader = body.getAttribute("data-csrf-header");

    // Container visivo e hidden
    const opereList = document.getElementById("opere-list");
    const opereHiddenContainer = document.getElementById("opere-hidden-container");

    // Set per tracciare le opere aggiunte (stringhe)
    const opereAggiunte = new Set(
        Array.from(opereList.querySelectorAll("li")).map(li => li.dataset.id)
    );

    function creaInputHidden(id) {
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "opereIds";
        input.value = id;
        input.dataset.operaId = id; // per identificarlo facilmente
        return input;
    }

    function aggiungiOpera(id, titolo) {
        if (opereAggiunte.has(id)) return;

        opereAggiunte.add(id);

        // aggiungo elemento visivo
        const li = document.createElement("li");
        li.classList.add("opera-item");
        li.dataset.id = id;

        const span = document.createElement("span");
        span.textContent = titolo;

        const btn = document.createElement("button");
        btn.type = "button";
        btn.classList.add("rimuovi-opera");
        btn.textContent = "✖";

        btn.addEventListener("click", () => {
            rimuoviOpera(id, li);
        });

        li.appendChild(span);
        li.appendChild(btn);
        opereList.appendChild(li);

        // aggiungo input hidden al form
        const inputHidden = creaInputHidden(id);
        opereHiddenContainer.appendChild(inputHidden);
    }

    function rimuoviOpera(id, liElement) {
        if (!confirm("Sei sicuro di voler rimuovere quest'opera dall'evento?")) return;

        // Se vuoi fare chiamata fetch al backend per rimuoverla da DB subito,
        // altrimenti puoi rimandare a salvataggio form (qui ti faccio solo UI update)
        // Se la fetch va a buon fine:
        opereAggiunte.delete(id);
        liElement.remove();

        // Rimuovo input hidden corrispondente
        const inputToRemove = opereHiddenContainer.querySelector(`input[data-opera-id="${id}"]`);
        if (inputToRemove) inputToRemove.remove();
    }

    // Inizializza i bottoni di rimozione già presenti nel markup
    document.querySelectorAll("#opere-list .rimuovi-opera").forEach(btn => {
        btn.addEventListener("click", function () {
            const li = this.closest("li");
            const operaId = li.dataset.id;
            rimuoviOpera(operaId, li);
        });
    });

    // Gestione select per aggiungere opere
    const select = document.getElementById("opera-select");
    select.addEventListener("change", function () {
        const selectedId = this.value;
        const selectedText = this.options[this.selectedIndex].text;

        if (selectedId && !opereAggiunte.has(selectedId)) {
            aggiungiOpera(selectedId, selectedText);
        }

        this.selectedIndex = 0; // resetta select
    });

      // ------ Inizio codice nuovo per gestione fasce ------
    const fasceHiddenContainer = document.getElementById("fasce-hidden-container");
    const btnAggiungiFascia = document.getElementById("aggiungi-fascia");

    btnAggiungiFascia.addEventListener("click", () => {
        const dataInput = document.getElementById("fascia-data");
        const orarioInput = document.getElementById("fascia-orario");
        const capienzaInput = document.getElementById("fascia-capienza");

        const data = dataInput.value;
        const orario = orarioInput.value;
        const capienza = capienzaInput.value;

        if (!data || !orario || !capienza || capienza <= 0) {
            alert("Compila tutti i campi della fascia con valori validi.");
            return;
        }

        // Crea gli input hidden da inviare al backend
        const inputData = document.createElement("input");
        inputData.type = "hidden";
        inputData.name = "fasceData";
        inputData.value = data;

        const inputOrario = document.createElement("input");
        inputOrario.type = "hidden";
        inputOrario.name = "fasceOrarioInizio";
        inputOrario.value = orario;

        const inputCapienza = document.createElement("input");
        inputCapienza.type = "hidden";
        inputCapienza.name = "fasceCapienza";
        inputCapienza.value = capienza;

        // Aggiungo i campi hidden al container
        fasceHiddenContainer.appendChild(inputData);
        fasceHiddenContainer.appendChild(inputOrario);
        fasceHiddenContainer.appendChild(inputCapienza);

        // (Facoltativo) mostra la fascia aggiunta nella UI sotto fasce esistenti
        const listaFasce = document.querySelector(".fasce-esistenti");
        if (listaFasce) {
            const li = document.createElement("li");
            li.textContent = `${data} ${orario} (Capienza: ${capienza})`;
            listaFasce.appendChild(li);
        }

        // Reset campi input
        dataInput.value = "";
        orarioInput.value = "";
        capienzaInput.value = "1";
    });
});
