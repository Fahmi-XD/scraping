import * as cheerio from 'cheerio';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url); 

const __dirname = path.dirname(__filename);

const htmlFilePath = path.join(__dirname, 'home.html'); 
const html = fs.readFileSync(htmlFilePath, 'utf8');
const $ = cheerio.load(html);

const scrapedData = {
    metadata: {},
    navigation: [],
    banner_ads: [],
    hot_komik_update: [],
    main_latest_updates: [],
    sidebar_popular_comics: [],
    sidebar_genre_list: [],
    latest_project: [],
    footer_navigation: [],
    social_links: {}
};

scrapedData.metadata.title = $('title').text().trim() || null;
scrapedData.metadata.description = $('meta[name="description"]').attr('content') || $('meta[property="og:description"]').attr('content') || null;
scrapedData.metadata.keywords = $('meta[name="keywords"]').attr('content') || null;
scrapedData.metadata.canonical_url = $('link[rel="canonical"]').attr('href') || null;

$('#main-menu .menu li, #main-menu .login-link').each((i, el) => {
    const $item = $(el);
    const $link = $item.is('a') ? $item : $item.find('a');
    scrapedData.navigation.push({
        name: $link.text().trim().replace('LOGIN/REGISTER', 'Login/Register'),
        url: $link.attr('href').replace(/&#038;/g, '&')
    });
});

$('.kln .lmt a').each((i, el) => {
    const linkUrl = $(el).attr('href');
    const imageUrl = $(el).find('img').attr('src');
    if (linkUrl && imageUrl) {
        scrapedData.banner_ads.push({
            link_url: linkUrl,
            image_src: imageUrl
        });
    }
});

$('.bixbox.hothome .swiper-slide').each((i, el) => {
    const $slide = $(el);
    const $link = $slide.find('a[title]');
    const $img = $slide.find('img');
    const $chapterLink = $slide.find('.chapter');

    scrapedData.hot_komik_update.push({
        title: $link.attr('title').trim(),
        url: $link.attr('href'),
        image_src: $img.attr('src') || $img.attr('data-src'),
        type: $slide.find('.type').text().trim(),
        latest_chapter: {
            text: $chapterLink.text().trim(),
            url: $chapterLink.attr('href')
        }
    });
});

$('.listupd > .utao > .uta').each((i, el) => {
    const $item = $(el);
    const $mainLink = $item.find('.luf a');
    const $img = $item.find('.luf img');
    const $latestChapter = $item.find('.luch');
    
    const update = {
        title: $mainLink.attr('title') ? $mainLink.attr('title').trim() : 'N/A',
        url: $mainLink.attr('href'),
        image_src: $img.attr('src') || $img.attr('data-src'),
        type: $item.find('.luf .type').text().trim(),
        rating: $item.find('.numscore').text().trim(),
        chapters: []
    };
    
    $latestChapter.find('li').each((j, li) => {
        const $li = $(li);
        update.chapters.push({
            chapter_text: $li.find('.chap a').text().trim(),
            chapter_url: $li.find('.chap a').attr('href'),
            updated_time: $li.find('.dt:last-child').text().trim()
        });
    });
    
    scrapedData.main_latest_updates.push(update);
});

$('.wpop ul li').each((i, el) => {
    const $item = $(el);
    const $link = $item.find('a');
    const $img = $item.find('img');

    scrapedData.sidebar_popular_comics.push({
        title: $link.attr('title') ? $link.attr('title').trim() : $item.find('.pop-title').text().trim(),
        url: $link.attr('href'),
        image_src: $img.attr('src') || $img.attr('data-src'),
        views: $item.find('.pop-view').text().trim()
    });
});

$('.widget.widget_genres ul li a').each((i, el) => {
    const $link = $(el);
    scrapedData.sidebar_genre_list.push({
        name: $link.text().trim(),
        url: $link.attr('href')
    });
});

$('.widget.widget_latestproject ul li').each((i, el) => {
    const $item = $(el);
    const $link = $item.find('a');

    scrapedData.latest_project.push({
        name: $link.text().trim(),
        url: $link.attr('href')
    });
});

$('.foot-menu .menu li a').each((i, el) => {
    const $link = $(el);
    scrapedData.footer_navigation.push({
        name: $link.text().trim(),
        url: $link.attr('href')
    });
});

$('.social-icon .dt a').each((i, el) => {
    const $link = $(el);
    const href = $link.attr('href');
    let platform = 'unknown';

    if (href.includes('facebook')) platform = 'facebook';
    else if (href.includes('twitter')) platform = 'twitter';
    else if (href.includes('instagram')) platform = 'instagram';
    
    if(href) {
        scrapedData.social_links[platform] = href;
    }
});

console.log(JSON.stringify(scrapedData, null, 2));