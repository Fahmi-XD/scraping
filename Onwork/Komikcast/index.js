import axios from "axios";
import * as cheerio from "cheerio";

/**
 * Utility class untuk melakukan ekstraksi (scraping) dari halaman web menggunakan Cheerio.
 */
class Util {
    /**
     * Mengambil dan mengekstrak konten HTML dari sebuah URL, kemudian menjalankan callback untuk parsing-nya.
     * @param {string} url - URL dari halaman yang ingin di-scrape.
     * @param {(cheerioStatic: cheerio.CheerioAPI) => Promise<any>} callback - Fungsi async yang menerima objek Cheerio ($) untuk memproses HTML.
     * @returns {Promise<any>} - Mengembalikan hasil dari callback (biasanya berupa data hasil scraping).
     */
    static async extract(url, callback) {
        try {
            const response = await axios({
                url,
                method: "GET",
                headers: {
                    "User-Agent":
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36",
                },
            });

            const $ = cheerio.load(response.data);

            const getContent = await callback($);

            return getContent;
        } catch (error) {
            console.error("Error saat extract:", error.message);
            return null;
        }
    }
}

class Komikcast {
    static baseUrl = "https://komikcast03.com";

    /**
     * Scrape daftar komik dari halaman utama Komikcast.
     * Mengambil judul, URL, thumbnail, dan chapter terbaru.
     * @returns {Promise<Object[]>} - Mengembalikan array berisi data komik.
     */
    static async scrapeKomikcastHome() {
        try {
            return await Util.extract(`${Komikcast.baseUrl}/`, async ($) => {
                const comics = [];
                const comicElements = $(".bixbox").find(".listupd > .utao");

                comicElements.each((index, element) => {
                    const el = $(element);

                    const title = el.find("a.series h3").text().trim();
                    const url = el.find("a.series").attr("href");
                    const thumbnail = el.find("div.imgu img").attr("data-src");

                    const chapters = [];
                    el.find(".luf ul li").each((i, chapterElement) => {
                        const chapterEl = $(chapterElement);
                        chapters.push({
                            title: chapterEl.find("a").text().trim(),
                            url: chapterEl.find("a").attr("href"),
                            time: chapterEl.find("span").text().trim(),
                        });
                    });

                    if (title && url) {
                        comics.push({
                            title,
                            url,
                            thumbnail,
                            chapters,
                        });
                    }
                });

                return comics;
            });
        } catch (error) {
            console.error("Error scraping komikcast:", error.message);
            return [];
        }
    }

    /**
     * Scrape daftar komik dari halaman 'daftar-komik' Komikcast dengan filter dinamis.
     * @param {string} page - Halaman yang ingin di-scrape (default: "1").
     * @param {object} options - Opsi filter untuk scraping.
     * @param {string[]} [options.genre=[]] - Array genre yang akan difilter. Contoh: ['action', 'fantasy'].
     * @param {string} [options.status=''] - Status komik ('Ongoing', 'Completed', atau '' untuk semua).
     * @param {string} [options.type=''] - Tipe komik ('manga', 'manhwa', 'manhua', atau '' untuk semua).
     * @param {string} [options.orderby='titleasc'] - Urutan ('titleasc', 'titledesc', 'update', 'popular').
     * @returns {Promise<Object[]>} - Promise yang resolve ke array berisi data komik hasil filter.
     */
    static async scrapeDaftarKomik(page = "1", options = {}) {
        try {
            const {
                genre = [],
                status = '',
                type = '',
                orderby = 'titleasc'
            } = options;

            const params = new URLSearchParams();

            genre.forEach(g => {
                params.append('genre[]', g);
            });

            params.set('status', status);
            params.set('type', type);
            params.set('orderby', orderby);

            console.log(`${Komikcast.baseUrl}?${params.toString()}`);

            return await Util.extract(`${Komikcast.baseUrl}/daftar-komik?${params.toString()}`, async ($) => {
                const daftarKomik = [];

                $(".list-update_item").each((index, element) => {
                    const el = $(element);
                    const linkElement = el.find("a");

                    const title = linkElement.find("h3.title").text().trim();
                    const url = linkElement.attr("href");
                    const thumbnail = linkElement.find("img").attr("src");
                    const type = linkElement.find("span.type").text().trim();
                    const latestChapter = linkElement.find(".chapter").text().trim();
                    const rating = linkElement.find(".numscore").text().trim();

                    if (title && url) {
                        daftarKomik.push({
                            title,
                            url,
                            thumbnail,
                            type,
                            latestChapter,
                            rating,
                        });
                    }
                });

                return daftarKomik;
            });
        } catch (error) {
            console.error("Error scraping komikcast:", error.message);
            return [];
        }
    }
}

/**
 * Contoh penggunaan
 */
(async () => {
    const data = await Komikcast.scrapeKomikcastHome();
    console.log("Total komik ditemukan:", data.length);

    const data2 = await Komikcast.scrapeDaftarKomik("1", { genre: ["yuri"], status: "Ongoing", type: "manga", orderby: "titleasc" });
    console.log("Total Daftar komik ditemukan:", data2.length);

    console.log("Data Komik Pertama:", data2[0]);
})();

