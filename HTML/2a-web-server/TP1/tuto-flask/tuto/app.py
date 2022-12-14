from flask import Flask
from flask_bootstrap import Bootstrap5
from flask_sqlalchemy import SQLAlchemy
import os.path
from flask_login import LoginManager

app = Flask(__name__)
app.config['BOOTSTRAP_SERVE_LOCAL'] = True
bootstrap = Bootstrap5(app)

def mkpath(p):
    return os.path.normpath(os.path.join(os.path.dirname(__file__),p))


app.config['SQLALCHEMY_DATABASE_URI'] = ('sqlite:///' + mkpath('../myapp.db'))
db = SQLAlchemy(app)

app.config['SECRET_KEY'] = "bcc090e2-26b2-4c16-84ab-e766cc644320"


login_manager = LoginManager(app)
