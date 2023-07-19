import json
import re

import requests
from bs4 import BeautifulSoup as bs

headers = {
    'Referer': 'https://www.bilibili.com/',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'
}


def get_src_url(bv, part):
    if part is None:
        url = "https://www.bilibili.com/video/" + bv + "?p=1"
    else:
        url = "https://www.bilibili.com/video/" + bv + "?p=" + str(part)

    print("url: " + url)
    text = requests.get(url=url, headers=headers)
    print(text.text)
    soup = bs(text.text, 'lxml')
    scripts = soup.find_all("script")

    video_title = soup.title.string
    video_title = video_title.rstrip("_哔哩哔哩_bilibili")

    img_url_tag = soup.find_all("meta", attrs={"itemprop": "image"})[0]
    img_url = None
    if img_url_tag is not None:
        img_url = str(img_url_tag.get("content"))
        result = re.sub(r"@(.*)$", "", img_url, 0, re.MULTILINE)
        img_url_with_https = "https:" + result

    src_json = None

    for script in scripts:

        script_text = str(script.string)
        if script_text.startswith("window.__playinfo__="):
            src_json = json.loads(script_text.lstrip("window.__playinfo__="))
        # print(script_text)
        if src_json is not None:
            # print(src_json)
            break
    try:
        ret = []
        for audio in src_json["data"]["dash"]["audio"]:
            ret.append(
                {"url": audio["baseUrl"], "bandwidth": audio["bandwidth"]}
            )
        ret.sort(key=sort_key)
        ret2 = []
        for index, audio in enumerate(ret):
            ret2.append(
                {"url": audio["url"], "quality": index}
            )
        return {
            "video_title": video_title,
            "urls": ret2,
            "img": img_url_with_https
        }
    except TypeError:
        return None


def sort_key(item):
    return item["bandwidth"]


if __name__ == "__main__":
    print(get_src_url("BV1kP411K7RV", 74))
