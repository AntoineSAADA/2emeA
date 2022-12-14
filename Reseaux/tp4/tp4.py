import socket 

class Server:
    def __init__(self):
        self.counter = 0
    
    def mainServer(self,port):
        sock = socket.socket()
        sock.bind(("0.0.0.0",port))
        sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        sock.listen(10)
        while True:
            cli, addr = sock.accept()
            sess = Session(self,cli)
            sess.mainSession()
        

class Session:
    def __init__(self,server,sock):
        self.server = server
        self.sock = sock
        self.file = sock.makefile(mode="rw")
        
    def mainSession(self):
        while True:
            line = self.file.readline().strip()
            if line == "get":
                self.file.write("val %d\n" % self.server.counter)
                self.file.flush()
            elif line == "quit":
                self.file.write("quit\n")
                self.file.flush()
                break
            elif line == "inc":
                self.server.counter += 1
                self.file.write("ok\n")
                self.file.flush()
            elif line == "dec":
                self.server.counter -= 1
                self.file.write("ok\n")
                self.file.flush()
            elif line.startswith("add"):
                try:
                    self.server.counter += int(line.split()[1])
                    self.file.write("ok\n")
                    self.file.flush()
                except:
                    self.file.write("error\n")
                    self.file.flush()
            elif line.startswith("sub"):
                try:
                    self.server.counter -= int(line.split()[1])
                    self.file.write("ok\n")
                    self.file.flush()
                except:
                    self.file.write("error\n")
                    self.file.flush()
                             
            else:
                self.file.write("error\n")
                self.file.flush()
        self.file.close()
        self.sock.shutdown(socket.SHUT_RDWR)
        self.sock.close()

Server().mainServer(5555)