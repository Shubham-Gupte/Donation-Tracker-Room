from flask import Flask, render_template, redirect, request, url_for
import requests
import json

app = Flask(__name__)
app.config['SECRET_KEY'] = 'you-will-never-guess'
loggedInFlask = False
attemptsRemaining = 3

@app.route('/')
def hello_world():
    return render_template("login.html")

@app.route('/register')
def register():
    return render_template("register.html")

@app.route('/locationsList')
def locationsList():
    if not loggedInFlask:
        return redirect(url_for('hello_world'))
    URL = "https://donation-tracker-room.firebaseio.com/locations.json"
    r = requests.get(url = URL)
    data = r.json()
    return render_template("locationsList.html", data= data)

@app.route('/location/<key>')
def location(key):
    if not loggedInFlask:
        return redirect(url_for('hello_world'))
    key = int(key) - 1
    URL = "https://donation-tracker-room.firebaseio.com/locations/" + str(key) +".json"
    r = requests.get(url = URL)
    data = r.json()
    return render_template("location.html", data=data)

@app.route('/addItem/<location>')
def addItems(location):
    if not loggedInFlask:
        return redirect(url_for('hello_world'))
    key = int(location) - 1
    return render_template("addItem.html", location=key)

@app.route('/handle_data/<location>', methods = ['GET', 'POST'])
def handle_data(location):
    if not loggedInFlask:
        return redirect(url_for('hello_world'))
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
