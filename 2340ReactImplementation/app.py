from flask import Flask, render_template, redirect, request, url_for
import requests
import json
import pyrebase

config = {
  "apiKey": "AIzaSyDb5OTYD1fQhxyA-iLKWzX_Og666-DgK_E",
  "authDomain": "donation-tracker-room.firebaseapp.com",
  "databaseURL": "https://donation-tracker-room.firebaseio.com",
  "storageBucket": "donation-tracker-room.appspot.com"
}

firebase = pyrebase.initialize_app(config)
auth = firebase.auth()

app = Flask(__name__)
app.config['SECRET_KEY'] = 'you-will-never-guess'

@app.route('/', methods = ['GET', 'POST'])
def hello_world():
    if request.method == 'POST':
        email = request.form['firstname']
        password = request.form['lastname']
        user = auth.sign_in_with_email_and_password(email, password)
        print(user)
        return redirect(url_for('locationsList'))
    else:
        return render_template("login.html")

@app.route('/register')
def register():
    return render_template("register.html")

@app.route('/locationsList')
def locationsList():
    URL = "https://donation-tracker-room.firebaseio.com/locations.json"
    r = requests.get(url = URL)
    data = r.json()
    return render_template("locationsList.html", data= data)

@app.route('/location/<key>')
def location(key):
    key = int(key) - 1
    URL = "https://donation-tracker-room.firebaseio.com/locations/" + str(key) +".json"
    r = requests.get(url = URL)
    data = r.json()
    return render_template("location.html", data=data)

@app.route('/addItem/<location>')
def addItems(location):
    key = int(location) - 1
    return render_template("addItem.html", location=key)

@app.route('/handle_data/<location>', methods = ['GET', 'POST'])
def handle_data(location):
    key = int(location) - 1
    if request.method == 'POST':
        cost = request.form['cost']
        type1 = request.form['type']
        name = request.form['name']
        donationDate = request.form['date']
        donationLocation = key
        toAdd = {
            "cost":cost,
            "type":type1,
            "name":name,
            "donationDate":donationDate,
            "donationLocation": donationLocation
        }
        toAdd = json.dumps(toAdd)
        loaded_toAdd = json.loads(toAdd)
        URL = "https://donation-tracker-room.firebaseio.com/locations/" + str(key + 1) +"/Items.json"
        requests.post(URL, json=loaded_toAdd)
        return redirect(url_for('locationsList'))
    else:
        return redirect(url_for('locationsList'))
