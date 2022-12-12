import click
from .app import app,db
import yaml


@app.cli.command()
@click.argument('filename')
def loaddb(filename):
    db.create_all()
    books = yaml.safe_load(open(filename))
    authors={}
    from .models import Author,Book
    for b in books:
        a = b["author"]
        if a not in authors:
            o = Author(name=a)
            db.session.add(o)
            authors[a] = o
    db.session.commit()
    
    for b in books:
        a = authors[b["author"]]
        o = Book(price = b["price"],
                 title = b["title"],
                 url =   b["url"],
                 img =   b["img"],
                 author_id = a.id)
        db.session.add(o)
    db.session.commit()

@app.cli.command()
def syncdb():
    db.create_all()
    
@app.cli.command()
@click.argument('username')
@click.argument('password')
def newuser(username,password):
    from .models import User
    from hashlib import sha256
    m = sha256()
    m.update(password.encode())
    u = User(username=username,password=m.hexdigest())
    db.session.add(u)
    db.session.commit()

        