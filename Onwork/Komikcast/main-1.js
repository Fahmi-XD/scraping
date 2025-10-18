import axios from "axios";
import * as cheerio from "cheerio";
import fs from "fs";

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

/**
 * Scrape daftar komik dari halaman utama Komikcast.
 * Mengambil judul, URL, thumbnail, dan chapter terbaru.
 * @returns {Promise<Object[]>} - Mengembalikan array berisi data komik.
 */
async function scrapeKomikcastHome() {
    try {
        return await Util.extract("https://komikcast03.com/", async ($) => {
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
 * Scrape daftar komik dari file daftar-komik.html.
 * Mengambil judul, URL, thumbnail, tipe, chapter terbaru, dan rating.
 * @param {cheerio.CheerioAPI} $ - Objek Cheerio yang sudah di-load dengan konten HTML.
 * @returns {Object[]} - Mengembalikan array berisi data komik dari halaman daftar komik.
 */
function scrapeDaftarKomik($) {
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
}


/**
 * Main function untuk menjalankan scraping.
 */
(async () => {
    try {
        const htmlContent = fs.readFileSync('daftar-komik.html', 'utf-8');
        
        const $ = cheerio.load(htmlContent);
        
        const dataDaftarKomik = scrapeDaftarKomik($);

        console.log("Total komik ditemukan di daftar-komik.html:", dataDaftarKomik.length);
        console.log("Berikut adalah 3 data pertama:");
        console.log(JSON.stringify(dataDaftarKomik.slice(0, 3), null, 2));
    } catch (error) {
        console.error("Gagal membaca atau memproses file daftar-komik.html:", error.message);
    }
})();