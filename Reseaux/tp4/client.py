import socket

def client(host,port):
    sock = socket.socket()
    sock.connect((host,port))
    f = sock.makefile(mode="rw")
    x = None 
    while x != "quit":
        x = input("get,inc,dec,add,sub,quit?")
        f.write(x+"\n")
        f.flush()
        line = f.readline().strip()
        print(line) 
    f.close()
    sock.shutdown(socket.SHUT_RDWR)
    sock.close()

client("localhost",5555)
    
