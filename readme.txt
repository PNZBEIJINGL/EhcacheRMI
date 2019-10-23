EhCache 的主要特性有：

快速、精干
简单；
多种缓存策略；
缓存数据有两级：内存和磁盘，因此无需担心容量问题；
缓存数据会在虚拟机重启的过程中写入磁盘；
可以通过 RMI、可插入 API 等方式进行分布式缓存；
具有缓存和缓存管理器的侦听接口；
支持多缓存管理器实例，以及一个实例的多个缓存区域；
提供 Hibernate 的缓存实现；
EhCache集群解决的问题：
　　由 于 EhCache 是进程中的缓存系统，一旦将应用部署在集群环境中，每一个节点维护各自的缓存数据，当某个节点对缓存数据进行更新，这些更新的数据无法在其它节点中共享， 这不仅会降低节点运行的效率，而且会导致数据不同步的情况发生。例如某个网站采用 A、B 两个节点作为集群部署，当 A 节点的缓存更新后，而 B 节点缓存尚未更新就可能出现用户在浏览页面的时候，一会是更新后的数据，一会是尚未更新的数据。
　　所以就需要用到 EhCache 的集群解决方案。
　　
EhCache集群方案：

• Terracotta
• RMI
• JMS : 依赖 ehcache-jmsreplication.jar
• JGroups : 依赖ehcache-jgroupsreplication.jar
• EhCache Server

　　其中的三种最为常用集群方式，分别是 RMI、JGroups 以及 EhCache Server 。

EhCache集群疑问

• 你如何知道集群环境中的其他缓存？
• 分布式传送的消息是什么形式？
• 什么情况需要进行复制？增加（Puts），更新（Updates）或是失效（Expiries）？
• 采用什么方式进行复制？同步还是异步方式？

EhCache集群基本概念

1、正确的元素类型：只有可序列化的元素可以进行复制。一些操作，比如移除，只需要元素的键值而不用整个元素；在这样的操作中即使元素不是可序列化的但键值是可序列化的也可以被复制。
2、成员发现（Peer Discovery）：Ehcache进行集群的时候有一个cache组的概念。每个cache都是其他cache的一个peer，没有主cache的存在。成员发现（Peer Discovery）正是用来解决 “你如何知道集群环境中的其他缓存?” 这个问题的。Ehcache提供了两种机制用来进行成员发现，即：自动成员发现和手动成员发现。要使用一个内置的成员发现机制要在ehcache的配置文件中指定cacheManagerPeerProviderFactory元素的class属性为
net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory。