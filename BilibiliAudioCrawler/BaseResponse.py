import json
from dataclasses import dataclass
from datetime import datetime


class BaseResponse:
    def __init__(self):
        self.__status = 0
        self.__message = ""
        self.__timestamp = datetime.timestamp(datetime.now())
        self.__data = None

    @staticmethod
    def success(data):
        br = BaseResponse()
        br.__message = "success"
        br.__status = 0
        br.__data = data
        return br

    @staticmethod
    def failed(msg):
        br = BaseResponse()
        br.__message = msg
        br.__data = None
        br.__status = - 1
        return br

    def jsonformat(self):
        return {
            "status": self.__status,
            "message": self.__message,
            "timestamp": self.__timestamp,
            "data": self.__data
        }
