字符串替换Lib，主要用于将多国语言翻译后的字符串，从Excel文档中，导入到strings.xml中进行使用的一个Lib。

详细介绍了每个包中的类，需要使用某个类，请进行搜索定位，谢谢。

read包和write包是对xml、xls进行具体读写的方法，有需要自定义的同学可以看看，但是以下提供的方法应该足够应付字符串的替换了。

API：

xls_to_property包：

GenerateProperties.java：

该类主要用于从Excel中读取指定的Map，写出到xx.properties文件中

xls_to_xml包：

AppendStringMain.java：

把工程中的整个res目录拷贝出来，放到任意程序能访问到的目录中进行缓存。并将类中的ANDROID_STRINGS_FILE_PATH常量改成res所在的路径。

把需要读取的翻译后的excel文件，统一放到一个指定的目录中，随后把该目录的路径，添加给ANDROID_STRINGS_FILE_PATH常量。（需要注意的是：excel文件的文件名称，目前定义是对应国家国家码，如：es.xls对应的是values-es）

main方法中有如下API：

            Map<String, String> excelMaps = readFromExcel.readExcel(page, col1, col2);
     * @param page 读取第几个sheet(ecxel中sheet的编号从0开始,0,1,2,3,....)
     * @param col1 读取哪一列的内容（一般指的是key所在列,从0开始）
     * @param col2 读取哪一列的内容（一般指的是values所在列，从0开始）


随后运行该类的main方法即可

作用：

将excel中的key和value取出来，拼接到对应的国家字符串文件中。

DispersionXls2Xml.java：

有时候，翻译回来的文件里，有多个库中的字符串（如：关于页面、浏览器、文管的字符串都在一个文件中），我们可以使用该类，指定需要读取出来的内容，将他们写入到需要的字符串中。

使用方法与AppendStringMain.java类似，但需要注意一下细节：

配置LIST_NEED_TO_READ数组，该数组就是让我们指定程序需要读取哪些字符串用的。

同样需要如同AppendStringMain.java中一样，拷贝res目录，配置其路径、以及配置excel文件的路径。

配置好以后，运行类中的main方法即可。

SimpleReadXls2Xml.java：

从excel文件中读出数据以后，写入到xml文件中

配置：

STRING_XML_FILE_OUTPUT_PATH常量：xls文件的目录集

生成的xml文件和读取的xls文件名称一致，存放目录也是一致的。

xml_to_xls包：

CompareStringMain.java：

读取每个国家的strings和中文strings比较，寻找出没有进行翻译的内容

将工程中的res目录拷贝出来，放到任意程序能访问的目录下。

配置ANDROID_STRINGS_FILE_PATH常量路径为res所在的目录，注意：不包含res

配置OUTPUT_EXCEL_FILE_PATH为没有翻译过的字符串写出到excel的位置，注意：不包括excel文件的名称

执行类中的main方法即可

GetAllCountry.java：

其中只有一个方法

getAllCountryByPath(File dstfile, String countryCodeXlsToWrite) 
dstfile：res路径

countryCodeXlsToWrite：获取到国家码后，写出到excel文件的路径，包括excel文件名称以及后缀

ReadStringAndWrite.java：

﻿制定一个strings.xml把它的所有字符串读取并写入到一个excel文件中。

配置：

STRING_FILE_PATH_ZH_CN常量：需要读取的中文字符串文件strings.xml

OUTPUT_EXCEL_FILE_PATH常量： 要把数据写入的excel文件所在的路径，包括excel文件名称

执行main方法

delete_from_xml包：

DeleteFromXML.java

配置：

ANDROID_STRINGS_FILE_PATH常量：res目录所在位置，用于遍历strings.xml，对其内部指定数据进行删除。

DELETE_KEYS数组：指定要删除的字符串的key值。