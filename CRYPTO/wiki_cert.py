import ssl

address = ('wikipedia.org', 443)
certif = ssl.get_server_certificate(address)
print(certif)

FLASK_APP=testhttpscert.py 
FLASK_DEBUG=1 flask run --cert=server.crt \--key=server.key --host=infoXX-YY