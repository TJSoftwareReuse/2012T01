# -*- coding: utf-8 -*-
import socket

s = socket.socket()
addr = ('192.168.56.1', 12234)

s.connect(addr)
member ='许铭淏'
s.send(member)

data = s.recv(1024)
print 'data: ',data.decode('utf-8')
