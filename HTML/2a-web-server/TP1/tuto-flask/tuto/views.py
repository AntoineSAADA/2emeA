from .app import app,db
from flask import render_template,url_for,redirect
from .models import get_sample,get_auteurs,get_livre_auteur,get_author,Author
from flask_wtf import FlaskForm
from wtforms import StringField , HiddenField
from wtforms . validators import DataRequired
from wtforms import PasswordField
from .models import User
from hashlib import sha256
from flask_login import login_user,current_user
from flask import request


class LoginForm(FlaskForm):
    username = StringField('Username')
    password = PasswordField('Password')
    
    def get_authenticated_user(self):
        user = User.query.get(self.username.data)
        if user is None:
            return None
        m = sha256()
        m.update(self.password.data.encode())
        passwd = m.hexdigest()
        return user if passwd == user.password else None


class AuthorForm ( FlaskForm ):
    id = HiddenField('id')
    name = StringField ('Nom', validators =[ DataRequired ()])

@app.route('/save/author/', methods =['POST'])
def save_author():
    a = None
    f = AuthorForm()
    if f.validate_on_submit():
        id = int(f.id.data)
        a = get_auteurs(id)
        a.name = f.name.data
        db.session.commit()
        return redirect(url_for('one_author',id=a.id))
    a= get_author(int(f.id.data))
    return render_template('edit-author.html',author=a,form=f)


@app.route ("/edit/author/<int:id>")
def edit_author (id ):
    a = get_author(id)
    f = AuthorForm(id=a.id , name=a.name)
    return render_template (
    "edit-author.html",
    author =a, form=f)


@app.route("/")
def home():
    return render_template(
        "home.html",
        title = "Bibliothèque",
        livres = get_sample()
    )
    
@app.route("/detail/<id>")
def detail(id):
    books = get_sample()
    book = books[int(id)]
    return render_template(
    "detail.html",
    book=book)
    
@app.route("/listeauteurs")
def author():
    return render_template(
    "author.html",
    auteur = get_auteurs()
)
    
@app.route("/author/<int:id>")
def author_detail(id):
    return render_template(
    "auteurPr.html",
    book = get_livre_auteur(id)
)

@app.route ("/login/", methods =("GET","POST"))
def login():
    f = LoginForm()
    if f.validate_on_submit():
        user = f.get_authenticated_user()
    if user:
        login_user(user)
        return redirect(url_for("home"))
    return render_template("login.html",form=f)
    
    
