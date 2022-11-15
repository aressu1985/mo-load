# mo-load
how to run sysbench insert test:

1.git clone https://github.com/aressu1985/mo-load.git
2.modify mo addr in mo.yml
3.create database
create database sbtest;
4. git clone https://github.com/aressu1985/mo-sysbench.git
5.generate data
cd mo-sysbench & sysbench --mysql-host=127.0.0.1 --mysql-port=6001 --mysql-user=dump --mysql-password=111 oltp_common.lua --tables=10 --table_size=10000 --threads=1 --time=30 --report-interval=10 --create_secondary=off --auto_inc=off prepare

6.run test
./start.sh -c cases/sysbench/mixed_10_10000/
