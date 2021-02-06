import re
import urllib.request
import urllib.error
from bs4 import BeautifulSoup

def main():
    baseurl = "https://movie.douban.com/top250?start="
    savePath = "./"
    # 获取链接的规则
    findLink = re.compile(r'<a href="(.* ?)">')
    # 获取img的规则
    findImgSrc = re.compile(r'<img.*="(.* ?)">',re.S)
    # 片名
    findTitle = re.compile(r'<span class="title">(.*)</span>')
    # 评分
    findRatting = re.compile(r'<span class="rating_num" property="v:average">(.*)</span>')
    # 评分人数
    findJudge = re.compile(r'<span>(\d*)人评价</span>')
    # 概况
    findInq = re.compile(r'<span class="inq">(.*)</span>')
    # 相关内容
    findBd = re.compile(r'<p class="">(.*)</p>')

    dataList = getData(baseurl,findLink,findTitle,findRatting)
    print(dataList)

    saveData(savePath)


def getData(baseurl,findLink,findTitle,findRatting):
    dataList = []
    for i in range(0,1):
        url = baseurl + str(i*25)
        html = askURL(url)

        # 解析
        soup = BeautifulSoup(html,"html.parser")
        for item in soup.find_all('div',class_="item"):
            # print(item)
            data = [] #一部电影的所有信息
            item = str(item)
            link = re.findall(findLink,item)[0]
            data.append(link)

            title = re.findall(findTitle,item)
            if(len(title) == 2):
                ctitle = "".join(title[0].replace('/','').split())
                data.append(ctitle)
                etitle = "".join(title[1].replace('/','').split())
                data.append(etitle)
            else:
                data.append(title[0])
                data.append(' ')

            ratting = re.findall(findRatting,item)[0]
            data.append(ratting)

            dataList.append(data)
    return dataList


# 得到指定url内容
def askURL(url):
    head = {
        "user-agent" : "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_1_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36"
    }
    req = urllib.request.Request(url,headers=head)
    html = ""
    try:
        res = urllib.request.urlopen(req)
        html = res.read().decode('utf-8')
    except:
        print("error")
    return html

def saveData(savePath):
    print("save...")



if __name__ == "__main__":
    main()