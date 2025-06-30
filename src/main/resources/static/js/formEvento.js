const select = document.getElementById("opera-select");
const container = document.getElementById("opere-selezionate");
const opereAggiunte = new Set();

select.addEventListener("change", function () {
    const selectedId = this.value;
    const selectedText = this.options[this.selectedIndex].text;

    if (selectedId && !opereAggiunte.has(selectedId)) {
        opereAggiunte.add(selectedId);

        const wrapper = document.createElement("div");
        wrapper.classList.add("opera-item");
        wrapper.dataset.id = selectedId;

        const index = container.children.length;

        wrapper.innerHTML = `
            <span>${selectedText}</span>
            <button type="button" onclick="this.parentElement.remove(); opereAggiunte.delete('${selectedId}')">✖</button>
            <input type="hidden" name="opere[${index}].id" value="${selectedId}" />
        `;

        container.appendChild(wrapper);
    }

    this.selectedIndex = 0;
});


// Qui fuori dal change della select
let fasciaIndex = 0;
document.getElementById("aggiungi-fascia").addEventListener("click", function () {
    const data = document.getElementById("fascia-data").value;
    const orario = document.getElementById("fascia-orario").value;
    const capienza = document.getElementById("fascia-capienza").value;

    if (!data || !orario || !capienza || capienza < 1) {
        alert("Inserisci tutti i dati validi per la fascia.");
        return;
    }

    const container = document.getElementById("fasce-container");

    const div = document.createElement("div");
    div.className = "fascia-item";
    div.innerHTML = `
        <span>${data} ${orario} - Capienza: ${capienza}</span>
        <input type="hidden" name="fasceOrarie[${fasciaIndex}].data" value="${data}">
        <input type="hidden" name="fasceOrarie[${fasciaIndex}].orarioInizio" value="${orario}">
        <input type="hidden" name="fasceOrarie[${fasciaIndex}].capienzaMassima" value="${capienza}">
        <button type="button" class="remove-fascia">❌</button>
    `;

    div.querySelector(".remove-fascia").addEventListener("click", () => {
        container.removeChild(div);
    });

    container.appendChild(div);
    fasciaIndex++;

    // reset campi
    document.getElementById("fascia-data").value = "";
    document.getElementById("fascia-orario").value = "";
    document.getElementById("fascia-capienza").value = "";
});
