# ASOUL Rhythm

旨在实现一个输入BV号即可下载音频，并在本地进行音频管理的安卓端App

## 后端

目前后端的作用仅用于解析BV号获取视频相关信息，使用Flask作为Web框架，使用BeautifulSoup进行爬取

| 地址         | 请求方式 | 参数 |      |
| ------------ | -------- | ---- | ---- |
| BaseURL/{bv} | GET      | p    |      |

返回值：

```json
{
    "status": 0, 
    "message": "success", 
    "timestamp": 1689836362.42932, 
    "data": {
        "video_title": "爱的飞行日记2023-01-25", 
        "img": "",
        "urls": [
            {"url": "", 
             "quality": 0
            }, 
            {"url": "", 
             "quality": 1
            }, 
            {"url": "", 
             "quality": 2
            }
        ]
    }
}
```

## 安卓端

> 技术路线
>
> 1. UI: Kotlin + Jetpack Compose
> 2. 网络请求：Retrofit2
> 3. coil处理图片
> 4. 数据存储



***部分UI如下：***

<table>
    <tr><center><p>点击头部的四个Q版小人切换对应应援色主题</p></center></tr>
<tr>
<td ><center><img src="./doc/1.jpg" >首页（拉姐主题色） </center></td>
<td ><center><img src="./doc/2.jpg">首页（晚晚主题色）</center></td>
</tr>
<tr>
<td><center><img src="./doc/4.jpg"  >根据BV号下载界面</center></td>
<td ><center><img src="./doc/3.jpg"  >修改信息界面</center> </td>
</tr>
</table>



TODO：

- [x] 使用Room持久化存储歌曲信息
- [ ] 使用Notification在通知栏显示播放信息
- [ ] 蓝牙耳机控制播放
- [ ] App切到后台时间过长播放会中断
- [ ] 歌曲进度条
- [x] 哔哩哔哩APP分享到应用直接解析
- [x] 显示专辑封面
