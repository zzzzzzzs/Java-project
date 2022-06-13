# 写文件


import os


def main():
    # 打开一个文件
    f = open("test.txt", "w")

    # 写入文件
    f.write("Hello, world!\n")
    f.write("Hello, world!\n")
    f.write("Hello, world!\n")

    # 关闭文件
    f.close()


if __name__ == '__main__':
    main()