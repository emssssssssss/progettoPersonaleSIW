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

            wrapper.innerHTML = `
                <span>${selectedText}</span>
                <button type="button" onclick="this.parentElement.remove(); opereAggiunte.delete('${selectedId}')">✖</button>
                <input type="hidden" name="opere" value="${selectedId}" />
            `;

            container.appendChild(wrapper);
        }

        // Reset select dopo selezione
        this.selectedIndex = 0;
    });
