from flask_login import UserMixin
from .app import db 
from .app import login_manager

class User(db.Model,UserMixin):
    username = db.Column(db.String(50),primary_key=True)
    password = db.Column(db.String(64))
    
    def get_id(self):
        return self.username

class Author(db.Model):
    id = db.Column(db.Integer,primary_key=True)
    name = db.Column(db.String(100))
    
    def __repr__(self):
        return "<Author (%d) %s>" % (self.id,self.name)
    
class Book(db.Model):
    id = db.Column(db.Integer,primary_key =True)
    price = db.Column(db.Float)
    title = db.Column(db.String(100))
    url = db.Column(db.String(200))
    img = db.Column(db.String(300))
    author_id = db.Column(db.Integer,db.ForeignKey("author.id"))
    author = db.relationship("Author",backref=db.backref("books",lazy="dynamic"))
    def __repr__ (self):
        return "<Book (%d) %s>" % (self.id, self.title)
    
def get_sample():
    return Book.query.all()

def get_auteurs():
    return Author.query.all()

def get_livre_auteur(id):
    return Book.query.filter_by(author_id=id).all()

def get_author(id):
    return Author.query.get(id)

@login_manager.user_loader
def load_user(username):
    return User.query.get(username)