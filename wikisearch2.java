let searchInputEl = document.getElementById("searchInput");
let searchResultsEl = document.getElementById("searchResults");
let spinnerEl = document.getElementById("spinner");

function nowcreate(okay) {
    let {
        link,
        title,
        description
    } = okay;
    let resultcon = document.createElement("div");
    resultcon.classList.add("result-item");
    searchResultsEl.appendChild(resultcon);

    let titleEl = document.createElement("a");
    titleEl.href = link;
    titleEl.textContent = title;
    titleEl.target = "_blank";
    titleEl.classList.add("result-title");
    resultcon.appendChild(titleEl);

    let brek = document.createElement("br");
    resultcon.appendChild(brek);

    let linkEl = document.createElement("a");
    linkEl.href = link;
    linkEl.textContent = link;
    linkEl.target = "_blank";
    linkEl.classList.add("result-url");
    resultcon.appendChild(linkEl);

    let descriptionEl = document.createElement("p");
    descriptionEl.textContent = description;
    descriptionEl.classList.add("link-description");
    resultcon.appendChild(descriptionEl);
}

function display(okay_result) {
    for (let okay of okay_result) {
        nowcreate(okay);
    }
}

function startUp(event) {
    if (event.key === "Enter") {
        let options = {
            method: "GET",
        }
        spinnerEl.classList.remove("d-none");
        searchResultsEl.classList.add("d-none");

        fetch("https://apis.ccbp.in/wiki-search?search=" + searchInputEl.value, options)
            .then(function(response) {
                return response.json();
            })
            .then(function(jsonText) {
                spinnerEl.classList.add("d-none");
                searchResultsEl.classList.remove("d-none");
                let {
                    search_results
                } = jsonText;
                display(search_results);
            })
    }
}

searchInputEl.addEventListener("keydown", startUp);