/**
 * Filter Options:
 */
// Genres: [
//   { id: 'genre-4-koma', value: '4-koma', label: '4-Koma' },
//   { id: 'genre-action', value: 'action', label: 'Action' },
//   { id: 'genre-adventure', value: 'adventure', label: 'Adventure' },
//   { id: 'genre-comedy', value: 'comedy', label: 'Comedy' },
//   { id: 'genre-cooking', value: 'cooking', label: 'Cooking' },
//   { id: 'genre-demons', value: 'demons', label: 'Demons' },
//   { id: 'genre-drama', value: 'drama', label: 'Drama' },
//   { id: 'genre-ecchi', value: 'ecchi', label: 'Ecchi' },
//   { id: 'genre-fantasy', value: 'fantasy', label: 'Fantasy' },
//   { id: 'genre-game', value: 'game', label: 'Game' },
//   { id: 'genre-gender-bender', value: 'gender-bender', label: 'Gender Bender' },
//   { id: 'genre-gore', value: 'gore', label: 'Gore' },
//   { id: 'genre-harem', value: 'harem', label: 'Harem' },
//   { id: 'genre-historical', value: 'historical', label: 'Historical' },
//   { id: 'genre-horror', value: 'horror', label: 'Horror' },
//   { id: 'genre-isekai', value: 'isekai', label: 'Isekai' },
//   { id: 'genre-josei', value: 'josei', label: 'Josei' },
//   { id: 'genre-magic', value: 'magic', label: 'Magic' },
//   { id: 'genre-martial-arts', value: 'martial-arts', label: 'Martial Arts' },
//   { id: 'genre-mature', value: 'mature', label: 'Mature' },
//   { id: 'genre-mecha', value: 'mecha', label: 'Mecha' },
//   { id: 'genre-medical', value: 'medical', label: 'Medical' },
//   { id: 'genre-military', value: 'military', label: 'Military' },
//   { id: 'genre-music', value: 'music', label: 'Music' },
//   { id: 'genre-mystery', value: 'mystery', label: 'Mystery' },
//   { id: 'genre-one-shot', value: 'one-shot', label: 'One-Shot' },
//   { id: 'genre-police', value: 'police', label: 'Police' },
//   { id: 'genre-psychological', value: 'psychological', label: 'Psychological' },
//   { id: 'genre-reincarnation', value: 'reincarnation', label: 'Reincarnation' },
//   { id: 'genre-romance', value: 'romance', label: 'Romance' },
//   { id: 'genre-school', value: 'school', label: 'School' },
//   { id: 'genre-school-life', value: 'school-life', label: 'School Life' },
//   { id: 'genre-sci-fi', value: 'sci-fi', label: 'Sci-Fi' },
//   { id: 'genre-seinen', value: 'seinen', label: 'Seinen' },
//   { id: 'genre-shoujo', value: 'shoujo', label: 'Shoujo' },
//   { id: 'genre-shoujo-ai', value: 'shoujo-ai', label: 'Shoujo Ai' },
//   { id: 'genre-shounen', value: 'shounen', label: 'Shounen' },
//   { id: 'genre-shounen-ai', value: 'shounen-ai', label: 'Shounen Ai' },
//   { id: 'genre-slice-of-life', value: 'slice-of-life', label: 'Slice of Life' },
//   { id: 'genre-sports', value: 'sports', label: 'Sports' },
//   { id: 'genre-super-power', value: 'super-power', label: 'Super Power' },
//   { id: 'genre-supernatural', value: 'supernatural', label: 'Supernatural' },
//   { id: 'genre-thriller', value: 'thriller', label: 'Thriller' },
//   { id: 'genre-tragedy', value: 'tragedy', label: 'Tragedy' },
//   { id: 'genre-vampire', value: 'vampire', label: 'Vampire' },
//   { id: 'genre-webtoons', value: 'webtoons', label: 'Webtoons' },
//   { id: 'genre-yuri', value: 'yuri', label: 'Yuri' }
// ]

// Status: [
//   { id: 'status-all', value: '', label: 'All' },
//   { id: 'status-Ongoing', value: 'Ongoing', label: 'Ongoing' },
//   { id: 'status-Completed', value: 'Completed', label: 'Completed' }
// ]

// Type: [
//   { id: 'type-manga', value: 'manga', label: 'Manga' },   
//   { id: 'type-manhwa', value: 'manhwa', label: 'Manhwa' },
//   { id: 'type-manhua', value: 'manhua', label: 'Manhua' } 
// ]

// Sortby: [
//   { id: 'sort_by-title', value: 'titleasc', label: 'A-Z' },        
//   { id: 'sort_by-titlereverse', value: 'titledesc', label: 'Z-A' },
//   { id: 'sort_by-update', value: 'update', label: 'update' },      
//   { id: 'sort_by-popular', value: 'popular', label: 'Popular' }    
// ]