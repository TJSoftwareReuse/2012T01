# -*- coding: utf-8 -*-
import socket, sys, chardet

if len(sys.argv) != 3:
    print 'Parameter not mate, notice must use like this:'
    print '\rpython client.py [ip] [port]'
    # print '\rpython client.py [ip] [port] [member_name]'
    sys.exit(0)

ip = sys.argv[1]
port = sys.argv[2]
# member = sys.argv[3]

# print chardet.detect(member)

s = socket.socket()
# addr = ('192.168.56.1', 12234)
addr = (ip, int(port))

s.connect(addr)
member ='M:许铭淏'
# member = 'T:第一组'
s.send(member)

data = s.recv(1024)
print 'data: ',data.decode('utf-8')
