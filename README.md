# 百度改进的Elasticsearch
## 介绍
[Elasticsearch](https://github.com/elastic/elasticsearch)是一个灵活、功能强大的开源、分布式的实时搜索和分析引擎，百度从2014年开始在内部使用Elasticsearch来解决日志分析，多维分析，文本检索等问题。 在使用的过程中遇到了一系列的问题，比如Elasticsearch的多副本一致性，脑裂问题，查询语言不易用等。针对这些问题百度做了一系列的优化和改进，改进主要基于社区版本的Elasticsearch 2.3.3 和 Crate 0.55.

## 改进的特性

- 支持SQL并兼容MySQL链接协议
- 完善的权限管理
- 改进了Elasticsearch的脑裂问题
- 实现了多副本强一致
- 内置多租户管理功能
- 增强的Reindex能力
- 其他易用性改进

这些改进的详细介绍位于[Overview文档](./docs/overview.md)中。

## 编译

### 环境依赖

- 操作系统： CentOS 6.3 或者 Ubuntu 16.04 及以上版本。
- JDK： 1.7 及以上版本。

### 编译过程

- 进入根目录，运行build.sh 脚本，系统自动完成编译的工作，我们改变了Elasticsearch原有的编译方式，整个编译过程都是离线的，不需要从网上下载任何依赖包。
- 编译好的二进制文件输出在 output 目录中，可以直接部署使用。

	注： Elasticsearch原生的源代码是基于Gradle或者Maven来下载依赖包进行编译的，但是在国内环境一些jar包很难下载下来，导致开发者很难在本地通过源代码的方式调试Elasticsearch，学习源码。 我们将Elasticsearch的所有依赖都下载到本地，放在deps目录下，编译的方式改为ant方式，用户可以在任何环境下使用这份源码搭建起Elasticsearch的开发环境，调试Elasticsearch，学习Elasticsearch的源码。

## 部署
- 部署Distributed Log： 多副本一致和脑裂的改进这两个功能依赖了twitter开源的[Distributed Log](http://distributedlog.incubator.apache.org/docs/latest/ "Distributed Log"), 所以如果要使用这两个功能，用户需要先部署Distributed Log，部署的文档请参考Distributed Log的[官方文档](http://distributedlog.incubator.apache.org/docs/latest/deployment/cluster)。 如果用户不需要这两个能力，那么不需要安装Distributed Log。
- 部署Elasticsearch： 部署的方式跟社区版本的Elasticsearch大体是一样的，唯一的区别是需要在配置文件中增加三个配置项：
	- dl.endpoint 表示在Distributed Log中建立的namespace，namespace的概念请参考Distributed Log的文档。
	- discovery.type: dl 表示节点的加入退出，Master选举都使用我们改进的基于Distributed Log的实现。这个参数的含义可以参考Elasticsearch文档中对于[Discovery Plugins的介绍](https://www.elastic.co/guide/en/elasticsearch/plugins/current/discovery.html)。
	- mysql.port: 8306 表示提供SQL服务时使用的端口号，用户用MySQL客户端访问时，需要通过这个端口来连接。

## 使用
- 由于内置了权限功能，所以用户访问时必须传递用户名密码，默认的root密码是root，具体的访问方式有以下两种：
	-  通过http客户端访问：  curl http://es_host:http_port/ --user root:root_passwd 
	-  通过MySQL客户端访问： mysql -uroot -hes_host -proot_passwd -Pmysql_port
- 如果用户需要使用多副本强一致的功能时，需要在建立Index的时候指定index.engine.use_distributed_log 这个参数为true。比如

		PUT /index_name
		{
		    "settings": {
		        "number_of_replicas": 1,
		        "number_of_shards": 1,
		        "index.engine.use_distributed_log":true
		    }
		}
- SQL 的使用请参考 [Crate的官方文档](https://crate.io/docs/reference/sql/ddl/basics.html)。

## Contact us

palo-rd@baidu.com

需要加入Elasticsearch微信技术讨论群的, 请扫描图中的二维码加入。

![](./docs/images/weixinqun.jpg)


## License

	This software is licensed under the Apache License, version 2 ("ALv2"), quoted below.
	
	Copyright 2009-2016 Elasticsearch <https://www.elastic.co>
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may not
	use this file except in compliance with the License. You may obtain a copy of
	the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
	License for the specific language governing permissions and limitations under
	the License.
