def insertion_sort(arr):
    """
    插入排序算法实现
    :param arr: 待排序的列表
    :return: 排序后的列表
    """
    # 从第二个元素开始遍历
    for i in range(1, len(arr)):
        # 当前要插入的元素
        key = arr[i]
        # 已排序部分的最后一个元素索引
        j = i - 1
        
        # 将比key大的元素向右移动
        while j >= 0 and arr[j] > key:
            arr[j + 1] = arr[j]
            j -= 1
        
        # 插入key到正确位置
        arr[j + 1] = key
    
    return arr

# 测试代码
if __name__ == "__main__":
    # 测试用例
    test_array = [12, 11, 13, 5, 6, 7]
    print("原始数组:", test_array)
    
    # 调用插入排序
    sorted_array = insertion_sort(test_array)
    print("排序后的数组:", sorted_array) 