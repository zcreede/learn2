def bubble_sort(arr):
    """
    冒泡排序算法实现
    :param arr: 待排序的列表
    :return: 排序后的列表
    """
    n = len(arr)
    # 外层循环控制排序轮数
    for i in range(n):
        # 内层循环控制每轮比较次数
        for j in range(0, n-i-1):
            # 如果前一个元素大于后一个元素，则交换它们
            if arr[j] > arr[j+1]:
                arr[j], arr[j+1] = arr[j+1], arr[j]
    return arr

# 测试代码
if __name__ == "__main__":
    # 测试用例
    test_array = [10, 29, 51, 32, 3, 12, 44, 33, 15]
    print("原始数组:", test_array)
    
    # 调用冒泡排序
    sorted_array = bubble_sort(test_array)
    print("排序后的数组:", sorted_array) 

    # 创建第二个测试数组
    test_array2 = [10, 29, 51, 32, 3, 12, 44, 33, 15]
    # 打印原始数组内容
    print("原始数组:", test_array2)
    # 调用冒泡排序函数对数组进行排序
    sorted_array2 = bubble_sort(test_array2)
    # 这里我们使用了冒泡排序算法对test_array2进行排序
    # 冒泡排序的时间复杂度为O(n²)，其中n是数组的长度
    # 该算法通过重复遍历数组，比较相邻元素并交换它们的位置来实现排序
    # 每一轮遍历后，最大的元素会"冒泡"到数组的末尾
    # 打印排序后的数组内容
    print("排序后的数组:", sorted_array2)

    # Python基础语法大纲
    python_basics_outline = {
        "1. 基础知识": [
            "Python简介与安装",
            "Python解释器使用",
            "第一个Python程序",
            "注释的使用"
        ],
        "2. 数据类型": [
            "数字类型（整数、浮点数、复数）",
            "字符串",
            "列表",
            "元组",
            "字典",
            "集合",
            "布尔值"
        ],
        "3. 变量与运算符": [
            "变量的定义与使用",
            "算术运算符",
            "比较运算符",
            "逻辑运算符",
            "赋值运算符",
            "位运算符",
            "成员运算符",
            "身份运算符"
        ],
        "4. 控制流": [
            "条件语句（if-elif-else）",
            "循环语句（for, while）",
            "break和continue",
            "pass语句"
        ],
        "5. 函数": [
            "函数定义与调用",
            "参数传递",
            "返回值",
            "作用域",
            "匿名函数（lambda）",
            "内置函数"
        ],
        "6. 模块与包": [
            "模块的导入与使用",
            "包的概念",
            "常用标准库介绍"
        ],
        "7. 文件操作": [
            "文件的打开与关闭",
            "文件的读写操作",
            "with语句"
        ],
        "8. 异常处理": [
            "try-except语句",
            "自定义异常",
            "finally子句"
        ],
        "9. 面向对象编程": [
            "类与对象",
            "属性与方法",
            "继承与多态",
            "封装",
            "魔术方法"
        ],
        "10. 高级特性": [
            "生成器与迭代器",
            "装饰器",
            "上下文管理器",
            "列表推导式",
            "字典推导式",
            "集合推导式"
        ]
    }

    ## 打印Python基础语法大纲
    # print("\nPython基础语法大纲:")
    for section, topics in python_basics_outline.items():
        print(f"\n{section}")
        for topic in topics:
            print(f"  - {topic}")

    