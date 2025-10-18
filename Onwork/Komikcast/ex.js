import * as cheerio from "cheerio";

/**
 * Mengekstrak daftar genre dari elemen <ul class="komiklist_dropdown-menu c4 genrez">
 * @param {string} html - HTML string berisi daftar genre.
 * @returns {Object[]} Array of genre object { id, value, label }
 */
function extractGenres(html) {
  const $ = cheerio.load(html);
  const genres = [];

  $(".komiklist_dropdown-menu li").each((_, el) => {
    const input = $(el).find("input");
    const label = $(el).find("label");

    const id = input.attr("id")?.trim() || "";
    const value = input.attr("value")?.trim() || "";
    const name = label.text().trim();

    if (value) {
      genres.push({ id, value, label: name });
    }
  });

  return genres;
}

/**
 * Mengekstrak daftar status dari elemen <ul class="komiklist_dropdown-menu status">
 * @param {string} html - HTML string berisi daftar status.
 * @returns {Object[]} Array of status object { id, value, label }
 */
function extractStatus(html) {
  const $ = cheerio.load(html);
  const statuses = [];

  $(".komiklist_dropdown-menu li").each((_, el) => {
    const input = $(el).find("input");
    const label = $(el).find("label");

    const id = input.attr("id")?.trim() || "";
    const value = input.attr("value")?.trim() || "";
    const name = label.text().trim();

    statuses.push({ id, value, label: name });
  });

  return statuses;
}

const genreHTML = `<ul class="komiklist_dropdown-menu type">
               <li>
                  <input class="komiklist_filter-item" type="radio" id="type-all" name="type" value="" checked="">
                  <label for="type-all"> All
                  </label>
               </li>
               <li>
                  <input class="komiklist_filter-item" type="radio" id="type-manga" name="type" value="manga">
                  <label for="type-manga"> Manga
                  </label>
               </li>
               <li>
                  <input class="komiklist_filter-item" type="radio" id="type-manhwa" name="type" value="manhwa">
                  <label for="type-manhwa"> Manhwa
                  </label>
               </li>
               <li>
                  <input class="komiklist_filter-item" type="radio" id="type-manhua" name="type" value="manhua">
                  <label for="type-manhua"> Manhua
                  </label>
               </li>
            </ul>`;

const statusHTML = `<ul class="komiklist_dropdown-menu sort_by">
               <li>
                  <input class="komiklist_filter-item" type="radio" id="sort_by-title" name="orderby" checked="" value="titleasc">
                  <label for="sort_by-title"> A-Z
                  </label>
               </li>
               <li>
                  <input class="komiklist_filter-item" type="radio" id="sort_by-titlereverse" name="orderby" value="titledesc">
                  <label for="sort_by-titlereverse"> Z-A
                  </label>
               </li>
               <li>
                  <input class="komiklist_filter-item" type="radio" id="sort_by-update" name="orderby" value="update">
                  <label for="sort_by-update"> update
                  </label>
               </li>
               <li>
                  <input class="komiklist_filter-item" type="radio" id="sort_by-popular" name="orderby" value="popular">
                  <label for="sort_by-popular"> Popular
                  </label>
               </li>
            </ul>`;

const genres = extractGenres(genreHTML);
const statuses = extractStatus(statusHTML);

console.log("Type:", genres);
console.log("Sortby:", statuses);
