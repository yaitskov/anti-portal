-- cassandra schema

drop keyspace if exists anti_portal;

create keyspace anti_portal WITH replication = {'class':'SimpleStrategy', 'replication_factor':3};

use anti_portal;

-- bunch individual ticks in groups by 10 minutes
-- 10 * 60 = 600
create table ticks (tenmin bigint,  -- tens of minutes
                    quota text,     -- quota
                    milsecs int,    -- seconds shift inside tenmin interval
                    torder  int,    -- tick order inside millisecond
                    meta    text,   -- source of price
                    price   double, --
                    primary key ((tenmin, quota), milsecs, torder));

-- aggregated values by minute intervals
create table minute_val (hour int,
                         quota text,
                         minute int,
                         price double,
                         primary key ((hour, quota), minute));


