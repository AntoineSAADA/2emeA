from flask import Flask

app = Flask(__name__)

@app.route('/')
def home():
    return "c<h1>Hello, World!</h1>"