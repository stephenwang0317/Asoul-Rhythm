import json

import requests
from bs4 import BeautifulSoup as bs

headers = {
    'Referer': 'https://www.bilibili.com/',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36'
}


def get_src_url(bv):
    url = "https://www.bilibili.com/video/" + bv
    text = requests.get(url=url, headers=headers)
    soup = bs(text.text, 'lxml')
    scripts = soup.find_all("script")
    try:
        video_title = soup.find_all("h1", attrs={"class": "video-title"})[0]
    except IndexError:
        video_title = ""

    src_json = None

    for script in scripts:
        # print("====================")
        script_text = str(script.string)
        if script_text.startswith("window.__playinfo__="):
            src_json = json.loads(script_text.lstrip("window.__playinfo__="))
        # print(script_text)
        print(src_json)
        if src_json is not None:
            break
    try:
        return {"url": src_json["data"]["dash"]["audio"][0]["baseUrl"], "video-title": video_title.string}
    except TypeError:
        return None


if __name__ == "__main__":
    print(get_src_url("BV1kP411K7RV?p=108"))
