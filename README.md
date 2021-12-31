# jpa-source
自己实现 `SpringDataJPA` 核心模块（Hibernate才是仓储的真正实现，SpringDataJPA只是包装及注入给Spring）；  
核心在 `src/main/jpaSouce/MyJpa` 目录中；
- original 原始方式，人工调用
- way1 动态注入其中的一种方式
- way2 动态注入并且配合EnableXXX注解正规的最终方案
