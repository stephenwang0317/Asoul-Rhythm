from flask import Flask
from flask import request
from BaseResponse import BaseResponse
from utils import get_src_url
import json

app = Flask(__name__)


@app.route("/<bv>", methods=['GET'])
def get_url(bv):
    print(bv)
    part = request.args.get('p')
    ans = get_src_url(bv=bv, part=part)
    if bv != "" and ans is not None:
        br = BaseResponse.success(data=ans)
        return json.dumps(br.jsonformat(), ensure_ascii=False)
    elif ans is None:
        br = BaseResponse.failed(msg="解析失败")
        return json.dumps(br.jsonformat(), ensure_ascii=False)
    else:
        br = BaseResponse.failed(msg="bv is empty")
        return json.dumps(br.jsonformat(), ensure_ascii=False)


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=54321)
