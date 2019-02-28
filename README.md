# Leaves
Leaves is a lightweight and high-performance ID Generator service.Over 900 thousand QPS on my outdated notebook.

# sql

    CREATE TABLE `id_info` (
	`name` VARCHAR(16) NOT NULL COMMENT "业务名",
	`max` BIGINT(20) DEFAULT 0 COMMENT "初始值，最大值",
	`delta` BIGINT(20) DEFAULT 1000 COMMENT "波段ID跨度",
	`step` BIGINT(20) DEFAULT 1 COMMENT "id增量",
	PRIMARY KEY (`name`)
    );
    INSERT INTO `id_info` (`name`,`step`) VALUES ("user", 2);

# API
        id
        curl:http://ip:port/leveas/next?name=user
        response:10004(for example)

# why high-performance ?

通过并发容器缓存idGenerator的当前id：currentId，最大id：maxId，号码段长度：delta，还有步长：step。仅当currentId > maxId或者第一次加载idGenerator时需要访问DB.访问DB的次数从n/step降低到n/(delta*step).

# 一致性测试

        public String nums() {
            String name = "user";
            ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
            Set<Long> s = Collections.synchronizedSet(new HashSet<Long>());
            new Thread(()->{
                int i = 2000;
                while (i-- > 0) {
                    Long id = idGeneratorService.nextId(name);
                    System.out.println(id);
                    queue.add(id);
                    s.add(id);
                }
            }).start();
            new Thread(()->{
                int i = 2000;

                while (i-- > 0) {
                    Long id = idGeneratorService.nextId(name);
                    System.out.println(id);
                    queue.add(id);
                    s.add(id);
                }
            }).start();
            return s.size() + "  " + queue.size();
        }

# qps测试

        public Long qps() {
            Long count = 0L;
            String name = "user";
            Long startTime = System.currentTimeMillis();
            while(System.currentTimeMillis() - startTime < 1000) {
                idGeneratorService.nextId(name);
                count++;
            }
            return count;
        }